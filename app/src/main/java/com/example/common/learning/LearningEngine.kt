package com.example.common.learning

import android.content.Context
import com.example.data.Category
import com.example.data.Question
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.json.JSONObject

enum class MasteryLevel(val label: String, val weight: Int) {
    NEEDS_REVIEW("Needs Review", 3),
    LEARNING("Learning", 2),
    MASTERED("Mastered", 1)
}

data class CardLearningState(
    val questionId: Int,
    val timesReviewed: Int = 0,
    val timesCorrect: Int = 0,
    val timesIncorrect: Int = 0,
    val mastery: MasteryLevel = MasteryLevel.NEEDS_REVIEW,
    val lastReviewedTimestamp: Long = 0L
)

/**
 * Standardized Learning, Spaced Repetition, and Acceptance Engine.
 * Designed as a modular package that can be shared across educational apps
 * (US Civics, USPS 955, Technical Certifications, etc.).
 */
class LearningEngine(context: Context) {

    private val prefs = context.getSharedPreferences("shared_learning_engine_prefs", Context.MODE_PRIVATE)

    private val _learningStates = MutableStateFlow<Map<Int, CardLearningState>>(emptyMap())
    val learningStates: StateFlow<Map<Int, CardLearningState>> = _learningStates.asStateFlow()

    init {
        loadStates()
    }

    private fun loadStates() {
        val rawJson = prefs.getString("card_learning_states", "{}") ?: "{}"
        try {
            val json = JSONObject(rawJson)
            val map = mutableMapOf<Int, CardLearningState>()
            val keys = json.keys()
            while (keys.hasNext()) {
                val key = keys.next()
                val obj = json.getJSONObject(key)
                val id = key.toInt()
                val reviewed = obj.optInt("reviewed", 0)
                val correct = obj.optInt("correct", 0)
                val incorrect = obj.optInt("incorrect", 0)
                val masteryStr = obj.optString("mastery", MasteryLevel.NEEDS_REVIEW.name)
                val mastery = try { MasteryLevel.valueOf(masteryStr) } catch (_: Exception) { MasteryLevel.NEEDS_REVIEW }
                val time = obj.optLong("time", 0L)
                map[id] = CardLearningState(
                    questionId = id,
                    timesReviewed = reviewed,
                    timesCorrect = correct,
                    timesIncorrect = incorrect,
                    mastery = mastery,
                    lastReviewedTimestamp = time
                )
            }
            _learningStates.value = map
        } catch (_: Exception) {}
    }

    private fun saveStates(map: Map<Int, CardLearningState>) {
        _learningStates.value = map
        try {
            val json = JSONObject()
            map.forEach { (id, state) ->
                val obj = JSONObject()
                obj.put("reviewed", state.timesReviewed)
                obj.put("correct", state.timesCorrect)
                obj.put("incorrect", state.timesIncorrect)
                obj.put("mastery", state.mastery.name)
                obj.put("time", state.lastReviewedTimestamp)
                json.put(id.toString(), obj)
            }
            prefs.edit().putString("card_learning_states", json.toString()).apply()
        } catch (_: Exception) {}
    }

    fun recordFlashcardReview(questionId: Int, rating: MasteryLevel) {
        val current = _learningStates.value.toMutableMap()
        val existing = current[questionId] ?: CardLearningState(questionId = questionId)
        val isCorrect = rating == MasteryLevel.MASTERED || rating == MasteryLevel.LEARNING

        val updated = existing.copy(
            timesReviewed = existing.timesReviewed + 1,
            timesCorrect = existing.timesCorrect + (if (isCorrect) 1 else 0),
            timesIncorrect = existing.timesIncorrect + (if (!isCorrect) 1 else 0),
            mastery = rating,
            lastReviewedTimestamp = System.currentTimeMillis()
        )
        current[questionId] = updated
        saveStates(current)
    }

    fun recordQuestionOutcome(questionId: Int, isCorrect: Boolean) {
        val current = _learningStates.value.toMutableMap()
        val existing = current[questionId] ?: CardLearningState(questionId = questionId)

        val newMastery = when {
            isCorrect && existing.mastery == MasteryLevel.LEARNING -> MasteryLevel.MASTERED
            isCorrect -> MasteryLevel.LEARNING
            else -> MasteryLevel.NEEDS_REVIEW
        }

        val updated = existing.copy(
            timesReviewed = existing.timesReviewed + 1,
            timesCorrect = existing.timesCorrect + (if (isCorrect) 1 else 0),
            timesIncorrect = existing.timesIncorrect + (if (!isCorrect) 1 else 0),
            mastery = newMastery,
            lastReviewedTimestamp = System.currentTimeMillis()
        )
        current[questionId] = updated
        saveStates(current)
    }

    /**
     * Spaced Repetition Ordering Algorithm:
     * Prioritizes questions based on error weight and mastery level:
     * Needs Review (weight 3) > Learning (weight 2) > Mastered (weight 1).
     */
    fun getSpacedRepetitionQueue(allQuestions: List<Question>, category: Category? = null): List<Question> {
        val pool = if (category != null) allQuestions.filter { it.category == category } else allQuestions
        val states = _learningStates.value

        return pool.sortedWith { q1, q2 ->
            val s1 = states[q1.id]
            val s2 = states[q2.id]
            val w1 = s1?.mastery?.weight ?: MasteryLevel.NEEDS_REVIEW.weight
            val w2 = s2?.mastery?.weight ?: MasteryLevel.NEEDS_REVIEW.weight
            if (w1 != w2) {
                w2.compareTo(w1) // higher weight first
            } else {
                val t1 = s1?.lastReviewedTimestamp ?: 0L
                val t2 = s2?.lastReviewedTimestamp ?: 0L
                t1.compareTo(t2) // older reviews first
            }
        }
    }

    fun getQuestionsByMastery(allQuestions: List<Question>, mastery: MasteryLevel): List<Question> {
        val states = _learningStates.value
        return allQuestions.filter { q ->
            val state = states[q.id]
            if (mastery == MasteryLevel.NEEDS_REVIEW) {
                state == null || state.mastery == MasteryLevel.NEEDS_REVIEW
            } else {
                state?.mastery == mastery
            }
        }
    }
}
