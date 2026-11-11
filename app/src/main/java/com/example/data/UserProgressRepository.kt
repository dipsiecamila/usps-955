package com.example.data

import android.content.Context
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.json.JSONArray
import org.json.JSONObject

class UserProgressRepository(context: Context) {

    private val prefs = context.getSharedPreferences("usps_955_user_prefs", Context.MODE_PRIVATE)

    private val _bookmarkedIds = MutableStateFlow<Set<Int>>(emptySet())
    val bookmarkedIds: StateFlow<Set<Int>> = _bookmarkedIds.asStateFlow()

    private val _answeredQuestions = MutableStateFlow<Map<Int, Boolean>>(emptyMap()) // id -> isCorrect
    val answeredQuestions: StateFlow<Map<Int, Boolean>> = _answeredQuestions.asStateFlow()

    private val _targetCraft = MutableStateFlow<CraftRole>(CraftRole.MPE_9)
    val targetCraft: StateFlow<CraftRole> = _targetCraft.asStateFlow()

    private val _examHistory = MutableStateFlow<List<ExamAttempt>>(emptyList())
    val examHistory: StateFlow<List<ExamAttempt>> = _examHistory.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        // Load bookmarks
        val bmString = prefs.getString("bookmarks", "") ?: ""
        if (bmString.isNotEmpty()) {
            val ids = bmString.split(",").mapNotNull { it.toIntOrNull() }.toSet()
            _bookmarkedIds.value = ids
        }

        // Load answered questions
        val ansString = prefs.getString("answered", "{}") ?: "{}"
        try {
            val jsonObj = JSONObject(ansString)
            val map = mutableMapOf<Int, Boolean>()
            val keys = jsonObj.keys()
            while (keys.hasNext()) {
                val key = keys.next()
                map[key.toInt()] = jsonObj.getBoolean(key)
            }
            _answeredQuestions.value = map
        } catch (_: Exception) {}

        // Load target craft
        val craftCode = prefs.getString("target_craft", CraftRole.MPE_9.name) ?: CraftRole.MPE_9.name
        _targetCraft.value = try {
            CraftRole.valueOf(craftCode)
        } catch (_: Exception) {
            CraftRole.MPE_9
        }

        // Load exam history
        val historyJson = prefs.getString("exam_history", "[]") ?: "[]"
        try {
            val arr = JSONArray(historyJson)
            val list = mutableListOf<ExamAttempt>()
            for (i in 0 until arr.length()) {
                val obj = arr.getJSONObject(i)
                val total = obj.getInt("total")
                val correct = obj.getInt("correct")
                val pct = obj.getInt("pct")
                val duration = obj.getInt("duration")
                val timestamp = obj.getLong("timestamp")
                list.add(
                    ExamAttempt(
                        id = timestamp,
                        timestamp = timestamp,
                        totalQuestions = total,
                        correctCount = correct,
                        scorePercentage = pct,
                        durationSeconds = duration,
                        categoryBreakdown = emptyMap(),
                        craftQualifications = CraftRole.entries.associateWith { pct >= it.passingScore }
                    )
                )
            }
            _examHistory.value = list
        } catch (_: Exception) {}
    }

    fun toggleBookmark(questionId: Int) {
        val current = _bookmarkedIds.value.toMutableSet()
        if (current.contains(questionId)) {
            current.remove(questionId)
        } else {
            current.add(questionId)
        }
        _bookmarkedIds.value = current
        prefs.edit().putString("bookmarks", current.joinToString(",")).apply()
    }

    fun recordAnswer(questionId: Int, isCorrect: Boolean) {
        val current = _answeredQuestions.value.toMutableMap()
        current[questionId] = isCorrect
        _answeredQuestions.value = current

        try {
            val obj = JSONObject()
            current.forEach { (k, v) -> obj.put(k.toString(), v) }
            prefs.edit().putString("answered", obj.toString()).apply()
        } catch (_: Exception) {}
    }

    fun setTargetCraft(craft: CraftRole) {
        _targetCraft.value = craft
        prefs.edit().putString("target_craft", craft.name).apply()
    }

    fun recordExamAttempt(attempt: ExamAttempt) {
        val list = _examHistory.value.toMutableList()
        list.add(0, attempt)
        _examHistory.value = list

        try {
            val arr = JSONArray()
            list.take(20).forEach { item ->
                val obj = JSONObject()
                obj.put("total", item.totalQuestions)
                obj.put("correct", item.correctCount)
                obj.put("pct", item.scorePercentage)
                obj.put("duration", item.durationSeconds)
                obj.put("timestamp", item.timestamp)
                arr.put(obj)
            }
            prefs.edit().putString("exam_history", arr.toString()).apply()
        } catch (_: Exception) {}
    }

    fun resetProgress() {
        _bookmarkedIds.value = emptySet()
        _answeredQuestions.value = emptyMap()
        _examHistory.value = emptyList()
        prefs.edit().clear().apply()
    }
}
