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
    val standardExpression: String,
    val alternativeSymbols: String, // Light green symbols (e.g. "•  ^  &")
    val logicTag: String,          // Light blue uppercase tag (e.g. "AND")
    val description: String,
    val truthTable: List<Pair<String, String>> // "A, B" -> "Output"
)

data class LotoStepInfo(
    val stepNumber: Int,
    val shortTitle: String,
    val detail: String,
    val isVerificationStep: Boolean = false
)

data class LotoDrillScenario(
    val id: String,
    val title: String,
    val equipmentName: String,
    val description: String,
    val correctOrderedSteps: List<String>
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
            standardExpression = "Y = A • B",
            alternativeSymbols = "•  ^  &",
            logicTag = "AND",
            description = "Output is HIGH (1) only when ALL inputs are HIGH (1).",
            truthTable = listOf(
                "0, 0" to "0",
                "0, 1" to "0",
                "1, 0" to "0",
                "1, 1" to "1"
            )
        ),
        LogicGateInfo(
            name = "OR Gate",
            standardExpression = "Y = A + B",
            alternativeSymbols = "+  v  ||",
            logicTag = "OR",
            description = "Output is HIGH (1) if ANY input is HIGH (1).",
            truthTable = listOf(
                "0, 0" to "0",
                "0, 1" to "1",
                "1, 0" to "1",
                "1, 1" to "1"
            )
        ),
        LogicGateInfo(
            name = "NOT Gate (Inverter)",
            standardExpression = "Y = A̅",
            alternativeSymbols = "¬  !  ~",
            logicTag = "NOT",
            description = "Inverts the input signal: HIGH becomes LOW, and LOW becomes HIGH.",
            truthTable = listOf(
                "0" to "1",
                "1" to "0"
            )
        ),
        LogicGateInfo(
            name = "NAND Gate",
            standardExpression = "Y = NOT(A • B)",
            alternativeSymbols = "¬(•)  !(^)  !(&)",
            logicTag = "NAND",
            description = "Universal gate. Output is LOW (0) ONLY when ALL inputs are HIGH (1).",
            truthTable = listOf(
                "0, 0" to "1",
                "0, 1" to "1",
                "1, 0" to "1",
                "1, 1" to "0"
            )
        ),
        LogicGateInfo(
            name = "NOR Gate",
            standardExpression = "Y = NOT(A + B)",
            alternativeSymbols = "¬(+)  !(v)  !(||)",
            logicTag = "NOR",
            description = "Universal gate. Output is HIGH (1) ONLY when ALL inputs are LOW (0).",
            truthTable = listOf(
                "0, 0" to "1",
                "0, 1" to "0",
                "1, 0" to "0",
                "1, 1" to "0"
            )
        ),
        LogicGateInfo(
            name = "XOR Gate (Exclusive OR)",
            standardExpression = "Y = A ⊕ B",
            alternativeSymbols = "⊕  ≠  !=",
            logicTag = "NOT EQUAL",
            description = "Output is HIGH (1) when inputs are DIFFERENT (odd parity).",
            truthTable = listOf(
                "0, 0" to "0",
                "0, 1" to "1",
                "1, 0" to "1",
                "1, 1" to "0"
            )
        ),
        LogicGateInfo(
            name = "XNOR Gate (Equivalence)",
            standardExpression = "Y = NOT(A ⊕ B)",
            alternativeSymbols = "⊙  =  ==",
            logicTag = "EQUAL",
            description = "Output is HIGH (1) when inputs are IDENTICAL (both 0 or both 1).",
            truthTable = listOf(
                "0, 0" to "1",
                "0, 1" to "0",
                "1, 0" to "0",
                "1, 1" to "1"
            )
        )
    )

    val lotoSteps: List<LotoStepInfo> = listOf(
        LotoStepInfo(
            stepNumber = 1,
            shortTitle = "1. Identification",
            detail = "Survey and identify all energy sources (electrical, pneumatic, hydraulic, kinetic, gravity, thermal).",
            isVerificationStep = false
        ),
        LotoStepInfo(
            stepNumber = 2,
            shortTitle = "2. Notification",
            detail = "Notify affected postal operators, tour supervisors, and maintenance technicians that equipment will be locked out.",
            isVerificationStep = false
        ),
        LotoStepInfo(
            stepNumber = 3,
            shortTitle = "3. Normal Shutdown",
            detail = "Stop the machine using standard operating stop buttons and sequence procedures.",
            isVerificationStep = false
        ),
        LotoStepInfo(
            stepNumber = 4,
            shortTitle = "4. Energy Isolation",
            detail = "Open all primary electrical disconnect switches, main circuit breakers, and pneumatic supply ball valves.",
            isVerificationStep = false
        ),
        LotoStepInfo(
            stepNumber = 5,
            shortTitle = "5. Lockout & Tagout",
            detail = "Apply standardized postal safety padlocks and danger tags with technician identification to every isolating device.",
            isVerificationStep = false
        ),
        LotoStepInfo(
            stepNumber = 6,
            shortTitle = "6. Stored Energy Release",
            detail = "Bleed air lines, discharge hydraulic accumulators, lower counterweights, block gravity loads, and discharge capacitors.",
            isVerificationStep = false
        ),
        LotoStepInfo(
            stepNumber = 7,
            shortTitle = "7. Zero-Energy Verification",
            detail = "CRITICAL: Attempt local restart to verify isolation, then perform a 3-point Live-Dead-Live meter test on all phases to prove ZERO voltage.",
            isVerificationStep = true
        )
    )

    val lotoDrillScenarios: List<LotoDrillScenario> = listOf(
        LotoDrillScenario(
            id = "drill_dbcs_sorter",
            title = "DBCS Sorter Drive & Belt Lockout",
            equipmentName = "Delivery Bar Code Sorter (DBCS)",
            description = "A maintenance technician must service the main transport belt drive and replace a pinch roller assembly.",
            correctOrderedSteps = listOf(
                "1. Identify 480V 3-phase feed and 90 psi pneumatic line",
                "2. Notify tour supervisor and mail processing operator",
                "3. Press standard machine stop button on DBCS console",
                "4. Throw main 480V disconnect handle to OFF and shut pneumatic valve",
                "5. Apply postal padlock and signed Danger Tag to disconnect and valve",
                "6. Open air drain valve to bleed residual 90 psi manifold pressure",
                "7. Attempt console start, then verify ZERO voltage using Live-Dead-Live meter test"
            )
        ),
        LotoDrillScenario(
            id = "drill_chiller_pump",
            title = "HVAC Chilled Water Pump Lockout (BEM)",
            equipmentName = "Plant Chiller & Secondary Pump #2",
            description = "BEM technician must rebuild mechanical seals on a 480V, 25 HP chilled water circulation pump.",
            correctOrderedSteps = listOf(
                "1. Review mechanical & electrical schematics for pump #2 and motor control center",
                "2. Notify building facilities supervisor and control room engineer",
                "3. Stop pump via building automation console and hand-off-auto switch",
                "4. Open MCC feeder disconnect and close suction/discharge water valves",
                "5. Install lockout hasp, postal lock, and danger tag on MCC breaker handle",
                "6. Depressurize pump casing bleed valve and verify expansion tank isolated",
                "7. Test MCC load side terminals with calibrated multimeter using Live-Dead-Live method"
            )
        ),
        LotoDrillScenario(
            id = "drill_afcs_cabinet",
            title = "AFCS Automation Cabinet 480V Feeder Lockout",
            equipmentName = "Advanced Facer Canceler System (AFCS)",
            description = "Electronic technician (ET) needs to replace a fused power distribution terminal block inside the primary power bay.",
            correctOrderedSteps = listOf(
                "1. Identify main feeder panel breaker and 120V control transformer circuit",
                "2. Notify automation maintenance supervisor and canceler operations crew",
                "3. Perform controlled shutdown of AFCS controller and sub-systems",
                "4. Turn wall-mounted disconnect switch to the OFF position",
                "5. Attach personal postal safety lock and completed Danger Tag to handle hasp",
                "6. Discharge internal DC bus filter capacitors through high-wattage bleed resistor",
                "7. Perform 3-point Live-Dead-Live test across all three line and load phases"
            )
        ),
        LotoDrillScenario(
            id = "drill_bulk_pusher",
            title = "Parcel Sorter Pneumatic Pusher Arm Lockout",
            equipmentName = "Bulk Parcel Sorter Ejector #4",
            description = "Mechanic must replace the rod-end clevis and guide bearings on a pneumatic pusher cylinder.",
            correctOrderedSteps = listOf(
                "1. Identify compressed air supply header and 24V DC solenoid valve circuit",
                "2. Inform parcel sorter induction crew and lead mechanic",
                "3. Stop parcel conveyor and disable automated ejector sequence",
                "4. Close pneumatic lockout ball valve feeding ejector bank #4",
                "5. Fasten safety padlock and tag through lockout ball valve handle hole",
                "6. Cycle exhaust valve and physically pin/block cylinder pusher arm from moving",
                "7. Verify pressure gauge reads 0 psi and press manual test solenoid to verify dead line"
            )
        )
    )
}
