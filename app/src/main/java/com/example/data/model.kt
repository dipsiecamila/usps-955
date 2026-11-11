package com.example.data

enum class Category(
    val title: String,
    val subtitle: String,
    val iconName: String
) {
    ELECTRONICS(
        "Electronics & Electricity",
        "Ohm's Law, AC/DC, Digital Logic, Motors & VFDs",
        "electrical_services"
    ),
    MECHANICAL(
        "Mechanics & Transmission",
        "Gears, Pulleys, Belts, Bearings & Fasteners",
        "build"
    ),
    FLUID_POWER(
        "Hydraulics & Pneumatics",
        "Pumps, Valves, Cylinders & Compressors",
        "water_drop"
    ),
    MAINTENANCE(
        "Maint. & Troubleshooting",
        "DBCS Sorters, Photo-eyes, Sensors & PMs",
        "precision_manufacturing"
    ),
    SPATIAL(
        "Spatial Relations",
        "2D-to-3D Pattern Folding & Component Shapes",
        "view_in_ar"
    ),
    HVAC_BUILDING(
        "HVAC & Plant Systems",
        "Refrigeration Cycle, Chillers, Boilers & Pumps",
        "ac_unit"
    )
}

enum class CraftRole(
    val title: String,
    val code: String,
    val passingScore: Int,
    val description: String
) {
    MM_7(
        "Maintenance Mechanic",
        "MM-7",
        70,
        "Entry maintenance craft. Focus on PMs, basic electrical, lubrication, and mechanical repairs."
    ),
    MPE_9(
        "Mail Processing Equip.",
        "MPE-9",
        70,
        "Automation specialist for DBCS, AFCS, parcel sorters, optical sensors, and high-speed belts."
    ),
    ET_10(
        "Electronic Technician",
        "ET-10",
        70,
        "Highest technical level. Digital logic, PLCs, motor control drives, board-level diagnosis."
    ),
    BEM_9(
        "Building Equip. Mechanic",
        "BEM-9",
        70,
        "Facility infrastructure. HVAC chillers, boilers, 480V distribution, plumbing, and compressors."
    ),
    AMT_9(
        "Area Maintenance Tech",
        "AMT-9",
        70,
        "Mobile multi-craft technician servicing associate post offices, retail equipment, and locks."
    )
}

data class Question(
    val id: Int,
    val category: Category,
    val crafts: List<CraftRole>,
    val questionText: String,
    val options: List<String>,
    val correctIndex: Int,
    val explanation: String,
    val formulaOrRule: String? = null,
    val diagramType: String? = null,
    val diagramData: String? = null
)

data class UserQuestionState(
    val questionId: Int,
    val selectedOption: Int? = null,
    val isBookmarked: Boolean = false,
    val isFlagged: Boolean = false,
    val isAnswered: Boolean = false,
    val isCorrect: Boolean = false
)

data class ExamAttempt(
    val id: Long = System.currentTimeMillis(),
    val timestamp: Long = System.currentTimeMillis(),
    val totalQuestions: Int,
    val correctCount: Int,
    val scorePercentage: Int,
    val durationSeconds: Int,
    val categoryBreakdown: Map<Category, Pair<Int, Int>>, // category -> Pair(correct, total)
    val craftQualifications: Map<CraftRole, Boolean>
)

data class DiagnosticTestStep(
    val stepTitle: String,
    val description: String,
    val tool: String,
    val testOptions: List<String>,
    val correctOption: Int,
    val findingExplanation: String
)

data class TroubleshootingCase(
    val id: String,
    val machine: String,
    val reportedSymptom: String,
    val initialConditions: String,
    val steps: List<DiagnosticTestStep>,
    val rootCauseSummary: String,
    val preventativeAction: String
)
