package com.example.data

data class FormulaItem(
    val title: String,
    val category: String,
    val formula: String,
    val description: String,
    val example: String
)

data class LogicGateInfo(
    val name: String,
    val expression: String,
    val description: String,
    val truthTable: List<Pair<String, String>> // "A, B" -> "Output"
)

object ReferenceData {

    val formulas: List<FormulaItem> = listOf(
        FormulaItem(
            title = "Ohm's Law & DC Power",
            category = "Electrical",
            formula = "V = I × R  |  P = V × I = I²R = V² / R",
            description = "Fundamental relation between voltage (V), current (Amperes), resistance (Ohms), and power (Watts).",
            example = "A 24V DC solenoid with 48 Ω resistance draws I = 24 / 48 = 0.5A. Power P = 24 × 0.5 = 12 Watts."
        ),
        FormulaItem(
            title = "Three-Phase AC Power",
            category = "Electrical",
            formula = "P (Watts) = √3 × V_line × I_line × Power Factor (cos θ)",
            description = "Calculates active electrical power in balanced 3-phase circuits. √3 ≈ 1.732.",
            example = "480V 3-phase motor drawing 10A line current with 0.85 PF: P = 1.732 × 480 × 10 × 0.85 = 7,067 Watts (7.07 kW)."
        ),
        FormulaItem(
            title = "AC Inductive & Capacitive Reactance",
            category = "Electrical",
            formula = "X_L = 2π f L  |  X_C = 1 / (2π f C)",
            description = "Opposition to alternating current from inductors (Henry) and capacitors (Farad) at line frequency f (60 Hz).",
            example = "At 60 Hz, a 0.5 Henry coil has X_L = 2 × 3.1416 × 60 × 0.5 ≈ 188.5 Ω."
        ),
        FormulaItem(
            title = "Transformer Voltage & Current Ratio",
            category = "Electrical",
            formula = "V_p / V_s = N_p / N_s = I_s / I_p",
            description = "Step-up or step-down ratio where primary and secondary turns (N) set voltage. Current transforms inversely.",
            example = "A 480V to 120V step-down transformer has a 4:1 turns ratio. If secondary draws 8A, primary draws 2A."
        ),
        FormulaItem(
            title = "Gear Speed & Gear Ratio",
            category = "Mechanical",
            formula = "Gear Ratio = N_driven / N_driver = RPM_driver / RPM_driven",
            description = "Speed ratio between meshed gears. Torque multiplies proportionally to the reduction ratio.",
            example = "A 20-tooth pinion driving an 80-tooth gear gives GR = 80/20 = 4:1. Motor at 1,750 RPM turns output shaft at 437.5 RPM with 4x torque."
        ),
        FormulaItem(
            title = "Pulley & V-Belt Speed",
            category = "Mechanical",
            formula = "D_driver × RPM_driver = D_driven × RPM_driven",
            description = "Speed relation across sheaves based on pitch diameter (D).",
            example = "A 4\" motor sheave spinning at 1,800 RPM drives a 12\" fan sheave: RPM_driven = (4 × 1800) / 12 = 600 RPM."
        ),
        FormulaItem(
            title = "Rotational Torque & Horsepower",
            category = "Mechanical",
            formula = "HP = (Torque in lb-ft × RPM) / 5252",
            description = "Relationship between mechanical torque, rotational velocity, and mechanical horsepower.",
            example = "At 1,750 RPM producing 15 lb-ft torque: HP = (15 × 1750) / 5252 = 5.0 HP."
        ),
        FormulaItem(
            title = "Hydraulic & Pneumatic Force",
            category = "Fluid Power",
            formula = "Force (lbs) = Pressure (psi) × Area (sq inches)  |  A = π × r²",
            description = "Pascal's principle applied to cylinder pistons for linear actuator thrust.",
            example = "A 3-inch bore cylinder (Area = 3.1416 × 1.5² = 7.07 sq in) at 100 psi produces F = 100 × 7.07 = 707 lbs of thrust."
        ),
        FormulaItem(
            title = "Cylinder Stroke Velocity",
            category = "Fluid Power",
            formula = "Velocity (in/sec) = (231 × Q_gpm) / (60 × Area_sq_in)",
            description = "Speed of cylinder extension based on hydraulic pump volumetric flow rate Q.",
            example = "5 GPM pump feeding a 10 sq in cylinder extends rod at ≈ 1.92 inches per second."
        )
    )

    val logicGates: List<LogicGateInfo> = listOf(
        LogicGateInfo(
            name = "AND Gate",
            expression = "Y = A · B",
            description = "Output is HIGH (1) only when BOTH inputs are HIGH (1).",
            truthTable = listOf(
                "0, 0" to "0",
                "0, 1" to "0",
                "1, 0" to "0",
                "1, 1" to "1"
            )
        ),
        LogicGateInfo(
            name = "OR Gate",
            expression = "Y = A + B",
            description = "Output is HIGH (1) if EITHER input is HIGH (1).",
            truthTable = listOf(
                "0, 0" to "0",
                "0, 1" to "1",
                "1, 0" to "1",
                "1, 1" to "1"
            )
        ),
        LogicGateInfo(
            name = "NAND Gate",
            expression = "Y = NOT(A · B)",
            description = "Universal gate. Output is LOW (0) only when BOTH inputs are HIGH (1).",
            truthTable = listOf(
                "0, 0" to "1",
                "0, 1" to "1",
                "1, 0" to "1",
                "1, 1" to "0"
            )
        ),
        LogicGateInfo(
            name = "NOR Gate",
            expression = "Y = NOT(A + B)",
            description = "Universal gate. Output is HIGH (1) only when BOTH inputs are LOW (0).",
            truthTable = listOf(
                "0, 0" to "1",
                "0, 1" to "0",
                "1, 0" to "0",
                "1, 1" to "0"
            )
        ),
        LogicGateInfo(
            name = "XOR Gate (Exclusive OR)",
            expression = "Y = A ⊕ B",
            description = "Output is HIGH (1) when inputs are DIFFERENT from each other.",
            truthTable = listOf(
                "0, 0" to "0",
                "0, 1" to "1",
                "1, 0" to "1",
                "1, 1" to "0"
            )
        )
    )

    val lotoSteps: List<Pair<String, String>> = listOf(
        "1. Preparation" to "Identify all energy sources (electrical, pneumatic, hydraulic, kinetic, gravity, thermal).",
        "2. Notification" to "Notify affected postal operators and supervisors that equipment will be shut down and locked out.",
        "3. Equipment Shutdown" to "Stop machine via standard operating stop buttons/procedures.",
        "4. Isolation" to "Operate main disconnect switches, circuit breakers, and pneumatic isolation ball valves.",
        "5. Lockout & Tagout" to "Apply standardized postal safety padlock and danger tag to energy-isolating devices.",
        "6. Stored Energy Dissipation" to "Bleed air lines, discharge hydraulic accumulators, lower counterweights, verify capacitors discharged.",
        "7. Verification (Live-Dead-Live)" to "Attempt machine restart; perform 3-point live-dead-live meter test on all phases to verify ZERO electrical potential."
    )
}
