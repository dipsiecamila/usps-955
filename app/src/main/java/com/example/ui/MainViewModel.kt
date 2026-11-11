package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

sealed class Screen {
    data object Home : Screen()
    data class Practice(val category: Category? = null) : Screen()
    data object ExamActive : Screen()
    data class ExamResultScreen(val result: ExamAttempt) : Screen()
    data object SpatialLab : Screen()
    data object Troubleshooting : Screen()
    data object Reference : Screen()
}

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = UserProgressRepository(application.applicationContext)

    // Navigation state
    private val _currentScreen = MutableStateFlow<Screen>(Screen.Home)
    val currentScreen: StateFlow<Screen> = _currentScreen.asStateFlow()

    private val screenBackStack = mutableListOf<Screen>()

    // Persistent user progress
    val bookmarkedIds = repository.bookmarkedIds
    val answeredQuestions = repository.answeredQuestions
    val targetCraft = repository.targetCraft
    val examHistory = repository.examHistory

    // Practice Mode State
    private val _practiceCategory = MutableStateFlow<Category?>(null)
    val practiceCategory: StateFlow<Category?> = _practiceCategory.asStateFlow()

    private val _practiceFilterBookmarkOnly = MutableStateFlow(false)
    val practiceFilterBookmarkOnly: StateFlow<Boolean> = _practiceFilterBookmarkOnly.asStateFlow()

    private val _practiceQuestionIndex = MutableStateFlow(0)
    val practiceQuestionIndex: StateFlow<Int> = _practiceQuestionIndex.asStateFlow()

    private val _practiceSelectedAnswer = MutableStateFlow<Int?>(null)
    val practiceSelectedAnswer: StateFlow<Int?> = _practiceSelectedAnswer.asStateFlow()

    private val _practiceShowExplanation = MutableStateFlow(false)
    val practiceShowExplanation: StateFlow<Boolean> = _practiceShowExplanation.asStateFlow()

    // Mock Exam State
    private val _examQuestions = MutableStateFlow<List<Question>>(emptyList())
    val examQuestions: StateFlow<List<Question>> = _examQuestions.asStateFlow()

    private val _examCurrentIndex = MutableStateFlow(0)
    val examCurrentIndex: StateFlow<Int> = _examCurrentIndex.asStateFlow()

    private val _examAnswers = MutableStateFlow<Map<Int, Int>>(emptyMap()) // questionId -> selectedOption
    val examAnswers: StateFlow<Map<Int, Int>> = _examAnswers.asStateFlow()

    private val _examFlagged = MutableStateFlow<Set<Int>>(emptySet())
    val examFlagged: StateFlow<Set<Int>> = _examFlagged.asStateFlow()

    private val _examSecondsRemaining = MutableStateFlow(1800) // 30 mins
    val examSecondsRemaining: StateFlow<Int> = _examSecondsRemaining.asStateFlow()

    private var timerJob: Job? = null
    private var examDurationSeconds = 1800

    // Spatial Lab State
    private val _spatialIndex = MutableStateFlow(0)
    val spatialIndex: StateFlow<Int> = _spatialIndex.asStateFlow()

    private val _spatialSelected = MutableStateFlow<Int?>(null)
    val spatialSelected: StateFlow<Int?> = _spatialSelected.asStateFlow()

    private val _spatialShowResult = MutableStateFlow(false)
    val spatialShowResult: StateFlow<Boolean> = _spatialShowResult.asStateFlow()

    // Troubleshooting State
    private val _currentScenarioIndex = MutableStateFlow(0)
    val currentScenarioIndex: StateFlow<Int> = _currentScenarioIndex.asStateFlow()

    private val _currentScenarioStep = MutableStateFlow(0)
    val currentScenarioStep: StateFlow<Int> = _currentScenarioStep.asStateFlow()

    private val _scenarioSelectedOption = MutableStateFlow<Int?>(null)
    val scenarioSelectedOption: StateFlow<Int?> = _scenarioSelectedOption.asStateFlow()

    private val _scenarioStepSubmitted = MutableStateFlow(false)
    val scenarioStepSubmitted: StateFlow<Boolean> = _scenarioStepSubmitted.asStateFlow()

    private val _scenarioCompleted = MutableStateFlow(false)
    val scenarioCompleted: StateFlow<Boolean> = _scenarioCompleted.asStateFlow()

    // Resistor Calculator State (4-band: Band1, Band2, Multiplier, Tolerance)
    private val _resistorBand1 = MutableStateFlow(1) // Brown (1)
    val resistorBand1: StateFlow<Int> = _resistorBand1.asStateFlow()

    private val _resistorBand2 = MutableStateFlow(0) // Black (0)
    val resistorBand2: StateFlow<Int> = _resistorBand2.asStateFlow()

    private val _resistorMultiplier = MutableStateFlow(2) // Red (10^2 = 100) -> 1,000 Ω (1k)
    val resistorMultiplier: StateFlow<Int> = _resistorMultiplier.asStateFlow()

    private val _resistorTolerance = MutableStateFlow(5) // Gold (±5%)
    val resistorTolerance: StateFlow<Int> = _resistorTolerance.asStateFlow()

    fun navigateTo(destination: Screen) {
        if (_currentScreen.value != destination) {
            screenBackStack.add(_currentScreen.value)
            _currentScreen.value = destination
        }
    }

    fun handleBack(): Boolean {
        if (screenBackStack.isNotEmpty()) {
            val prev = screenBackStack.removeAt(screenBackStack.lastIndex)
            _currentScreen.value = prev
            return true
        }
        if (_currentScreen.value !is Screen.Home) {
            _currentScreen.value = Screen.Home
            return true
        }
        return false
    }

    // Practice controls
    fun startPractice(category: Category? = null, bookmarkOnly: Boolean = false) {
        _practiceCategory.value = category
        _practiceFilterBookmarkOnly.value = bookmarkOnly
        _practiceQuestionIndex.value = 0
        _practiceSelectedAnswer.value = null
        _practiceShowExplanation.value = false
        navigateTo(Screen.Practice(category))
    }

    fun getFilteredPracticeQuestions(): List<Question> {
        val cat = _practiceCategory.value
        val bmOnly = _practiceFilterBookmarkOnly.value
        val bms = bookmarkedIds.value

        var list = QuestionBank.allQuestions
        if (cat != null) {
            list = list.filter { it.category == cat }
        }
        if (bmOnly) {
            list = list.filter { bms.contains(it.id) }
        }
        return list.ifEmpty { QuestionBank.allQuestions }
    }

    fun selectPracticeAnswer(index: Int) {
        if (_practiceSelectedAnswer.value == null) {
            _practiceSelectedAnswer.value = index
            _practiceShowExplanation.value = true

            val questions = getFilteredPracticeQuestions()
            val currentQ = questions.getOrNull(_practiceQuestionIndex.value)
            if (currentQ != null) {
                val isCorrect = index == currentQ.correctIndex
                repository.recordAnswer(currentQ.id, isCorrect)
            }
        }
    }

    fun nextPracticeQuestion() {
        val questions = getFilteredPracticeQuestions()
        if (_practiceQuestionIndex.value < questions.size - 1) {
            _practiceQuestionIndex.value += 1
            _practiceSelectedAnswer.value = null
            _practiceShowExplanation.value = false
        }
    }

    fun prevPracticeQuestion() {
        if (_practiceQuestionIndex.value > 0) {
            _practiceQuestionIndex.value -= 1
            _practiceSelectedAnswer.value = null
            _practiceShowExplanation.value = false
        }
    }

    fun toggleBookmark(questionId: Int) {
        repository.toggleBookmark(questionId)
    }

    fun setTargetCraft(craft: CraftRole) {
        repository.setTargetCraft(craft)
    }

    // Mock Exam
    fun startExam(questionCount: Int = 30, durationMinutes: Int = 30) {
        val questions = QuestionBank.allQuestions.shuffled().take(questionCount)
        _examQuestions.value = questions
        _examCurrentIndex.value = 0
        _examAnswers.value = emptyMap()
        _examFlagged.value = emptySet()
        examDurationSeconds = durationMinutes * 60
        _examSecondsRemaining.value = examDurationSeconds

        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (_examSecondsRemaining.value > 0) {
                delay(1000)
                _examSecondsRemaining.value -= 1
            }
            submitExam()
        }

        navigateTo(Screen.ExamActive)
    }

    fun selectExamAnswer(questionId: Int, optionIndex: Int) {
        val current = _examAnswers.value.toMutableMap()
        current[questionId] = optionIndex
        _examAnswers.value = current
    }

    fun toggleExamFlag(questionId: Int) {
        val current = _examFlagged.value.toMutableSet()
        if (current.contains(questionId)) current.remove(questionId) else current.add(questionId)
        _examFlagged.value = current
    }

    fun setExamCurrentIndex(index: Int) {
        val list = _examQuestions.value
        if (index in list.indices) {
            _examCurrentIndex.value = index
        }
    }

    fun submitExam() {
        timerJob?.cancel()
        val questions = _examQuestions.value
        val answers = _examAnswers.value

        var correctCount = 0
        val catMap = mutableMapOf<Category, Pair<Int, Int>>() // correct, total

        for (q in questions) {
            val userAns = answers[q.id]
            val isCorrect = userAns == q.correctIndex
            if (isCorrect) correctCount++

            val existing = catMap[q.category] ?: Pair(0, 0)
            catMap[q.category] = Pair(
                existing.first + (if (isCorrect) 1 else 0),
                existing.second + 1
            )
        }

        val pct = if (questions.isNotEmpty()) (correctCount * 100) / questions.size else 0
        val elapsedSeconds = examDurationSeconds - _examSecondsRemaining.value

        val attempt = ExamAttempt(
            totalQuestions = questions.size,
            correctCount = correctCount,
            scorePercentage = pct,
            durationSeconds = elapsedSeconds,
            categoryBreakdown = catMap,
            craftQualifications = CraftRole.entries.associateWith { pct >= it.passingScore }
        )

        repository.recordExamAttempt(attempt)
        _currentScreen.value = Screen.ExamResultScreen(attempt)
    }

    // Spatial Lab
    val spatialQuestions: List<Question> = QuestionBank.allQuestions.filter { it.category == Category.SPATIAL }

    fun selectSpatialAnswer(index: Int) {
        if (_spatialSelected.value == null) {
            _spatialSelected.value = index
            _spatialShowResult.value = true
        }
    }

    fun nextSpatialQuestion() {
        if (_spatialIndex.value < spatialQuestions.size - 1) {
            _spatialIndex.value += 1
            _spatialSelected.value = null
            _spatialShowResult.value = false
        }
    }

    fun prevSpatialQuestion() {
        if (_spatialIndex.value > 0) {
            _spatialIndex.value -= 1
            _spatialSelected.value = null
            _spatialShowResult.value = false
        }
    }

    // Troubleshooting
    fun selectScenario(index: Int) {
        _currentScenarioIndex.value = index
        _currentScenarioStep.value = 0
        _scenarioSelectedOption.value = null
        _scenarioStepSubmitted.value = false
        _scenarioCompleted.value = false
    }

    fun selectScenarioOption(index: Int) {
        if (!_scenarioStepSubmitted.value) {
            _scenarioSelectedOption.value = index
        }
    }

    fun submitScenarioStep() {
        val scenario = TroubleshootingData.scenarios.getOrNull(_currentScenarioIndex.value) ?: return
        val currentStep = scenario.steps.getOrNull(_currentScenarioStep.value) ?: return
        val selected = _scenarioSelectedOption.value ?: return

        _scenarioStepSubmitted.value = true
        if (selected == currentStep.correctOption) {
            if (_currentScenarioStep.value >= scenario.steps.size - 1) {
                _scenarioCompleted.value = true
            }
        }
    }

    fun advanceScenarioStep() {
        val scenario = TroubleshootingData.scenarios.getOrNull(_currentScenarioIndex.value) ?: return
        if (_currentScenarioStep.value < scenario.steps.size - 1) {
            _currentScenarioStep.value += 1
            _scenarioSelectedOption.value = null
            _scenarioStepSubmitted.value = false
        }
    }

    // Resistor Calculator setters
    fun setResistorBand1(digit: Int) { _resistorBand1.value = digit }
    fun setResistorBand2(digit: Int) { _resistorBand2.value = digit }
    fun setResistorMultiplier(power: Int) { _resistorMultiplier.value = power }
    fun setResistorTolerance(pct: Int) { _resistorTolerance.value = pct }

    fun calculateResistanceValue(): String {
        val b1 = _resistorBand1.value
        val b2 = _resistorBand2.value
        val mult = _resistorMultiplier.value
        val tol = _resistorTolerance.value

        val base = (b1 * 10 + b2)
        val ohms = base * Math.pow(10.0, mult.toDouble())

        val formattedOhms = when {
            ohms >= 1_000_000 -> String.format("%.2f MΩ", ohms / 1_000_000.0)
            ohms >= 1_000 -> String.format("%.2f kΩ", ohms / 1_000.0)
            else -> String.format("%.0f Ω", ohms)
        }

        return "$formattedOhms (±$tol%)"
    }

    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
    }
}
