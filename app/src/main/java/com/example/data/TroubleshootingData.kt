package com.example.data

object TroubleshootingData {

    val scenarios: List<TroubleshootingCase> = listOf(
        TroubleshootingCase(
            id = "case_dbcs_diverter",
            machine = "DBCS (Delivery Bar Code Sorter)",
            reportedSymptom = "Mail is bypassing stacker pocket #12 and mis-sorting into the overflow bin. Diverter paddle fails to actuate.",
            initialConditions = "Machine is in run mode. Control power is ON. Air pressure indicator on main gauge shows 85 psi (normal).",
            steps = listOf(
                DiagnosticTestStep(
                    stepTitle = "Step 1: Sensor & Input Check",
                    description = "Inspect the optical pocket entry photo-eye (PE-12) while passing a test mail piece in front of it.",
                    tool = "Visual LED & Digital Multimeter (DC Volts)",
                    testOptions = listOf(
                        "Measure 480V AC across the optical sensor wires",
                        "Check sensor indicator LED state and measure 24V DC signal line to PLC input terminal",
                        "Immediately replace the pneumatic air compressor",
                        "Adjust belt tension on main drive roller"
                    ),
                    correctOption = 1,
                    findingExplanation = "The photo-eye LED toggles correctly from OFF to ON, and the multimeter verifies 24V DC appears at the PLC input terminal. This proves the optical sensor, wiring, and PLC input card are functioning properly."
                ),
                DiagnosticTestStep(
                    stepTitle = "Step 2: PLC Output & Solenoid Coil Check",
                    description = "When mail triggers the pocket, check the PLC output LED and measure voltage at the 24V DC solenoid valve coil (SOL-12).",
                    tool = "Digital Multimeter (DC Volts & Ohms)",
                    testOptions = listOf(
                        "Measure DC voltage across solenoid valve coil terminals during actuation trigger",
                        "Measure motor winding resistance with a 1,000V Megger",
                        "Remove the stacker paddle with an impact wrench",
                        "Short the 24V DC power supply to ground"
                    ),
                    correctOption = 0,
                    findingExplanation = "The DMM registers 24.1V DC across the solenoid terminals when triggered, but the valve does not click or exhaust air. After de-energizing, coil resistance measures OPEN (infinite Ω)."
                ),
                DiagnosticTestStep(
                    stepTitle = "Step 3: Root Cause Isolation & Repair",
                    description = "Determine the final cause and perform corrective action according to postal safety standards.",
                    tool = "LOTO Equipment, Replacement Solenoid & Torque Screwdriver",
                    testOptions = listOf(
                        "Leave machine running and pull the solenoid wires with bare hands",
                        "Perform LOTO, bleed local pneumatic lockout valve, replace burned-out 24V DC solenoid coil, restore air and test cycle",
                        "Increase airline pressure from 85 psi to 160 psi to force the valve open",
                        "Bypass the pocket in software permanently"
                    ),
                    correctOption = 1,
                    findingExplanation = "The solenoid coil winding failed open. Safe repair requires electrical LOTO and pneumatic energy isolation, depressurizing the manifold, installing a new 24V DC coil, and verifying responsive actuation."
                )
            ),
            rootCauseSummary = "Open-circuit failure in the 24V DC pneumatic diverter solenoid coil (SOL-12).",
            preventativeAction = "Log part replacement in maintenance management system (MMS/EAM); verify coil surge-suppression diode is intact to prevent future inductive spike burnout."
        ),
        TroubleshootingCase(
            id = "case_motor_overload",
            machine = "AFCS (Advanced Facer Canceler System)",
            reportedSymptom = "Main induction drive motor stops abruptly after 15 minutes of operation. Start button will not restart the motor.",
            initialConditions = "The motor is hot to the touch. Control transformer fuse is intact. Machine status light shows FAULT.",
            steps = listOf(
                DiagnosticTestStep(
                    stepTitle = "Step 1: Motor Starter Overload Inspection",
                    description = "Open the motor control cabinet and examine the magnetic motor starter (contactor and thermal overload block).",
                    tool = "Visual Inspection & Infrared Thermometer",
                    testOptions = listOf(
                        "Press the red Emergency Stop button repeatedly",
                        "Check if the thermal overload relay reset button has tripped outward, and measure overload block temperature",
                        "Replace all 3 fuses with solid copper pipe",
                        "Turn on the building air handler"
                    ),
                    correctOption = 1,
                    findingExplanation = "The bimetallic thermal overload indicator has tripped open. The thermal block temperature is 145°F, confirming sustained overcurrent condition caused thermal expansion."
                ),
                DiagnosticTestStep(
                    stepTitle = "Step 2: Mechanical Freedom & Belt Check",
                    description = "Perform LOTO on the 480V disconnect. Manually inspect the mechanical drive train connected to the motor shaft.",
                    tool = "Hands-on rotation & feeler gauge",
                    testOptions = listOf(
                        "Attempt to rotate motor shaft and conveyor drive roller by hand with belt engaged",
                        "Hit the motor pulley with a 5 lb sledgehammer",
                        "Re-energize disconnect while holding the contactor in with a wooden stick",
                        "Loosen motor mounting bolts completely"
                    ),
                    correctOption = 0,
                    findingExplanation = "The motor shaft and driven roller are extremely stiff and seize intermittently. With drive belt removed, the motor spins freely, but the driven pillow block bearing on the conveyor roller is seized and full of dried, metal-flaked grease."
                ),
                DiagnosticTestStep(
                    stepTitle = "Step 3: Corrective Replacement & Re-commissioning",
                    description = "Restore the mechanical system to optimal operating condition.",
                    tool = "Bearing puller, replacement pillow block bearing, alignment straightedge",
                    testOptions = listOf(
                        "Spray penetrating lubricant on the seized bearing and force it to turn",
                        "Replace failed pillow-block roller bearing, inspect shaft for scoring, align sheaves with straightedge, tension V-belt, reset overload, and take full-load clamp-meter current reading",
                        "Increase thermal overload trip setting from 8A to 25A",
                        "Remove the belt and leave conveyor idle"
                    ),
                    correctOption = 1,
                    findingExplanation = "Replacing the seized bearing eliminates excessive mechanical drag. Sheave alignment and proper belt tension ensure smooth running. Clamp-on ammeter verifies motor current is now a steady 5.2A (well below 7.8A FLA rating)."
                )
            ),
            rootCauseSummary = "Mechanical seizure in the driven conveyor shaft pillow-block bearing caused excessive motor torque demand, drawing sustained overcurrent and tripping thermal overload.",
            preventativeAction = "Update PM lubrication route checklist to ensure high-speed pillow block bearings are regreased quarterly with proper polyurea postal-specified grease."
        ),
        TroubleshootingCase(
            id = "case_pneumatic_cylinder",
            machine = "Bulk Mail Sorter Pusher Arm",
            reportedSymptom = "Pneumatic parcel ejector arm extends very sluggishly under heavy parcels, failing to push packages off the sorter tray.",
            initialConditions = "System is pressurized. No major air leaks audible along the main header. Light packages push successfully.",
            steps = listOf(
                DiagnosticTestStep(
                    stepTitle = "Step 1: Dynamic Pressure Drop Measurement",
                    description = "Measure pneumatic pressure at the machine regulator during stationary idle vs active cylinder extension stroke.",
                    tool = "Pressure Gauge (0-150 psi)",
                    testOptions = listOf(
                        "Measure pressure: Idle = 90 psi, but drops to 35 psi during cylinder stroke",
                        "Measure resistance of the metal cylinder tube",
                        "Check ambient room temperature",
                        "Replace the parcel tray immediately"
                    ),
                    correctOption = 0,
                    findingExplanation = "The massive dynamic pressure drop (from 90 psi down to 35 psi) proves there is a severe flow restriction upstream of the cylinder manifold, starving the cylinder of volume (CFM) when demand occurs."
                ),
                DiagnosticTestStep(
                    stepTitle = "Step 2: Upstream Flow Restriction Isolation",
                    description = "Trace back from the cylinder valve through the FRL (Filter-Regulator-Lubricator) assembly to locate the restriction.",
                    tool = "Differential Pressure Indicator & Filter Inspection",
                    testOptions = listOf(
                        "Check the air filter bowl element for oil emulsion and particulate clogging",
                        "Turn the regulator knob completely clockwise to maximum lock",
                        "Drill a hole in the pneumatic receiver tank",
                        "Replace the electrical PLC processor"
                    ),
                    correctOption = 0,
                    findingExplanation = "The air filter sintered bronze element is heavily saturated with black oil varnish and water sludge from an aging facility compressor air dryer, causing a 55 psi pressure drop across the filter element under flow."
                ),
                DiagnosticTestStep(
                    stepTitle = "Step 3: Restoration & Verification",
                    description = "Perform corrective maintenance on the FRL and test parcel pusher under maximum package weight.",
                    tool = "Replacement filter cartridge, bowl cleaner, test weights",
                    testOptions = listOf(
                        "Remove the filter element completely and operate with no filtration",
                        "Replace filter element, clean bowl, check automatic drain float, verify steady 85 psi maintained during full stroke push",
                        "Pour motor oil directly into the airline hose",
                        "Reduce parcel weight limit to 1 pound"
                    ),
                    correctOption = 1,
                    findingExplanation = "Installing a clean filter element restores full CFM air flow. Pressure now stays rock solid at 85 psi throughout the push stroke, cleanly ejecting 70 lb parcels."
                )
            ),
            rootCauseSummary = "Clogged sintered bronze pneumatic filter element severely restricted compressed air volume (CFM), creating a massive dynamic pressure drop under load.",
            preventativeAction = "Schedule monthly inspection of pneumatic filter differential pressure indicators and verify plant refrigerated air dryer dewpoint is maintained at 38°F."
        )
    )
}
