package com.example.data

object QuestionBank {

    val allQuestions: List<Question> = listOf(
        // ==========================================
        // 1. ELECTRONICS & ELECTRICITY (Questions 1 - 20)
        // ==========================================
        Question(
            id = 1,
            category = Category.ELECTRONICS,
            crafts = listOf(CraftRole.MM_7, CraftRole.MPE_9, CraftRole.ET_10, CraftRole.BEM_9),
            questionText = "In a 240V AC circuit, a heater element draws 15 Amperes of current. What is the power consumed by the heater and its internal resistance?",
            options = listOf(
                "Power = 3,600 W, Resistance = 16 Ω",
                "Power = 1,800 W, Resistance = 32 Ω",
                "Power = 7,200 W, Resistance = 8 Ω",
                "Power = 3,600 W, Resistance = 24 Ω"
            ),
            correctIndex = 0,
            explanation = "Using Ohm's Law and Power equations: Power P = V × I = 240V × 15A = 3,600 Watts (3.6 kW). Resistance R = V / I = 240V / 15A = 16 Ω. Alternatively, P = I²R = 15² × 16 = 3,600 W.",
            formulaOrRule = "P = V × I; R = V / I"
        ),
        Question(
            id = 2,
            category = Category.ELECTRONICS,
            crafts = listOf(CraftRole.ET_10, CraftRole.MPE_9),
            questionText = "Which logic gate outputs a LOW (0) ONLY when ALL of its inputs are HIGH (1)?",
            options = listOf("NOR gate", "NAND gate", "XOR gate", "AND gate"),
            correctIndex = 1,
            explanation = "A NAND (NOT-AND) gate produces a HIGH output if any of its inputs are LOW. It only outputs a LOW when every input is HIGH (truth table: 1,1 -> 0; all other combinations -> 1).",
            formulaOrRule = "NAND: Y = NOT(A • B)"
        ),
        Question(
            id = 3,
            category = Category.ELECTRONICS,
            crafts = listOf(CraftRole.ET_10, CraftRole.MPE_9, CraftRole.MM_7),
            questionText = "A 3-phase, 480V squirrel-cage induction motor trips its thermal overload relay repeatedly after running for 20 minutes. A clamp meter measures currents of 18A on L1, 18A on L2, and 0A on L3. What is this condition called and what is the primary risk?",
            options = listOf(
                "Ground fault; risk of arc flash explosion",
                "Single-phasing; remaining active windings will overheat and burn out",
                "Reverse phase rotation; motor running backward",
                "Overvoltage surge; capacitor breakdown"
            ),
            correctIndex = 1,
            explanation = "When one phase of a three-phase supply is lost (0A on L3), the condition is termed 'single-phasing'. If already rotating, the motor will continue to turn but will draw excessive current through the remaining two phases, causing extreme heating in the stator windings and tripping thermal overloads.",
            formulaOrRule = "Single-phasing: Stator coil overheating"
        ),
        Question(
            id = 4,
            category = Category.ELECTRONICS,
            crafts = listOf(CraftRole.ET_10, CraftRole.MPE_9, CraftRole.BEM_9),
            questionText = "When measuring motor insulation resistance to ground with a Megohmmeter (Megger) on a 480V motor, what is the standard test voltage and minimum acceptable resistance according to IEEE guidelines?",
            options = listOf(
                "50V DC test; minimum 100 Ω",
                "500V or 1,000V DC test; minimum 1.0 MΩ (Megohm)",
                "24V AC test; minimum 10,000 Ω",
                "120V AC test; minimum 50 kΩ"
            ),
            correctIndex = 1,
            explanation = "For industrial equipment rated between 460V and 600V, IEEE 43 recommends applying 500V DC or 1,000V DC test voltage. The minimum rule of thumb is 1 Megohm plus 1 Megohm per kV of rated machine voltage.",
            formulaOrRule = "IEEE 43: Test voltage 500V/1000V DC, Min 1 MΩ"
        ),
        Question(
            id = 5,
            category = Category.ELECTRONICS,
            crafts = listOf(CraftRole.MM_7, CraftRole.MPE_9, CraftRole.BEM_9, CraftRole.ET_10),
            questionText = "Before performing any maintenance inside a mail sorter electrical cabinet, which step in OSHA Lockout/Tagout (LOTO) 1910.147 is the ultimate verification of a zero energy state?",
            options = listOf(
                "Turning off the local control toggle switch on the panel",
                "Verifying absence of voltage with a calibrated multimeter tested before and after (live-dead-live method)",
                "Placing a warning sign on the main hallway door",
                "Opening the disconnect handle and listening for motor hum"
            ),
            correctIndex = 1,
            explanation = "Verification of Isolation requires the three-point / live-dead-live test: 1) Test meter on a known live source, 2) Test across all phases and phase-to-ground of de-energized circuits, 3) Test meter again on known live source to verify meter integrity.",
            formulaOrRule = "LOTO Live-Dead-Live 3-Point Meter Test"
        ),
        Question(
            id = 6,
            category = Category.ELECTRONICS,
            crafts = listOf(CraftRole.ET_10, CraftRole.MPE_9),
            questionText = "Three resistors of 10 Ω, 20 Ω, and 30 Ω are connected in PARALLEL across a 60V DC supply. What is the total equivalent resistance and total current drawn?",
            options = listOf(
                "Total R = 60 Ω, Total I = 1 A",
                "Total R = 5.45 Ω, Total I = 11 A",
                "Total R = 15 Ω, Total I = 4 A",
                "Total R = 6.67 Ω, Total I = 9 A"
            ),
            correctIndex = 1,
            explanation = "For parallel resistors: 1/Req = 1/R1 + 1/R2 + 1/R3 = 1/10 + 1/20 + 1/30 = 6/60 + 3/60 + 2/60 = 11/60. Req = 60/11 ≈ 5.45 Ω. Total current I = V / Req = 60V / (60/11) = 11 Amperes (or I1 = 60/10=6A, I2 = 60/20=3A, I3 = 60/30=2A -> 6+3+2 = 11A).",
            formulaOrRule = "1/Req = 1/R1 + 1/R2 + 1/R3; I_total = ∑ I_branches"
        ),
        Question(
            id = 7,
            category = Category.ELECTRONICS,
            crafts = listOf(CraftRole.ET_10),
            questionText = "What is the function of a freewheeling (flyback) diode connected in reverse-bias across an inductive DC relay coil?",
            options = listOf(
                "To increase the pull-in speed of the relay armature",
                "To suppress high-voltage inductive kickback (counter-EMF) when the coil is de-energized, protecting the switching transistor",
                "To rectify AC line ripple into DC voltage",
                "To reduce the continuous operating current of the coil"
            ),
            correctIndex = 1,
            explanation = "When current through an inductor is suddenly interrupted (dI/dt), the collapsing magnetic field creates a huge reverse-polarity voltage spike (V = -L dI/dt). A reverse-biased flyback diode provides a safe discharge path for this inductive kickback, protecting solid-state drivers.",
            formulaOrRule = "V_ind = -L (dI/dt); Flyback suppression"
        ),
        Question(
            id = 8,
            category = Category.ELECTRONICS,
            crafts = listOf(CraftRole.ET_10, CraftRole.MPE_9),
            questionText = "What binary value is equivalent to hexadecimal number 0x2F?",
            options = listOf("0010 1111", "0011 1110", "0010 1101", "0001 1111"),
            correctIndex = 0,
            explanation = "Hexadecimal digit '2' in 4-bit binary is 0010. Hexadecimal digit 'F' (decimal 15) in binary is 1111 (8+4+2+1). Combining the nibbles gives 0010 1111.",
            formulaOrRule = "Hex '2' = 0010, Hex 'F' = 1111 -> 0010 1111"
        ),
        Question(
            id = 9,
            category = Category.ELECTRONICS,
            crafts = listOf(CraftRole.ET_10, CraftRole.MPE_9),
            questionText = "An AC inductor has an inductance of 0.2 Henries. Connected to a 60 Hz AC power source, what is its inductive reactance (X_L)?",
            options = listOf("12.0 Ω", "75.4 Ω", "37.7 Ω", "150.8 Ω"),
            correctIndex = 1,
            explanation = "Inductive reactance formula: X_L = 2π f L = 2 × 3.1416 × 60 × 0.2 ≈ 75.4 Ω.",
            formulaOrRule = "X_L = 2π f L"
        ),
        Question(
            id = 10,
            category = Category.ELECTRONICS,
            crafts = listOf(CraftRole.ET_10, CraftRole.BEM_9),
            questionText = "In a standard step-down control transformer with a 480V primary winding and a 120V secondary winding, if the secondary connects to a load drawing 4 Amps, what is the primary current (assuming 100% efficiency)?",
            options = listOf("16 A", "1.0 A", "4.0 A", "0.5 A"),
            correctIndex = 1,
            explanation = "Turns ratio Np/Ns = Vp/Vs = 480/120 = 4:1. For ideal transformers, power in = power out: Vp × Ip = Vs × Is -> 480 × Ip = 120 × 4 = 480 VA -> Ip = 1.0 Amp.",
            formulaOrRule = "Vp / Vs = Is / Ip"
        ),
        Question(
            id = 11,
            category = Category.ELECTRONICS,
            crafts = listOf(CraftRole.ET_10, CraftRole.MPE_9),
            questionText = "Which type of digital flip-flop has a single clock input and an active input that causes the output to invert (toggle) on every clock pulse when the input is HIGH?",
            options = listOf("D flip-flop", "T (Toggle) flip-flop", "RS latch", "Schmitt trigger"),
            correctIndex = 1,
            explanation = "A T (Toggle) flip-flop complements its state whenever T = 1 on an active clock edge. When T = 0, it holds its current state.",
            formulaOrRule = "T Flip-Flop: Q_next = Q' when T=1"
        ),
        Question(
            id = 12,
            category = Category.ELECTRONICS,
            crafts = listOf(CraftRole.ET_10, CraftRole.MM_7),
            questionText = "When testing a silicon rectifier diode out of circuit with a digital multimeter in diode test mode, what readings indicate a normal, functioning diode?",
            options = listOf(
                "Forward bias: 0.5V to 0.7V drop; Reverse bias: 'OL' (open loop/infinite)",
                "Forward bias: 0.00V; Reverse bias: 0.00V",
                "Forward bias: 'OL'; Reverse bias: 'OL'",
                "Forward bias: 100 kΩ; Reverse bias: 100 kΩ"
            ),
            correctIndex = 0,
            explanation = "A good silicon PN junction exhibits a forward voltage drop between 0.5V and 0.7V (germanium is 0.2V-0.3V). In reverse bias, it should block current completely, reading 'OL' on the meter.",
            formulaOrRule = "Silicon diode forward drop ≈ 0.6V to 0.7V"
        ),
        Question(
            id = 13,
            category = Category.ELECTRONICS,
            crafts = listOf(CraftRole.ET_10, CraftRole.MPE_9),
            questionText = "A Variable Frequency Drive (VFD) controls an AC induction motor's speed primarily by varying which two electrical parameters simultaneously?",
            options = listOf(
                "Voltage and Frequency in a constant V/Hz ratio",
                "Amperage and Resistance",
                "Capacitance and Inductance",
                "DC Bus ripple and Rotor slip"
            ),
            correctIndex = 0,
            explanation = "VFDs adjust the motor speed by varying the frequency of the stator magnetic field. To maintain constant motor flux and rated torque without magnetic saturation or overheating, the voltage is varied proportionally with frequency (constant Volts-per-Hertz ratio).",
            formulaOrRule = "VFD: Constant V/Hz ratio preserves magnetic flux"
        ),
        Question(
            id = 14,
            category = Category.ELECTRONICS,
            crafts = listOf(CraftRole.ET_10, CraftRole.MPE_9),
            questionText = "What is the primary difference between an NPN bipolar junction transistor (BJT) and an N-channel MOSFET?",
            options = listOf(
                "BJTs are current-controlled devices, while MOSFETs are voltage-controlled devices with insulated gates",
                "BJTs can only handle AC, while MOSFETs can only handle DC",
                "MOSFETs require high gate current to remain in saturation",
                "BJTs have no collector-emitter saturation voltage"
            ),
            correctIndex = 0,
            explanation = "BJT conduction is governed by base current (Ib controlling Ic), making it a current-controlled device. MOSFETs feature an insulated silicon dioxide gate that draws virtually zero steady-state DC current, making them voltage-controlled devices (Vgs controls Id).",
            formulaOrRule = "BJT = Current-controlled; MOSFET = Voltage-controlled"
        ),
        Question(
            id = 15,
            category = Category.ELECTRONICS,
            crafts = listOf(CraftRole.ET_10, CraftRole.MPE_9, CraftRole.MM_7),
            questionText = "In a standard start/stop 3-wire motor control circuit, which component maintains power to the contactor coil after the momentary START pushbutton is released?",
            options = listOf(
                "The overload relay heater",
                "The normally open (N.O.) auxiliary contact on the contactor (holding/seal-in contact) wired in parallel with the START button",
                "The normally closed STOP pushbutton",
                "The main line fuse"
            ),
            correctIndex = 1,
            explanation = "A 3-wire control circuit uses a normally open auxiliary contact on the contactor (often terminal 2 to 3) wired in parallel with the momentary START push-button. When energized, this contact closes and 'seals in' or latches the circuit.",
            formulaOrRule = "3-Wire Motor Control: Auxiliary Seal-In Contact"
        ),
        Question(
            id = 16,
            category = Category.ELECTRONICS,
            crafts = listOf(CraftRole.ET_10, CraftRole.MPE_9),
            questionText = "An oscilloscope displays a sinusoidal voltage waveform with a peak-to-peak voltage of 340V. What is the approximate RMS voltage?",
            options = listOf("340V", "120V", "240V", "170V"),
            correctIndex = 1,
            explanation = "Peak voltage Vp = Vp-p / 2 = 340V / 2 = 170V. For a sine wave, Vrms = Vp × 0.7071 ≈ 170 × 0.7071 ≈ 120.2V RMS.",
            formulaOrRule = "Vrms = (Vp-p / 2) × 0.7071"
        ),
        Question(
            id = 17,
            category = Category.ELECTRONICS,
            crafts = listOf(CraftRole.ET_10, CraftRole.MPE_9),
            questionText = "What is the equivalent resistance of a 100 μF capacitor and a 200 μF capacitor connected in PARALLEL?",
            options = listOf("66.7 μF", "300 μF", "150 μF", "20,000 μF"),
            correctIndex = 1,
            explanation = "Capacitors in parallel add directly: C_total = C1 + C2 = 100 μF + 200 μF = 300 μF. (Unlike resistors in parallel, parallel capacitors increase plate area and total capacitance).",
            formulaOrRule = "C_parallel = C1 + C2"
        ),
        Question(
            id = 18,
            category = Category.ELECTRONICS,
            crafts = listOf(CraftRole.ET_10, CraftRole.MPE_9),
            questionText = "Which logic circuit accepts binary inputs and activates one unique output line corresponding to the decimal equivalent of the binary code?",
            options = listOf("Multiplexer (MUX)", "Decoder", "Full Adder", "Shift Register"),
            correctIndex = 1,
            explanation = "A binary decoder (e.g. 2-to-4 or 3-to-8 decoder) converts an n-bit binary code on its inputs into a single active output among 2^n possible output lines.",
            formulaOrRule = "Decoder: n inputs -> 2^n unique outputs"
        ),
        Question(
            id = 19,
            category = Category.ELECTRONICS,
            crafts = listOf(CraftRole.ET_10, CraftRole.BEM_9),
            questionText = "A high-voltage power factor correction capacitor bank has been disconnected from the line. How long must you wait and what precaution must be taken before touching the terminals?",
            options = listOf(
                "Touch immediately since disconnecting AC eliminates all charge",
                "Wait at least 5 minutes for internal bleed resistors to discharge, then verify with a meter and apply a discharge grounding wand",
                "Submerge the capacitor in water to dissipate charge",
                "Short the terminals with a screwdriver while standing on a wet floor"
            ),
            correctIndex = 1,
            explanation = "Capacitors store dangerous lethal DC energy long after line disconnection. Standard safety protocols require waiting 5 minutes for discharge resistors to drop voltage below 50V, followed by testing with a calibrated meter and applying a grounded discharge lead.",
            formulaOrRule = "Capacitor Safety: Bleeder verification + ground wand"
        ),
        Question(
            id = 20,
            category = Category.ELECTRONICS,
            crafts = listOf(CraftRole.ET_10, CraftRole.MPE_9),
            questionText = "What is the output voltage of a full-wave bridge rectifier circuit supplied with 24V RMS AC, assuming ideal silicon diodes with 0.7V forward drops?",
            options = listOf("24.0V DC peak", "32.5V DC peak", "33.9V DC peak", "12.0V DC peak"),
            correctIndex = 1,
            explanation = "Peak AC input = 24V × √2 ≈ 24 × 1.414 = 33.94V. In a bridge rectifier, current conducts through two series diodes simultaneously, dropping 2 × 0.7V = 1.4V. V_peak_out = 33.94V - 1.4V = 32.54V.",
            formulaOrRule = "V_peak_out = (V_rms × √2) - (2 × V_diode)"
        ),

        // ==========================================
        // 2. MECHANICS & POWER TRANSMISSION (Questions 21 - 40)
        // ==========================================
        Question(
            id = 21,
            category = Category.MECHANICAL,
            crafts = listOf(CraftRole.MM_7, CraftRole.MPE_9, CraftRole.BEM_9),
            questionText = "A driving gear has 15 teeth and rotates at 1,800 RPM. It meshes directly with a driven gear having 45 teeth. What is the speed of the driven gear and the gear ratio?",
            options = listOf(
                "Speed = 600 RPM, Gear Ratio = 3:1 (reduction)",
                "Speed = 5,400 RPM, Gear Ratio = 1:3 (overdrive)",
                "Speed = 1,200 RPM, Gear Ratio = 1.5:1",
                "Speed = 900 RPM, Gear Ratio = 2:1"
            ),
            correctIndex = 0,
            explanation = "Gear Ratio = Driven Teeth / Driver Teeth = 45 / 15 = 3:1. Driven RPM = Driver RPM / Gear Ratio = 1,800 / 3 = 600 RPM. Larger gear turns slower but produces 3 times more torque.",
            formulaOrRule = "GR = N_driven / N_driver = RPM_in / RPM_out"
        ),
        Question(
            id = 22,
            category = Category.MECHANICAL,
            crafts = listOf(CraftRole.MM_7, CraftRole.MPE_9),
            questionText = "When inspecting a newly installed roller chain drive on a sorting conveyor, what is the proper amount of chain slack (sag) measured midway between sprockets?",
            options = listOf(
                "Zero sag; the chain should be drum-tight with no deflection",
                "Approximately 2% to 3% of the sprocket center-to-center distance",
                "15% to 20% of the center-to-center distance",
                "At least 2 full inches regardless of span"
            ),
            correctIndex = 1,
            explanation = "Standard mechanical practice for horizontal roller chain drives prescribes total mid-span deflection (slack) of approximately 2% to 3% of the center-to-center shaft distance. Excessive tightness causes rapid bearing and pin wear, while excessive sag causes chain whip and sprocket climbing.",
            formulaOrRule = "Chain sag = 2% - 3% of center distance"
        ),
        Question(
            id = 23,
            category = Category.MECHANICAL,
            crafts = listOf(CraftRole.MM_7, CraftRole.MPE_9, CraftRole.BEM_9),
            questionText = "An SAE Grade 8 hex head bolt can be identified by which markings on the bolt head?",
            options = listOf(
                "No radial lines on the head",
                "3 radial lines spaced at 120 degrees",
                "6 radial lines spaced around the perimeter",
                "The stamped numbers '4.8'"
            ),
            correctIndex = 2,
            explanation = "In the SAE bolt grading standard: Grade 2 has no head markings (minimum tensile 74 ksi); Grade 5 has 3 radial lines (120 ksi); Grade 8 has 6 radial lines (150 ksi, high strength alloy steel). Metric bolts use numbers like 8.8 or 10.9.",
            formulaOrRule = "SAE Grade 8: 6 radial lines; Grade 5: 3 radial lines"
        ),
        Question(
            id = 24,
            category = Category.MECHANICAL,
            crafts = listOf(CraftRole.MM_7, CraftRole.MPE_9, CraftRole.BEM_9),
            questionText = "What is the primary cause of bearing 'brinelling' (regularly spaced indents in the bearing raceway matching the roller spacing)?",
            options = listOf(
                "Static overload or severe mechanical impact forces when the bearing was stationary",
                "Chemical corrosion from acid vapor",
                "Normal fatigue wear after 100,000 hours of smooth operation",
                "Electric current arcing from welding ground clamps"
            ),
            correctIndex = 0,
            explanation = "True brinelling is permanent plastic deformation of raceways caused by heavy static load or impact pressing balls/rollers into the metal. 'False brinelling' looks similar but is caused by slight oscillatory vibration/micro-fretting while stationary.",
            formulaOrRule = "Brinelling: Plastic indentations from static shock/overload"
        ),
        Question(
            id = 25,
            category = Category.MECHANICAL,
            crafts = listOf(CraftRole.MM_7, CraftRole.BEM_9),
            questionText = "A motor and centrifugal pump shaft are being aligned. What condition exists when one of the motor's four mounting feet does not sit firmly on the baseplate prior to tightening?",
            options = listOf(
                "Axial end float",
                "Soft foot (which introduces mechanical stress and shaft distortion when torqued down)",
                "Dynamic unbalance",
                "Cavitation runout"
            ),
            correctIndex = 1,
            explanation = "Soft foot occurs when machine feet do not rest evenly on the soleplate/shims (like a wobbly chair). Tightening hold-down bolts on a soft foot bends the motor frame, distorts the bearing housings, and ruins precision shaft alignment.",
            formulaOrRule = "Soft foot: Checked with 0.002\" feeler gauge or dial indicator"
        ),
        Question(
            id = 26,
            category = Category.MECHANICAL,
            crafts = listOf(CraftRole.MM_7, CraftRole.MPE_9),
            questionText = "In a multi-pulley block and tackle system with 4 supporting rope strands holding a suspended 800 lb load, what pulling force (effort) is required to hold the load (ignoring friction)?",
            options = listOf("800 lbs", "400 lbs", "200 lbs", "100 lbs"),
            correctIndex = 2,
            explanation = "Mechanical Advantage (MA) equals the number of rope strands directly supporting the movable load pulley. Here MA = 4. Ideal Effort = Load / MA = 800 lbs / 4 = 200 lbs.",
            formulaOrRule = "Effort = Load / Supporting Strands = 800 / 4 = 200 lbs"
        ),
        Question(
            id = 27,
            category = Category.MECHANICAL,
            crafts = listOf(CraftRole.MM_7, CraftRole.MPE_9),
            questionText = "When installing multiple V-belts on a multi-groove sheave drive, what is a mandatory installation practice?",
            options = listOf(
                "Use a pry bar or large screwdriver to roll the belts over the sheave edges",
                "Always install belts in manufacturer-matched sets with identical length codes and loosen motor mount center distance first",
                "Mix old worn belts with new belts to equalize tension",
                "Apply heavy petroleum axle grease to belt flanks to prevent squeal"
            ),
            correctIndex = 1,
            explanation = "Belts must never be forced over grooves with a tool (which breaks internal tensile cords). Loosen center distance, install a matched set (same manufacturing lot/length code), and tension properly.",
            formulaOrRule = "V-Belts: Matched sets only; never pry over sheaves"
        ),
        Question(
            id = 28,
            category = Category.MECHANICAL,
            crafts = listOf(CraftRole.MM_7, CraftRole.MPE_9),
            questionText = "A motor spinning at 1,750 RPM is equipped with a 4-inch diameter sheave. It is belt-connected to a sorting conveyor drive sheave of 12 inches diameter. What is the conveyor shaft speed?",
            options = listOf("583 RPM", "5,250 RPM", "875 RPM", "1,200 RPM"),
            correctIndex = 0,
            explanation = "D_driver × RPM_driver = D_driven × RPM_driven -> 4 in × 1,750 RPM = 12 in × RPM_driven -> RPM_driven = 7,000 / 12 ≈ 583.3 RPM.",
            formulaOrRule = "D1 × RPM1 = D2 × RPM2"
        ),
        Question(
            id = 29,
            category = Category.MECHANICAL,
            crafts = listOf(CraftRole.MM_7, CraftRole.MPE_9),
            questionText = "What type of bearing is specifically designed to handle heavy thrust (axial) loads parallel to the shaft, rather than radial loads perpendicular to the shaft?",
            options = listOf(
                "Standard deep groove ball bearing",
                "Thrust ball bearing or tapered roller thrust bearing",
                "Needle sleeve bearing",
                "Straight cylindrical roller bearing"
            ),
            correctIndex = 1,
            explanation = "Thrust bearings are configured to support axial loads along the shaft centerline. Cylindrical roller bearings and needle bearings support pure radial loads, while deep-groove ball bearings support light combined loads.",
            formulaOrRule = "Thrust Bearing: Carries axial forces along shaft axis"
        ),
        Question(
            id = 30,
            category = Category.MECHANICAL,
            crafts = listOf(CraftRole.MM_7, CraftRole.BEM_9),
            questionText = "What precision measuring instrument is primarily used to measure the pitch diameter of external screw threads or internal bearing bore clearance accurately to within 0.0001 inch?",
            options = listOf(
                "Dial Caliper",
                "Micrometer (external or internal bore micrometer)",
                "Steel machinist rule",
                "Thread pitch gauge"
            ),
            correctIndex = 1,
            explanation = "Micrometers offer standard 0.0001 inch (tenth of a thousandth) precision for outside diameters, bore diameters, and thread pitch measuring. Dial calipers typically read to 0.001 inch.",
            formulaOrRule = "Precision Measurement: Vernier Micrometer (0.0001\")"
        ),
        Question(
            id = 31,
            category = Category.MECHANICAL,
            crafts = listOf(CraftRole.MM_7, CraftRole.MPE_9),
            questionText = "In a compound gear train, gear A (20 teeth) drives gear B (60 teeth). Mounted on the same intermediate shaft as gear B is gear C (15 teeth), which meshes with final gear D (45 teeth). What is the total reduction ratio from shaft A to shaft D?",
            options = listOf("3:1", "9:1", "6:1", "4.5:1"),
            correctIndex = 1,
            explanation = "First stage ratio = 60 / 20 = 3:1. Second stage ratio = 45 / 15 = 3:1. Total gear ratio is the product of intermediate ratios: Total GR = 3 × 3 = 9:1 reduction.",
            formulaOrRule = "Total GR = GR1 × GR2 = (60/20) × (45/15) = 9:1"
        ),
        Question(
            id = 32,
            category = Category.MECHANICAL,
            crafts = listOf(CraftRole.MM_7, CraftRole.MPE_9),
            questionText = "Which mechanical power transmission element is non-slip, does not stretch like rubber, and is capable of transferring high torque at exact rotational synchronization?",
            options = listOf("V-belt", "Flat canvas belt", "Timing belt (toothed synchronous belt) or roller chain", "O-ring polyurethane belt"),
            correctIndex = 2,
            explanation = "Timing belts (synchronous belts) utilize molded teeth that mate into grooved pulleys to deliver positive, non-slip power transmission with zero slippage and exact shaft timing.",
            formulaOrRule = "Synchronous Timing Belts: Non-slip positive engagement"
        ),
        Question(
            id = 33,
            category = Category.MECHANICAL,
            crafts = listOf(CraftRole.MM_7, CraftRole.MPE_9),
            questionText = "When torqueing down a critical 8-bolt circular flange on a conveyor drive gearbox, what bolt tightening sequence should always be followed?",
            options = listOf(
                "Tighten sequentially in a clockwise circular circle",
                "Criss-cross (star pattern) in progressive incremental torque steps (e.g. 30%, 70%, 100%)",
                "Tighten one bolt fully to 100% torque before starting the next",
                "Finger tighten all and leave loose"
            ),
            correctIndex = 1,
            explanation = "Flanges and bearing covers must always be torqued in a criss-cross (star/cross) pattern in progressive stages (e.g., snug, 50%, 100%) to ensure uniform gasket compression and avoid flange warping.",
            formulaOrRule = "Torque Pattern: Criss-cross star pattern in progressive stages"
        ),
        Question(
            id = 34,
            category = Category.MECHANICAL,
            crafts = listOf(CraftRole.MM_7, CraftRole.BEM_9),
            questionText = "What is the primary consequence of over-greasing an electric motor's high-speed anti-friction ball bearings?",
            options = listOf(
                "Excess grease causes churning, rapid friction overheating, seal blowout, and motor winding contamination",
                "The bearing turns too easily and overspeeds the motor",
                "The grease turns into mineral oil and leaks out harmlessly",
                "Over-greasing increases bearing lifespan indefinitely"
            ),
            correctIndex = 0,
            explanation = "Over-greasing causes the rolling elements to constantly churn through excess grease, generating intense internal fluid friction and heat, rupturing bearing shields, and forcing grease into the motor windings.",
            formulaOrRule = "Bearing Lubrication: Over-greasing causes thermal overheating"
        ),
        Question(
            id = 35,
            category = Category.MECHANICAL,
            crafts = listOf(CraftRole.MM_7, CraftRole.MPE_9),
            questionText = "A worm gear drive has a single-thread worm driving a 40-tooth worm wheel. What is the gear ratio and what is its special mechanical property?",
            options = listOf(
                "Ratio is 40:1; it is self-locking (cannot be back-driven by the load)",
                "Ratio is 1:40; it operates as an overdrive speed increaser",
                "Ratio is 20:1; it has 99% mechanical efficiency",
                "Ratio is 4:1; it requires no lubrication"
            ),
            correctIndex = 0,
            explanation = "For a single-lead worm, each revolution advances the gear by 1 tooth, giving a 40:1 ratio. Because the friction angle exceeds the lead angle, the load cannot drive the worm backwards (self-locking holding capability).",
            formulaOrRule = "Worm Gear: High single-stage reduction + Self-locking"
        ),
        Question(
            id = 36,
            category = Category.MECHANICAL,
            crafts = listOf(CraftRole.MM_7, CraftRole.BEM_9),
            questionText = "What type of shaft misalignment occurs when two shaft centerlines are parallel to each other but do not lie in the same straight line plane?",
            options = listOf("Angular misalignment", "Parallel (offset) misalignment", "Axial end play", "Torsional vibration"),
            correctIndex = 1,
            explanation = "Parallel offset misalignment occurs when the rotational axes of two connected shafts are parallel but offset by a measurable distance. Angular misalignment occurs when shafts intersect at an angle.",
            formulaOrRule = "Shaft Alignment: Parallel offset vs. Angular angle"
        ),
        Question(
            id = 37,
            category = Category.MECHANICAL,
            crafts = listOf(CraftRole.MM_7, CraftRole.MPE_9),
            questionText = "A technician applies 50 lbs of force at the end of a 1.5-foot long torque wrench. What torque is applied to the fastener?",
            options = listOf("33.3 lb-ft", "75 lb-ft", "100 lb-ft", "50 lb-ft"),
            correctIndex = 1,
            explanation = "Torque = Force × Distance = 50 lbs × 1.5 ft = 75 lb-ft (or 900 lb-in).",
            formulaOrRule = "Torque = Force × Distance"
        ),
        Question(
            id = 38,
            category = Category.MECHANICAL,
            crafts = listOf(CraftRole.MM_7, CraftRole.MPE_9),
            questionText = "Which hand tool is designed specifically for removing internal or external snap rings (retaining rings) from shafts or housing grooves?",
            options = listOf("Needle-nose pliers", "Circlip / Retaining Ring pliers", "Water pump pliers", "Vice grips"),
            correctIndex = 1,
            explanation = "Retaining ring (circlip) pliers feature specialized hardened cylindrical pin tips that insert into the eyelets of snap rings to expand (external) or contract (internal) them safely.",
            formulaOrRule = "Snap Ring Pliers: Internal/External circlip tips"
        ),
        Question(
            id = 39,
            category = Category.MECHANICAL,
            crafts = listOf(CraftRole.MM_7, CraftRole.MPE_9),
            questionText = "On an ANSI standard #50 roller chain, what is the chain pitch (center-to-center distance between adjacent link pins)?",
            options = listOf("3/8 inch", "1/2 inch", "5/8 inch", "3/4 inch"),
            correctIndex = 2,
            explanation = "In ANSI roller chain numbering, the first digit represents the pitch in eighths of an inch. For #50 chain, pitch = 5/8 inch (0.625\"). For #40 chain, pitch = 4/8 = 1/2 inch.",
            formulaOrRule = "ANSI Chain Pitch = (First Digit) / 8 inches"
        ),
        Question(
            id = 40,
            category = Category.MECHANICAL,
            crafts = listOf(CraftRole.MM_7, CraftRole.BEM_9),
            questionText = "What is the primary function of a flexible shaft coupling (such as a Lovejoy jaw coupling with an elastomeric spider insert)?",
            options = listOf(
                "To increase shaft rotational speed by 20%",
                "To accommodate minor shaft misalignment, dampen torsional vibration, and isolate shock loads between driver and driven equipment",
                "To act as a solid permanent weld",
                "To eliminate the need for equipment hold-down bolts"
            ),
            correctIndex = 1,
            explanation = "Flexible couplings absorb slight angular, parallel, and axial misalignments while cushioning mechanical shock and vibration between motors and driven pumps/sorters.",
            formulaOrRule = "Flexible Coupling: Misalignment accommodation & shock dampening"
        ),

        // ==========================================
        // 3. FLUID POWER: HYDRAULICS & PNEUMATICS (Questions 41 - 60)
        // ==========================================
        Question(
            id = 41,
            category = Category.FLUID_POWER,
            crafts = listOf(CraftRole.MPE_9, CraftRole.BEM_9, CraftRole.MM_7),
            questionText = "According to Pascal's Law, if 100 psi of pressure is supplied to a hydraulic cylinder with a piston diameter of 4 inches, what is the theoretical thrust force generated on extension?",
            options = listOf("400 lbs", "1,257 lbs", "800 lbs", "314 lbs"),
            correctIndex = 1,
            explanation = "Piston Area A = π × r² = π × (2 in)² ≈ 3.1416 × 4 = 12.57 sq inches. Force F = Pressure × Area = 100 psi × 12.57 sq in = 1,257 lbs.",
            formulaOrRule = "F = P × A; Area = π × (d/2)²"
        ),
        Question(
            id = 42,
            category = Category.FLUID_POWER,
            crafts = listOf(CraftRole.MPE_9, CraftRole.MM_7),
            questionText = "In a compressed air distribution system supplying DBCS sorting machines, what three components make up the standard 'FRL' unit located at machine inlets?",
            options = listOf(
                "Flowmeter, Resistor, Limit switch",
                "Filter, Regulator, Lubricator",
                "Fuse, Relay, Latch",
                "Fluid cooler, Radiator, Line drier"
            ),
            correctIndex = 1,
            explanation = "An FRL unit consists of: 1) Filter (removes water condensate, rust, particulate), 2) Regulator (maintains constant downstream operating pressure), 3) Lubricator (injects metered oil mist for pneumatic valves/cylinders).",
            formulaOrRule = "FRL: Filter -> Regulator -> Lubricator"
        ),
        Question(
            id = 43,
            category = Category.FLUID_POWER,
            crafts = listOf(CraftRole.BEM_9, CraftRole.MPE_9),
            questionText = "What is the primary function of a nitrogen-charged hydraulic accumulator in an industrial hydraulic power unit?",
            options = listOf(
                "To chill hydraulic fluid below freezing",
                "To store pressurized hydraulic fluid, absorb pressure spikes/water hammer, and supply supplemental flow during peak demand",
                "To aerate the hydraulic reservoir",
                "To prevent electric current from flowing to the oil filter"
            ),
            correctIndex = 1,
            explanation = "Accumulators use compressible pre-charged nitrogen gas separated by a bladder/piston to store non-compressible hydraulic fluid under pressure. They absorb hydraulic shocks, dampen pump pulsations, and provide emergency power.",
            formulaOrRule = "Accumulator: Energy storage & hydraulic shock dampening"
        ),
        Question(
            id = 44,
            category = Category.FLUID_POWER,
            crafts = listOf(CraftRole.MPE_9, CraftRole.ET_10),
            questionText = "A 4-way, 3-position (4/3) directional control valve with a 'closed center' spool is de-energized. What occurs to the connected double-acting hydraulic cylinder?",
            options = listOf(
                "Both ports are vented to tank, allowing the cylinder rod to float freely",
                "Full pump flow is directed directly to the rod end, causing rapid retraction",
                "All fluid ports are blocked, locking the cylinder in place hydraulically",
                "The pump pressure relief valve opens to full bypass"
            ),
            correctIndex = 2,
            explanation = "In a closed-center spool valve, when spring-centered to the neutral/off position, ports P (pump), T (tank), A, and B (cylinder work ports) are all blocked. Fluid cannot enter or leave either side of the cylinder, holding it locked in position.",
            formulaOrRule = "Closed Center: Ports P, T, A, B all blocked in neutral"
        ),
        Question(
            id = 45,
            category = Category.FLUID_POWER,
            crafts = listOf(CraftRole.MPE_9, CraftRole.MM_7),
            questionText = "What is the primary difference in compressibility between hydraulic fluid (oil) and pneumatic fluid (compressed air)?",
            options = listOf(
                "Hydraulic fluid is essentially incompressible, while compressed air is highly compressible and springy",
                "Compressed air is completely incompressible",
                "Hydraulic oil compresses up to 80% under standard operating pressure",
                "Both fluids have identical bulk modulus compressibility"
            ),
            correctIndex = 0,
            explanation = "Liquids are nearly incompressible, giving hydraulic systems rigid, high-force precision positioning. Gases are highly compressible, giving pneumatic actuators rapid acceleration and spongy, cushioning characteristics.",
            formulaOrRule = "Fluid Physics: Liquids incompressible; Gases compressible"
        ),
        Question(
            id = 46,
            category = Category.FLUID_POWER,
            crafts = listOf(CraftRole.BEM_9, CraftRole.MPE_9),
            questionText = "What critical safety component must be installed directly on a compressed air receiver storage tank?",
            options = listOf(
                "An ASME-rated pressure relief safety valve and a bottom moisture condensate drain valve",
                "An electric heating element",
                "A vacuum relief valve set to 10 psi",
                "An open bypass pipe to the atmosphere"
            ),
            correctIndex = 0,
            explanation = "Air receiver tanks must have an ASME-certified spring-loaded pressure relief safety valve set at or below the tank's Maximum Allowable Working Pressure (MAWP) to prevent rupture, along with an automatic/manual water drain.",
            formulaOrRule = "ASME Pressure Safety Relief Valve on Receiver"
        ),
        Question(
            id = 47,
            category = Category.FLUID_POWER,
            crafts = listOf(CraftRole.MPE_9, CraftRole.MM_7),
            questionText = "In a double-acting hydraulic cylinder, why is the retraction force lower than the extension force when supplied with the same line pressure?",
            options = listOf(
                "Fluid travels faster during retraction",
                "The cylinder rod occupies area on the rod side, reducing the effective piston surface area exposed to fluid pressure",
                "The relief valve cracks open only on retraction",
                "Friction is doubled during retraction"
            ),
            correctIndex = 1,
            explanation = "Extension area = π × r_bore². Retraction area = Area_bore - Area_rod. Because F = P × A, the smaller effective surface area on the rod side produces less thrust force but higher linear retraction speed.",
            formulaOrRule = "A_retract = A_bore - A_rod"
        ),
        Question(
            id = 48,
            category = Category.FLUID_POWER,
            crafts = listOf(CraftRole.BEM_9, CraftRole.MPE_9),
            questionText = "What is the purpose of a refrigerated compressed air dryer in a postal automation facility?",
            options = listOf(
                "To freeze the compressed air into dry ice",
                "To cool compressed air to approximately 38°F, causing water vapor to condense out before reaching machinery pneumatic lines",
                "To increase line pressure by 50%",
                "To add fragrance to postal mail processing sorting rooms"
            ),
            correctIndex = 1,
            explanation = "Air dryers cool compressed air down to a pressure dewpoint of 35°F to 38°F, condensing moisture out of the air stream so that water does not rust valves, gum up cylinders, or soak postal mail.",
            formulaOrRule = "Refrigerated Air Dryer: Lowers dewpoint to ≈ 38°F"
        ),
        Question(
            id = 49,
            category = Category.FLUID_POWER,
            crafts = listOf(CraftRole.MPE_9, CraftRole.BEM_9),
            questionText = "What hydraulic valve is used to protect hydraulic system pumps and piping from over-pressurization by diverting excess pump flow back to the oil reservoir?",
            options = listOf("Check valve", "Main pressure relief valve", "Sequence valve", "Shuttle valve"),
            correctIndex = 1,
            explanation = "The main pressure relief valve is the primary safety valve in a hydraulic circuit. When system pressure exceeds its spring setting, it opens and dumps excess flow directly to the reservoir tank.",
            formulaOrRule = "Pressure Relief Valve: Limits maximum system pressure"
        ),
        Question(
            id = 50,
            category = Category.FLUID_POWER,
            crafts = listOf(CraftRole.MPE_9, CraftRole.ET_10),
            questionText = "In a pneumatic sorting diverter system, what type of valve allows fluid flow in only one direction while completely preventing backflow?",
            options = listOf("Check valve", "Needle valve", "4-way spool valve", "Regulator valve"),
            correctIndex = 0,
            explanation = "A check valve uses a spring-loaded ball or poppet to permit flow freely in the forward direction while blocking reverse flow immediately.",
            formulaOrRule = "Check Valve: One-way flow check"
        ),
        Question(
            id = 51,
            category = Category.FLUID_POWER,
            crafts = listOf(CraftRole.BEM_9, CraftRole.MPE_9),
            questionText = "What condition in a hydraulic pump is caused by air being sucked into the pump suction line, creating milky oil, jerky actuator motion, and loud whining?",
            options = listOf("Thermal expansion", "Aeration", "Hydrodynamic lubrication", "Fluid condensation"),
            correctIndex = 1,
            explanation = "Aeration occurs when air enters the hydraulic fluid through loose suction fittings, low reservoir levels, or shaft seals. Unlike cavitation (which boils fluid vapor), aeration is entrained atmospheric air.",
            formulaOrRule = "Aeration: Atmospheric air entrained in hydraulic fluid"
        ),
        Question(
            id = 52,
            category = Category.FLUID_POWER,
            crafts = listOf(CraftRole.MPE_9, CraftRole.MM_7),
            questionText = "What is the function of a pneumatic flow control valve equipped with an integral reverse-flow bypass check valve (meter-out flow control)?",
            options = listOf(
                "To control cylinder stroke speed by throttling exhausting air, while allowing free full-speed flow during the opposite stroke",
                "To increase air pressure above supply line rating",
                "To eliminate the need for an air filter",
                "To stop cylinder movement permanently"
            ),
            correctIndex = 0,
            explanation = "A meter-out flow control regulates cylinder speed by restricting air exhausting from the cylinder rod/cap end, providing smooth, chatter-free motion regardless of load fluctuations.",
            formulaOrRule = "Meter-Out Flow Control: Controls exhaust backpressure"
        ),
        Question(
            id = 53,
            category = Category.FLUID_POWER,
            crafts = listOf(CraftRole.BEM_9, CraftRole.MPE_9),
            questionText = "A hydraulic pump produces a volumetric flow rate of 10 Gallons Per Minute (GPM). How many cubic inches per minute does this represent?",
            options = listOf("1,000 cu in/min", "2,310 cu in/min", "1,728 cu in/min", "231 cu in/min"),
            correctIndex = 1,
            explanation = "One US liquid gallon contains exactly 231 cubic inches. Therefore, 10 GPM × 231 cu in/gal = 2,310 cubic inches per minute.",
            formulaOrRule = "1 Gallon = 231 cubic inches"
        ),
        Question(
            id = 54,
            category = Category.FLUID_POWER,
            crafts = listOf(CraftRole.MPE_9, CraftRole.BEM_9),
            questionText = "What is the function of a hydraulic counterbalance valve connected to a vertical cylinder supporting an overhead load?",
            options = listOf(
                "To prevent the load from free-falling under gravity by creating backpressure on the rod end until positive pump pilot pressure is applied",
                "To heat the oil before it enters the cylinder",
                "To reverse motor phase rotation",
                "To drain the hydraulic reservoir during operation"
            ),
            correctIndex = 0,
            explanation = "Counterbalance valves maintain backpressure in vertical or overrunning cylinders, preventing gravity from causing uncontrolled descent. They open only when pilot pressure from the opposite port signals intentional extension.",
            formulaOrRule = "Counterbalance Valve: Prevents runaway gravitational load"
        ),
        Question(
            id = 55,
            category = Category.FLUID_POWER,
            crafts = listOf(CraftRole.MPE_9, CraftRole.MM_7),
            questionText = "What happens to the relative humidity of compressed air when it is compressed to 100 psi without temperature conditioning?",
            options = listOf(
                "Moisture disappears completely",
                "Its moisture-holding capacity decreases drastically, causing rapid saturation and liquid water condensation in airlines",
                "The air becomes completely dry like desert air",
                "The air turns into pure nitrogen"
            ),
            correctIndex = 1,
            explanation = "Compressing air reduces its volume by a factor of 8 without reducing the mass of water vapor present. This drives the relative humidity past 100% (dewpoint), causing excessive liquid water condensation.",
            formulaOrRule = "Compression forces moisture condensation"
        ),
        Question(
            id = 56,
            category = Category.FLUID_POWER,
            crafts = listOf(CraftRole.MPE_9, CraftRole.BEM_9),
            questionText = "Which type of hydraulic pump provides variable volumetric displacement per revolution by tilting a swashplate angle?",
            options = listOf("External gear pump", "Axial piston pump", "Internal crescent gear pump", "Lobe pump"),
            correctIndex = 1,
            explanation = "In an axial piston pump, pistons reciprocate within a rotating cylinder barrel against an angled swashplate. Tilting the swashplate adjusts piston stroke length, varying displacement from zero to maximum.",
            formulaOrRule = "Variable Axial Piston Pump: Swashplate controls stroke"
        ),
        Question(
            id = 57,
            category = Category.FLUID_POWER,
            crafts = listOf(CraftRole.MPE_9, CraftRole.MM_7),
            questionText = "When bleeding air from a newly installed hydraulic cylinder and piping circuit, where is air most likely to collect and where should bleeding be performed?",
            options = listOf(
                "At the lowest drain plug in the reservoir",
                "At the highest physical point in the cylinder and line circuit",
                "Inside the oil filter bypass valve",
                "Directly at the electric motor starter"
            ),
            correctIndex = 1,
            explanation = "Because air is significantly lighter than hydraulic oil, air bubbles rise naturally and migrate to the highest elevation in the circuit. High-point bleed valves must be cracked open to purge trapped air.",
            formulaOrRule = "Air Bleeding: Bleed at highest physical point"
        ),
        Question(
            id = 58,
            category = Category.FLUID_POWER,
            crafts = listOf(CraftRole.BEM_9, CraftRole.MPE_9),
            questionText = "What is the standard ISO cleanliness code target for industrial hydraulic fluid in automated sorting systems with servo/proportional valves?",
            options = listOf("ISO 24/22/19", "ISO 16/14/11 or cleaner", "ISO 30/28/25", "No cleanliness standard exists"),
            correctIndex = 1,
            explanation = "High-precision servo and proportional valves have spool clearances as small as 1 to 4 microns. They require high cleanliness ratings (typically ISO 16/14/11 or better) to prevent valve sticking and premature wear.",
            formulaOrRule = "ISO Cleanliness: 16/14/11 for proportional/servo valves"
        ),
        Question(
            id = 59,
            category = Category.FLUID_POWER,
            crafts = listOf(CraftRole.MPE_9, CraftRole.MM_7),
            questionText = "In a pneumatic cylinder, what is the purpose of built-in adjustable end-of-stroke cushions?",
            options = listOf(
                "To trap a pocket of air at the end of the stroke, decelerating the piston smoothly to prevent damaging mechanical impact against the end caps",
                "To increase cylinder stroke length by 2 inches",
                "To act as a secondary air heater",
                "To lubricate the piston rod wiper"
            ),
            correctIndex = 0,
            explanation = "End-of-stroke cushions block the main exhaust port just before the end of travel, forcing remaining air through a restricted needle valve to decelerate the piston smoothly and eliminate slamming shock.",
            formulaOrRule = "Cylinder Cushioning: Decelerates piston before impact"
        ),
        Question(
            id = 60,
            category = Category.FLUID_POWER,
            crafts = listOf(CraftRole.MPE_9, CraftRole.BEM_9),
            questionText = "What gas is strictly required for pre-charging hydraulic accumulators, and why can compressed air or pure oxygen NEVER be used?",
            options = listOf(
                "Carbon dioxide only",
                "Dry Nitrogen gas (N2); oxygen or air under high pressure combined with hydraulic oil forms an explosive mixture (diesel effect)",
                "Helium only",
                "Compressed air is preferred"
            ),
            correctIndex = 1,
            explanation = "Pure dry Nitrogen (an inert gas) must always be used. Pre-charging with compressed air or oxygen creates an imminent explosion hazard due to spontaneous auto-ignition (diesel effect) when mixed with high-pressure oil vapors.",
            formulaOrRule = "Accumulator Safety: DRY NITROGEN ONLY (Never oxygen/air)"
        ),

        // ==========================================
        // 4. MAINTENANCE & TROUBLESHOOTING METHODOLOGY (Questions 61 - 80)
        // ==========================================
        Question(
            id = 61,
            category = Category.MAINTENANCE,
            crafts = listOf(CraftRole.MM_7, CraftRole.MPE_9, CraftRole.ET_10),
            questionText = "On a DBCS (Delivery Bar Code Sorter) transport belt, letters are consistently jamming at the stacker diverter gate. The photo-eye sensor LED indicates it is permanently 'blocked' even when no mail is present. What is the first logical check?",
            options = listOf(
                "Replace the entire PLC central CPU rack",
                "Inspect the optical lens and reflector for postal dust/debris buildup and verify physical sensor alignment",
                "Increase conveyor belt motor speed to 150%",
                "Re-flash the machine firmware"
            ),
            correctIndex = 1,
            explanation = "Optical sensors in postal processing environments accumulate paper dust on lenses and retro-reflective mirrors. A coated lens attenuates the infrared beam below threshold, registering a false blockage. Cleaning and alignment are first-line checks.",
            formulaOrRule = "Optical Sensor check: Dust cleaning & alignment first"
        ),
        Question(
            id = 62,
            category = Category.MAINTENANCE,
            crafts = listOf(CraftRole.ET_10, CraftRole.MPE_9),
            questionText = "In systematic troubleshooting, what is the 'half-split' (binary search) technique?",
            options = listOf(
                "Testing every component sequentially starting from the power cord",
                "Splitting the diagnostic circuit or signal path in the middle to determine whether the fault is in the upstream or downstream half",
                "Replacing half the circuit cards simultaneously",
                "Running the machine at half rated speed until the failure repeats"
            ),
            correctIndex = 1,
            explanation = "The half-split method tests the circuit at the approximate midpoint of the signal path. If the signal is normal there, the fault is downstream; if absent/corrupted, the fault is upstream. This divides remaining suspects in half each step.",
            formulaOrRule = "Half-Split: Tests midpoint to eliminate 50% of suspects"
        ),
        Question(
            id = 63,
            category = Category.MAINTENANCE,
            crafts = listOf(CraftRole.MM_7, CraftRole.MPE_9, CraftRole.BEM_9),
            questionText = "Which predictive maintenance (PdM) technology is most effective for detecting high-resistance electrical connections, overloaded circuit breakers, and loose busbar lugs BEFORE they cause an outage?",
            options = listOf(
                "Infrared thermography (thermal imaging)",
                "Vibration spectrum analysis",
                "Ultrasonic oil particle counter",
                "Eddy current metal hardness testing"
            ),
            correctIndex = 0,
            explanation = "Loose connections and overloaded conductors create localized resistive heating (P = I²R). Infrared cameras detect hot spots immediately under normal operating load without interrupting power.",
            formulaOrRule = "PdM: Infrared thermography detects electrical hot spots"
        ),
        Question(
            id = 64,
            category = Category.MAINTENANCE,
            crafts = listOf(CraftRole.ET_10, CraftRole.MPE_9),
            questionText = "An incremental optical rotary encoder connected to a mail conveyor shaft has two output channels (A and B) in quadrature. What is the electrical phase difference between channels A and B, and why is this used?",
            options = listOf(
                "180 degrees; used to cancel electromagnetic noise",
                "90 degrees; used to determine both speed and direction of shaft rotation",
                "0 degrees; identical redundant signals for safety backup",
                "45 degrees; used to multiply voltage by 1.414"
            ),
            correctIndex = 1,
            explanation = "Quadrature encoding uses two square wave channels shifted 90 electrical degrees apart. By observing whether channel A leads channel B or B leads A, the controller determines shaft direction as well as rotational velocity.",
            formulaOrRule = "Quadrature: 90° phase shift distinguishes CW from CCW"
        ),
        Question(
            id = 65,
            category = Category.MAINTENANCE,
            crafts = listOf(CraftRole.MM_7, CraftRole.MPE_9),
            questionText = "What is the primary difference between Preventive Maintenance (PM) and Corrective Maintenance (CM)?",
            options = listOf(
                "PM is performed on scheduled calendar/runtime intervals to prevent breakdowns, while CM is performed after a failure has already occurred",
                "PM is only performed on software, while CM is performed on hardware",
                "CM is scheduled annually, while PM is an emergency response",
                "There is no difference"
            ),
            correctIndex = 0,
            explanation = "PM encompasses routine scheduled tasks (lubrication, belt inspection, cleaning, adjustments) designed to prevent failures. CM restores equipment to operation after an unplanned functional failure occurs.",
            formulaOrRule = "PM = Proactive scheduled; CM = Reactive breakdown"
        ),
        Question(
            id = 66,
            category = Category.MAINTENANCE,
            crafts = listOf(CraftRole.ET_10, CraftRole.MPE_9),
            questionText = "An inductive proximity sensor fails to detect a plastic tote bin passing on a roller conveyor. Why does this occur?",
            options = listOf(
                "The sensor's sensitivity potentiometer is set too high",
                "Inductive proximity sensors only detect ferrous and non-ferrous metallic targets via electromagnetic eddy currents",
                "Plastic requires an ultrasonic sensor only",
                "The sensor input voltage is 24V DC instead of 120V AC"
            ),
            correctIndex = 1,
            explanation = "Inductive proximity sensors generate an oscillating RF electromagnetic field that detects metallic conductors. To detect non-metallic targets like plastic, cardboard, or liquids, a Capacitive or Optical sensor must be used.",
            formulaOrRule = "Inductive = Metals only; Capacitive = Metals & Non-metals"
        ),
        Question(
            id = 67,
            category = Category.MAINTENANCE,
            crafts = listOf(CraftRole.MM_7, CraftRole.MPE_9, CraftRole.ET_10),
            questionText = "What is Mean Time Between Failures (MTBF) and what does an increasing MTBF trend indicate regarding machine reliability?",
            options = listOf(
                "Average operating time between unscheduled breakdowns; an increasing MTBF indicates improving equipment reliability",
                "Total time required to repair a machine; increasing MTBF indicates slower technicians",
                "The maximum temperature of a motor before burning out",
                "The number of mail pieces sorted per hour"
            ),
            correctIndex = 0,
            explanation = "MTBF = Total operating hours / Number of failures. An increasing MTBF means the machine runs longer between breakdowns, reflecting effective PM and higher reliability.",
            formulaOrRule = "MTBF = Operating Hours / Failure Count"
        ),
        Question(
            id = 68,
            category = Category.MAINTENANCE,
            crafts = listOf(CraftRole.ET_10, CraftRole.MPE_9),
            questionText = "On an automated sorter PLC I/O card, the input LED illuminates when a limit switch closes, but the PLC logic program does not register the input bit turning ON. What is the most likely cause?",
            options = listOf(
                "The field switch wiring is completely severed",
                "Defective PLC optical isolator or input card bus interface circuitry",
                "The motor contactor is stuck closed",
                "The incoming 480V line voltage is 482V"
            ),
            correctIndex = 1,
            explanation = "The input LED is driven directly by the field loop circuit. If the LED illuminates, field voltage reaches the terminal block. If the CPU logic table does not update, the internal optocoupler or internal backplane register is faulty.",
            formulaOrRule = "PLC LED ON but Logic 0 = Faulty I/O card optocoupler/bus"
        ),
        Question(
            id = 69,
            category = Category.MAINTENANCE,
            crafts = listOf(CraftRole.MM_7, CraftRole.MPE_9),
            questionText = "When tracking a high-speed flat sorting belt that is wandering toward the left frame edge, which way should the tracking roller idler bolt on the left side be adjusted?",
            options = listOf(
                "Tighten (push forward) the idler bearing on the left side, or loosen the right side",
                "Loosen all tracking bolts completely",
                "Spray silicone lubricant on the belt drive pulley",
                "Reverse motor electrical rotation"
            ),
            correctIndex = 0,
            explanation = "A conveyor belt always climbs to the side of greater tension or the first point of contact on an angled roll. Increasing tension on the left side pushes the belt back toward the center/right.",
            formulaOrRule = "Belt Tracking: Belt moves toward side with higher tension"
        ),
        Question(
            id = 70,
            category = Category.MAINTENANCE,
            crafts = listOf(CraftRole.ET_10, CraftRole.MPE_9),
            questionText = "What diagnostic instrument is best suited for analyzing complex transient electrical noise spikes, timing jitter on encoder pulses, and serial communication waveforms?",
            options = listOf("Analog voltmeter", "Digital Storage Oscilloscope (DSO)", "Megohmmeter", "Phase rotation tester"),
            correctIndex = 1,
            explanation = "A Digital Storage Oscilloscope (DSO) captures high-speed voltage versus time waveforms, allowing technicians to inspect signal shape, rise times, noise spikes, and digital pulse timing.",
            formulaOrRule = "Oscilloscope: High-speed voltage vs. time waveform analysis"
        ),
        Question(
            id = 71,
            category = Category.MAINTENANCE,
            crafts = listOf(CraftRole.MM_7, CraftRole.BEM_9),
            questionText = "In vibration analysis of rotating equipment, what frequency vibration component typically indicates dynamic unbalance in a motor rotor or fan impeller?",
            options = listOf("0.5X running speed", "1X rotational running speed (fundamental frequency)", "10X running speed", "Blade pass frequency only"),
            correctIndex = 1,
            explanation = "Mass unbalance produces a heavy radial centrifugal force that repeats once per shaft revolution, showing as a dominant peak at 1X running speed in the FFT vibration spectrum.",
            formulaOrRule = "Vibration: 1X RPM dominant peak = Mass Unbalance"
        ),
        Question(
            id = 72,
            category = Category.MAINTENANCE,
            crafts = listOf(CraftRole.MM_7, CraftRole.MPE_9),
            questionText = "Why should a technician never use compressed shop air to blow postal paper dust off clothes or out of mechanical sorting machines without safety air nozzles?",
            options = listOf(
                "Compressed air can penetrate unbroken skin causing fatal air embolisms, and throws airborne paper dust into eyes",
                "Compressed air turns paper dust into liquid grease",
                "Paper dust will short circuit the air hose",
                "It violates copyright laws"
            ),
            correctIndex = 0,
            explanation = "OSHA 1910.242(b) restricts compressed air for cleaning to under 30 psi with chip guarding. High pressure air can penetrate human skin, causing fatal arterial air embolisms and severe eye injuries.",
            formulaOrRule = "Air Safety: OSHA 1910.242(b) Max 30 psi with chip guarding"
        ),
        Question(
            id = 73,
            category = Category.MAINTENANCE,
            crafts = listOf(CraftRole.ET_10, CraftRole.MPE_9),
            questionText = "What is the function of a snubber circuit (series resistor and capacitor) wired across an electromechanical relay contact switching an inductive load?",
            options = listOf(
                "To increase contact current rating by 400%",
                "To suppress contact arcing during contact opening, preventing pitting, contact welding, and electromagnetic interference (EMI)",
                "To keep the contacts permanently closed",
                "To step up 24V DC to 120V AC"
            ),
            correctIndex = 1,
            explanation = "RC snubber circuits absorb inductive energy when contacts open, dampening the high-voltage arc, preserving contact tip silver alloys, and eliminating radiated RF noise.",
            formulaOrRule = "RC Snubber: Contact arc suppression & EMI reduction"
        ),
        Question(
            id = 74,
            category = Category.MAINTENANCE,
            crafts = listOf(CraftRole.MM_7, CraftRole.MPE_9),
            questionText = "When replacing a worn drive belt, what must be done to the sheave grooves before fitting the new belt?",
            options = listOf(
                "Clean thoroughly and inspect with a sheave groove profile gauge for dish/sidewall wear",
                "Coat grooves with paint to add friction",
                "File the grooves flat with a bastard file",
                "No inspection is ever required"
            ),
            correctIndex = 0,
            explanation = "Worn sheave grooves (dished/concave sidewalls exceeding 1/32\") cause new belts to bottom out on the groove bottom, reducing wedging friction and causing rapid belt destruction.",
            formulaOrRule = "Sheave Inspection: Sheave groove gauge check (<1/32\" wear)"
        ),
        Question(
            id = 75,
            category = Category.MAINTENANCE,
            crafts = listOf(CraftRole.ET_10, CraftRole.MPE_9),
            questionText = "What does an unexpected resistance measurement of 0.0 Ω (dead short) between a motor power phase lead and the motor grounded metallic frame indicate?",
            options = listOf(
                "Normal healthy ground connection",
                "Catastrophic phase-to-ground winding insulation breakdown (ground fault)",
                "The motor is running backward",
                "The thermal overload is tripped"
            ),
            correctIndex = 1,
            explanation = "Phase windings must be completely isolated from the metallic motor chassis. A 0.0 Ω reading to ground indicates a direct short circuit that will trip circuit protection and could cause severe shock.",
            formulaOrRule = "Winding-to-Frame Resistance must be > 1 MΩ (Never 0 Ω)"
        ),
        Question(
            id = 76,
            category = Category.MAINTENANCE,
            crafts = listOf(CraftRole.MM_7, CraftRole.MPE_9),
            questionText = "What is the root cause when a roller chain drive produces a rhythmic popping and climbing sound over the sprocket teeth?",
            options = listOf(
                "The chain is elongated from internal pin/bushing wear and no longer pitches correctly with the sprocket teeth",
                "The chain is running too cold",
                "The sprockets have too many teeth",
                "The chain is made of stainless steel"
            ),
            correctIndex = 0,
            explanation = "Chain 'stretch' is actually internal metal wear between pins and bushings. As pitch increases beyond sprocket tooth pitch, rollers ride up on the sprocket tooth tips and snap down into the roots.",
            formulaOrRule = "Chain Wear: Elongation causes sprocket tooth climbing"
        ),
        Question(
            id = 77,
            category = Category.MAINTENANCE,
            crafts = listOf(CraftRole.ET_10, CraftRole.MPE_9),
            questionText = "What is the typical operating DC voltage range for industrial sensors (photo-eyes, proximity sensors) on modern postal automation equipment?",
            options = listOf("5V to 10V AC", "10V to 30V DC (nominally 24V DC)", "120V to 240V AC", "480V DC"),
            correctIndex = 1,
            explanation = "Modern automated material handling and postal sorting machines standardize on 24V DC control power (operating window 10V to 30V DC) for sensors, solid-state logic, and solenoid valves.",
            formulaOrRule = "Standard Industrial Sensor Power: 24V DC nominal"
        ),
        Question(
            id = 78,
            category = Category.MAINTENANCE,
            crafts = listOf(CraftRole.MM_7, CraftRole.MPE_9),
            questionText = "How should a technician check the operating temperature of a running motor safely and accurately without risking contact with rotating parts?",
            options = listOf(
                "Hold bare fingers firmly against the spinning shaft",
                "Use an infrared non-contact pyrometer (thermometer) aimed at the stator core",
                "Pour water on the motor and see if it boils",
                "Smell the motor from 50 feet away"
            ),
            correctIndex = 1,
            explanation = "Infrared non-contact thermometers measure surface infrared radiation safely from a distance, preventing burns or entanglements with spinning shafts and fan blades.",
            formulaOrRule = "Thermal Inspection: Non-contact infrared thermometer"
        ),
        Question(
            id = 79,
            category = Category.MAINTENANCE,
            crafts = listOf(CraftRole.ET_10, CraftRole.MPE_9),
            questionText = "What is the function of an E-Stop (Emergency Stop) push-button safety circuit wired to a Category 4 safety relay?",
            options = listOf(
                "It immediately cuts control power to all machine hazard actuators and requires a deliberate manual reset before restarts can occur",
                "It pauses the sorter for 5 seconds and automatically resumes",
                "It operates as a standard production start button",
                "It turns on the room lights"
            ),
            correctIndex = 0,
            explanation = "NFPA 79 and OSHA require emergency stop systems to be hardwired, fail-safe, normally closed dual-channel circuits that remove actuator power immediately and prevent automatic restarts.",
            formulaOrRule = "E-Stop: Dual-channel, normally closed, manual reset"
        ),
        Question(
            id = 80,
            category = Category.MAINTENANCE,
            crafts = listOf(CraftRole.MM_7, CraftRole.BEM_9),
            questionText = "What is the correct procedure when an electrical disconnect switch is tagged and locked out by another technician who has already left the building?",
            options = listOf(
                "Cut the lock off with bolt cutters immediately and turn the machine on",
                "Follow official OSHA and USPS emergency lock removal procedures, verifying the employee is off-site, informing management, and inspecting the machine fully",
                "Kick the disconnect box open",
                "Borrow another technician's key"
            ),
            correctIndex = 1,
            explanation = "Only the employee who applied a lockout lock may remove it, except under strict emergency protocols: verifying absence, facility manager authorization, safety inspection, and formal documentation before re-energizing.",
            formulaOrRule = "LOTO Rule: Lock removal only by authorized procedure"
        ),

        // ==========================================
        // 5. SPATIAL RELATIONS & PERCEPTION (Questions 81 - 100)
        // ==========================================
        Question(
            id = 81,
            category = Category.SPATIAL,
            crafts = listOf(CraftRole.MM_7, CraftRole.MPE_9, CraftRole.ET_10, CraftRole.BEM_9, CraftRole.AMT_9),
            questionText = "A standard 2D cross net for a cube has 6 faces: Top, Bottom, and 4 wrap-around sides (Front, Right, Back, Left). If the Front face is labeled 'F' and the Right face is labeled 'R', which face will be OPPOSITE to the Front face when folded into a 3D box?",
            options = listOf("The Left face", "The Back face", "The Top face", "The Right face"),
            correctIndex = 1,
            explanation = "When a standard cube net is folded, opposite faces can never touch along an edge. The four side faces wrap in sequence: Front -> Right -> Back -> Left. Thus Front is directly opposite to Back, Left is opposite to Right, and Top is opposite to Bottom.",
            formulaOrRule = "Cube folding rule: Opposite faces never share an edge",
            diagramType = "CUBE_NET_CROSS",
            diagramData = "TOP:T | SIDES: L, F, R, B | BOTTOM:D"
        ),
        Question(
            id = 82,
            category = Category.SPATIAL,
            crafts = listOf(CraftRole.MM_7, CraftRole.MPE_9, CraftRole.ET_10, CraftRole.BEM_9, CraftRole.AMT_9),
            questionText = "Consider a flat sheet metal template unfolded into a T-shape. If a face with a solid black circle is opposite to a face with an open triangle, can these two faces ever be visible simultaneously from a single 3D perspective viewpoint?",
            options = listOf(
                "Yes, always visible together on adjacent corners",
                "No, opposite faces on a cube can never be seen together in a single exterior view",
                "Only if the cube is viewed from an angle of 45 degrees",
                "Yes, if the cube is hollow"
            ),
            correctIndex = 1,
            explanation = "A single exterior perspective view of a solid cube can show at most 3 mutually adjacent faces sharing a single vertex. Because opposite faces are parallel and 180° apart, you can NEVER see two opposite faces simultaneously.",
            formulaOrRule = "Spatial Rule: Maximum 3 mutually adjacent faces visible"
        ),
        Question(
            id = 83,
            category = Category.SPATIAL,
            crafts = listOf(CraftRole.MM_7, CraftRole.MPE_9, CraftRole.ET_10, CraftRole.BEM_9),
            questionText = "In an orthographic projection of a machine mounting bracket, the Top View shows a circle and the Front View shows a plain rectangle. What 3D geometric shape is represented?",
            options = listOf("A rectangular prism", "A solid cylinder", "A sphere", "A triangular pyramid"),
            correctIndex = 1,
            explanation = "A right circular cylinder projects as a circle when viewed from the end (Top View) and as a rectangle when viewed from the side (Front View).",
            formulaOrRule = "Orthographic: Circle top + Rectangle front = Cylinder"
        ),
        Question(
            id = 84,
            category = Category.SPATIAL,
            crafts = listOf(CraftRole.ET_10, CraftRole.MPE_9, CraftRole.MM_7),
            questionText = "A 3D cube has 6 faces: Face 1 has a diagonal stripe from bottom-left to top-right. Face 2 is solid black. If Face 1 and Face 2 share an edge, and you rotate the 3D cube 90 degrees around the vertical axis, what happens to their relative spatial orientation?",
            options = listOf(
                "Their shared edge is broken and they become opposite",
                "They remain adjacent and share the exact same edge",
                "Face 1 becomes inverted upside-down",
                "Face 2 moves to the bottom face"
            ),
            correctIndex = 1,
            explanation = "Rigid body 3D rotation preserves all topological relationships: vertices, edges, and adjacency between faces never change regardless of viewing angle or rotation in space.",
            formulaOrRule = "Rigid 3D Body: Adjacency and edge relations are invariant"
        ),
        Question(
            id = 85,
            category = Category.SPATIAL,
            crafts = listOf(CraftRole.MM_7, CraftRole.MPE_9, CraftRole.BEM_9),
            questionText = "In a 2D net of a cube laid out as a horizontal row of 4 squares (1, 2, 3, 4) with square 5 attached above square 2 and square 6 attached below square 2, which square is directly opposite square 5?",
            options = listOf("Square 1", "Square 6", "Square 3", "Square 4"),
            correctIndex = 1,
            explanation = "Square 5 is the top flap and square 6 is the bottom flap. When both are folded 90 degrees toward the central body, they form parallel opposite planes (Top and Bottom).",
            formulaOrRule = "Opposite Flaps Rule: Flaps on opposite sides of a row fold opposite"
        ),
        Question(
            id = 86,
            category = Category.SPATIAL,
            crafts = listOf(CraftRole.MM_7, CraftRole.MPE_9),
            questionText = "If a cube has vertices labeled A through H, how many total vertices (corners), edges, and faces does a single standard cube have?",
            options = listOf(
                "8 vertices, 12 edges, 6 faces",
                "6 vertices, 8 edges, 12 faces",
                "8 vertices, 8 edges, 8 faces",
                "12 vertices, 12 edges, 6 faces"
            ),
            correctIndex = 0,
            explanation = "Euler's polyhedron formula V - E + F = 2 confirms: 8 vertices - 12 edges + 6 faces = 2.",
            formulaOrRule = "Cube Geometry: 8 vertices, 12 edges, 6 faces"
        ),
        Question(
            id = 87,
            category = Category.SPATIAL,
            crafts = listOf(CraftRole.ET_10, CraftRole.MPE_9),
            questionText = "In orthographic technical drafting, what is the standard American projection convention where the top view is placed above the front view?",
            options = listOf("First Angle Projection", "Third Angle Projection", "Isometric oblique projection", "Cabinet projection"),
            correctIndex = 1,
            explanation = "Third Angle Projection is standard in the United States and Canada (ANSI/ASME): Top view is above Front view, and Right-side view is to the right of Front view.",
            formulaOrRule = "US Technical Standards: Third Angle Projection"
        ),
        Question(
            id = 88,
            category = Category.SPATIAL,
            crafts = listOf(CraftRole.MM_7, CraftRole.MPE_9),
            questionText = "A flat pattern net is folded into a triangular prism (like a Toblerone box). How many faces does a triangular prism possess?",
            options = listOf("4 faces", "5 faces (2 triangular bases + 3 rectangular sides)", "6 faces", "8 faces"),
            correctIndex = 1,
            explanation = "A triangular prism has 5 total faces: 2 congruent triangular ends and 3 rectangular sides connecting them.",
            formulaOrRule = "Triangular Prism: 5 faces, 9 edges, 6 vertices"
        ),
        Question(
            id = 89,
            category = Category.SPATIAL,
            crafts = listOf(CraftRole.MM_7, CraftRole.MPE_9),
            questionText = "An L-shaped flat stamping is folded at 90 degrees along its internal corner crease. How many mutual 90-degree planes does the resulting 3D bracket form?",
            options = listOf("1 plane", "2 perpendicular planes", "4 planes", "3 parallel planes"),
            correctIndex = 1,
            explanation = "Folding a flat 2D sheet along a single bend line creates two distinct planes intersecting at 90 degrees (a 90° angle bracket).",
            formulaOrRule = "Single Bend: Produces 2 intersecting planes"
        ),
        Question(
            id = 90,
            category = Category.SPATIAL,
            crafts = listOf(CraftRole.MM_7, CraftRole.MPE_9),
            questionText = "When viewing a 3D rectangular object from the front, hidden interior features (such as a drilled hole through the center) are represented on blueprints using which line type?",
            options = listOf("Thick solid line", "Dashed hidden line (short dashes with equal spacing)", "Centerline (long and short dashes)", "Zig-zag break line"),
            correctIndex = 1,
            explanation = "Standard drafting standards (ASME Y14.2) use dashed lines (hidden lines) of medium thickness to represent edges, surfaces, or corners obscured from the viewer's direct line of sight.",
            formulaOrRule = "Drafting Conventions: Dashed lines indicate hidden features"
        ),
        Question(
            id = 91,
            category = Category.SPATIAL,
            crafts = listOf(CraftRole.MM_7, CraftRole.MPE_9),
            questionText = "A solid block has dimensions 2 inches by 3 inches by 4 inches. What is its total surface area?",
            options = listOf("24 sq inches", "52 sq inches", "48 sq inches", "26 sq inches"),
            correctIndex = 1,
            explanation = "Surface area = 2 × (L×W + W×H + L×H) = 2 × (2×3 + 3×4 + 2×4) = 2 × (6 + 12 + 8) = 2 × 26 = 52 square inches. (Volume is 2 × 3 × 4 = 24 cu in).",
            formulaOrRule = "Surface Area = 2(ab + bc + ac)"
        ),
        Question(
            id = 92,
            category = Category.SPATIAL,
            crafts = listOf(CraftRole.ET_10, CraftRole.MPE_9),
            questionText = "If an isometric drawing shows an arrow pointing towards a face with an equilateral triangle pointing UPWARDS, and the cube is rotated 180 degrees about its horizontal left-to-right axis, in which direction will the triangle point?",
            options = listOf("Downwards", "Upwards", "To the right", "To the left"),
            correctIndex = 0,
            explanation = "Rotating 180° around the horizontal axis inverts vertical orientation: elements pointing UP invert to point DOWN.",
            formulaOrRule = "180° Horizontal Axis Rotation: Vertical directions invert"
        ),
        Question(
            id = 93,
            category = Category.SPATIAL,
            crafts = listOf(CraftRole.MM_7, CraftRole.MPE_9),
            questionText = "In a standard unfolded pattern of a cube with faces 1, 2, 3, 4, 5, 6, which pair can NEVER be adjacent (can never share an edge)?",
            options = listOf(
                "Any pair that is opposite each other when folded",
                "Faces sharing a fold line",
                "Top and Front faces",
                "Left and Bottom faces"
            ),
            correctIndex = 0,
            explanation = "Opposite faces lie on parallel planes separated by the width of the cube; they can never meet or share an edge in 3D space.",
            formulaOrRule = "Opposite faces share zero edges"
        ),
        Question(
            id = 94,
            category = Category.SPATIAL,
            crafts = listOf(CraftRole.MM_7, CraftRole.MPE_9),
            questionText = "What 3D shape is formed by rotating a right-angled triangle 360 degrees around one of its perpendicular legs?",
            options = listOf("A sphere", "A right circular cone", "A cylinder", "A torus"),
            correctIndex = 1,
            explanation = "Revolving a right triangle around one of its legs sweeps out a right circular cone with circular base and apex at the top.",
            formulaOrRule = "Solid of Revolution: Right triangle revolving = Cone"
        ),
        Question(
            id = 95,
            category = Category.SPATIAL,
            crafts = listOf(CraftRole.MM_7, CraftRole.MPE_9),
            questionText = "A blueprint displays an object whose Front View is a triangle, Side View is a triangle, and Top View is a square with an 'X' across it. What is the 3D shape?",
            options = listOf("A cube", "A square-based pyramid", "A cone", "A triangular prism"),
            correctIndex = 1,
            explanation = "A pyramid with a square base projects as a square with corner diagonals meeting in the center from the top, and as triangles from both the front and side views.",
            formulaOrRule = "Orthographic: Square top + Triangle front/side = Square Pyramid"
        ),
        Question(
            id = 96,
            category = Category.SPATIAL,
            crafts = listOf(CraftRole.MM_7, CraftRole.MPE_9),
            questionText = "How many identical 1-inch cube blocks are required to construct a solid 3-inch cube?",
            options = listOf("9 blocks", "18 blocks", "27 blocks", "36 blocks"),
            correctIndex = 2,
            explanation = "Volume of 3-inch cube = 3 × 3 × 3 = 27 cubic inches. Each 1-inch block has volume 1 cu in. Thus 27 blocks are required.",
            formulaOrRule = "Cube Volume = s³ = 3³ = 27"
        ),
        Question(
            id = 97,
            category = Category.SPATIAL,
            crafts = listOf(CraftRole.MM_7, CraftRole.MPE_9),
            questionText = "When looking down on a 45-degree chamfered edge from the top view, how does the angled surface appear in a 2D orthographic projection?",
            options = listOf("As a curve", "As a foreshortened flat rectangular strip", "It is invisible", "As a circle"),
            correctIndex = 1,
            explanation = "An inclined flat surface appears foreshortened (narrower than its true physical length) as a plane rectangle in orthographic projection.",
            formulaOrRule = "Foreshortening: Inclined surfaces project as narrower planes"
        ),
        Question(
            id = 98,
            category = Category.SPATIAL,
            crafts = listOf(CraftRole.ET_10, CraftRole.MPE_9),
            questionText = "A cube has 3 distinct symbols on 3 faces that meet at a common vertex: a Star, a Cross, and a Dot. If you look directly at the corner vertex, how do the 3 faces relate?",
            options = listOf(
                "They are mutually adjacent and all 3 share the single common vertex point",
                "Two of the faces must be opposite",
                "The Cross and Star must be parallel",
                "Only one face can touch the vertex"
            ),
            correctIndex = 0,
            explanation = "Every vertex of a cube is the exact intersection point of exactly 3 mutually perpendicular, adjacent faces.",
            formulaOrRule = "Cube Vertex: Exactly 3 mutually adjacent faces meet at each vertex"
        ),
        Question(
            id = 99,
            category = Category.SPATIAL,
            crafts = listOf(CraftRole.MM_7, CraftRole.MPE_9),
            questionText = "If you unfold a hollow cylinder with closed top and bottom lids into a flat 2D pattern, what shapes make up the net?",
            options = listOf(
                "3 circles",
                "1 rectangle and 2 circles",
                "4 triangles and 1 square",
                "2 squares and 2 rectangles"
            ),
            correctIndex = 1,
            explanation = "Unrolling the curved cylinder wall produces a flat rectangle (length equal to the base circumference 2πr), with two circular disks for the top and bottom covers.",
            formulaOrRule = "Cylinder Net: 1 rectangle (length = 2πr) + 2 circles"
        ),
        Question(
            id = 100,
            category = Category.SPATIAL,
            crafts = listOf(CraftRole.MM_7, CraftRole.MPE_9),
            questionText = "In isometric drawing projection, at what angle are the three primary coordinate axes (X, Y, Z) spaced relative to each other?",
            options = listOf("90 degrees", "120 degrees apart (30 degrees from horizontal baseline)", "45 degrees", "180 degrees"),
            correctIndex = 1,
            explanation = "Isometric drawings place the three principal axes at 120° angles to each other (vertical axis at 90°, and two lateral axes inclined at 30° to the horizontal).",
            formulaOrRule = "Isometric Axes: 120° apart (30° baseline incline)"
        ),

        // ==========================================
        // 6. HVAC & PLANT / BUILDING SYSTEMS (Questions 101 - 120)
        // ==========================================
        Question(
            id = 101,
            category = Category.HVAC_BUILDING,
            crafts = listOf(CraftRole.BEM_9, CraftRole.AMT_9),
            questionText = "In a standard vapor-compression refrigeration cycle used in postal facility air conditioning, what are the four sequential thermodynamic stages?",
            options = listOf(
                "Boiler -> Turbine -> Condenser -> Pump",
                "Compressor -> Condenser -> Expansion Device (TXV) -> Evaporator",
                "Evaporator -> Expansion Valve -> Condenser -> Compressor",
                "Condenser -> Compressor -> Fan -> Chiller barrel"
            ),
            correctIndex = 1,
            explanation = "The refrigeration cycle flows: 1) Compressor (raises pressure and temp of refrigerant vapor), 2) Condenser (rejects heat to outside air, condensing vapor to high-pressure liquid), 3) Expansion Device (drops pressure, causing flash cooling), 4) Evaporator (absorbs heat from indoor air/water, boiling liquid to vapor).",
            formulaOrRule = "Refrigeration Cycle: Compressor -> Condenser -> TXV -> Evaporator"
        ),
        Question(
            id = 102,
            category = Category.HVAC_BUILDING,
            crafts = listOf(CraftRole.BEM_9, CraftRole.AMT_9),
            questionText = "A large chilled water centrifugal pump in the postal plant mechanical room begins making a loud rattling sound like 'pumping gravel' and discharge pressure fluctuates wildly. What is occurring?",
            options = listOf(
                "Motor electrical single-phasing",
                "Cavitation (vapor bubbles forming at low suction pressure and violently collapsing against the impeller)",
                "Excessive pipe thermal insulation",
                "Reversed impeller rotation from low voltage"
            ),
            correctIndex = 1,
            explanation = "Cavitation occurs when the Net Positive Suction Head Available (NPSHA) falls below required suction pressure. Liquid boils into microscopic vapor cavities at the impeller eye, which then implode violently under discharge pressure, pitting metal and sounding like gravel.",
            formulaOrRule = "Cavitation: Low suction head causes vapor implosion"
        ),
        Question(
            id = 103,
            category = Category.HVAC_BUILDING,
            crafts = listOf(CraftRole.BEM_9),
            questionText = "On a low-pressure steam boiler heating system, which safety device is the PRIMARY protection against dry firing (catastrophic vessel explosion)?",
            options = listOf(
                "Thermostatic radiator trap",
                "Low-water fuel cut-off (LWCO)",
                "Barometric draft damper",
                "Chemical feed metering pump"
            ),
            correctIndex = 1,
            explanation = "The Low-Water Cut-Off (LWCO) is the critical safety device that automatically cuts off electrical power to the burner whenever the boiler water level drops below the safe operating minimum, preventing the boiler tubes from overheating and rupturing.",
            formulaOrRule = "Boiler safety: Low-Water Cut-Off (LWCO) prevents dry fire"
        ),
        Question(
            id = 104,
            category = Category.HVAC_BUILDING,
            crafts = listOf(CraftRole.BEM_9, CraftRole.AMT_9, CraftRole.ET_10),
            questionText = "In a 208Y/120V 3-phase, 4-wire electrical distribution panel, what is the nominal AC voltage measured between Phase A and Phase B, and between Phase A and Neutral?",
            options = listOf(
                "Phase A to B = 120V; Phase A to Neutral = 208V",
                "Phase A to B = 208V; Phase A to Neutral = 120V",
                "Phase A to B = 240V; Phase A to Neutral = 120V",
                "Phase A to B = 480V; Phase A to Neutral = 277V"
            ),
            correctIndex = 1,
            explanation = "In a Wye (Y) system, line-to-line voltage is √3 (1.732) times line-to-neutral voltage. Line-to-line (Phase A to Phase B) = 120V × √3 ≈ 208V. Line-to-neutral (Phase A to Neutral) = 120V.",
            formulaOrRule = "V_line-line = V_line-neutral × √3 (120V × 1.732 = 208V)"
        ),
        Question(
            id = 105,
            category = Category.HVAC_BUILDING,
            crafts = listOf(CraftRole.BEM_9, CraftRole.AMT_9),
            questionText = "In a 480Y/277V 3-phase commercial building service, what voltage is supplied between any single phase conductor and neutral (commonly used for postal facility fluorescent and LED high-bay lighting)?",
            options = listOf("120V", "208V", "277V", "480V"),
            correctIndex = 2,
            explanation = "480V divided by √3 (1.732) = 277V. In industrial facilities, large lighting ballasts/drivers are powered by 277V phase-to-neutral, while 480V phase-to-phase powers large 3-phase motors.",
            formulaOrRule = "480V / √3 = 277V Line-to-Neutral"
        ),
        Question(
            id = 106,
            category = Category.HVAC_BUILDING,
            crafts = listOf(CraftRole.BEM_9),
            questionText = "What is 'superheat' in refrigeration and where is it measured to ensure liquid refrigerant does not enter the compressor crankcase?",
            options = listOf(
                "The sensible heat added to refrigerant vapor after it has completely evaporated, measured at the evaporator outlet/compressor suction line",
                "The temperature of condenser water leaving the cooling tower",
                "The temperature of compressor motor windings",
                "Superheat only exists in steam boilers"
            ),
            correctIndex = 0,
            explanation = "Superheat is the temperature difference between the refrigerant vapor temperature and its saturation boiling point at that pressure. Positive superheat (typically 8°F to 12°F) guarantees 100% vapor enters the compressor, preventing liquid slugging.",
            formulaOrRule = "Superheat = Actual Suction Temp - Saturation Temp"
        ),
        Question(
            id = 107,
            category = Category.HVAC_BUILDING,
            crafts = listOf(CraftRole.BEM_9, CraftRole.AMT_9),
            questionText = "What is the function of a thermostatic expansion valve (TXV) in a chilled water system?",
            options = listOf(
                "It meters the exact flow of liquid refrigerant into the evaporator based on evaporator outlet superheat",
                "It acts as an on/off electrical switch",
                "It pumps oil back to the compressor",
                "It heats the condenser coils"
            ),
            correctIndex = 0,
            explanation = "A TXV uses a sensing bulb attached to the suction line to modulate liquid refrigerant flow, maintaining a constant superheat setting across varying cooling loads.",
            formulaOrRule = "TXV: Modulates refrigerant flow to hold constant superheat"
        ),
        Question(
            id = 108,
            category = Category.HVAC_BUILDING,
            crafts = listOf(CraftRole.BEM_9),
            questionText = "What maintenance test should be performed quarterly on a commercial boiler safety relief valve?",
            options = listOf(
                "Weld the valve shut to prevent dripping",
                "Manually lift the test lever (try-lever test) while operating under at least 75% rated pressure to verify free movement and seat flushing",
                "Replace the spring with a heavier spring",
                "Paint the valve red"
            ),
            correctIndex = 1,
            explanation = "National Board and ASME codes recommend manual lever testing under pressure to prevent scale buildup from seizing the valve disk to the nozzle seat.",
            formulaOrRule = "Boiler Safety Valve: Periodic manual try-lever test"
        ),
        Question(
            id = 109,
            category = Category.HVAC_BUILDING,
            crafts = listOf(CraftRole.BEM_9, CraftRole.AMT_9),
            questionText = "In a facility cooling tower, what is the process of removing a small percentage of circulating water to prevent the concentration of dissolved mineral solids and scale formation?",
            options = listOf("Blowdown (bleed-off)", "Free cooling", "Cavitation", "Subcooling"),
            correctIndex = 0,
            explanation = "As water evaporates in a cooling tower, dissolved minerals remain behind. Continuous or periodic 'blowdown' bleeds mineral-concentrated water to the drain while fresh makeup water dilutes the basin.",
            formulaOrRule = "Cooling Tower: Blowdown controls mineral concentration"
        ),
        Question(
            id = 110,
            category = Category.HVAC_BUILDING,
            crafts = listOf(CraftRole.BEM_9),
            questionText = "What is the function of an Automatic Transfer Switch (ATS) in a postal processing plant during a commercial utility power outage?",
            options = listOf(
                "It immediately signals the emergency diesel generator to start and transfers critical plant loads from utility power to generator power within seconds",
                "It disconnects all batteries permanently",
                "It steps down 480V to 12V DC",
                "It operates the freight elevators manually"
            ),
            correctIndex = 0,
            explanation = "An ATS senses loss of normal utility voltage, sends a remote start command to the emergency backup generator, and automatically throws the mechanical transfer switch once generator voltage and frequency stabilize.",
            formulaOrRule = "ATS: Automatic utility-to-generator emergency transfer"
        ),
        Question(
            id = 111,
            category = Category.HVAC_BUILDING,
            crafts = listOf(CraftRole.BEM_9),
            questionText = "In psychrometrics, what is 'dew point temperature'?",
            options = listOf(
                "The temperature at which air becomes 100% saturated with water vapor and condensation begins to form",
                "The boiling point of liquid mercury",
                "The temperature inside an air duct at full blower speed",
                "The temperature of dry ambient air with zero humidity"
            ),
            correctIndex = 0,
            explanation = "Dew point is the temperature to which moist air must be cooled at constant pressure to reach 100% relative humidity, causing moisture to condense into fog, dew, or liquid water.",
            formulaOrRule = "Dew Point: Saturation temp where RH reaches 100%"
        ),
        Question(
            id = 112,
            category = Category.HVAC_BUILDING,
            crafts = listOf(CraftRole.BEM_9),
            questionText = "What is the function of steam traps installed on steam heating distribution mains and heat exchangers?",
            options = listOf(
                "To discharge condensed water (condensate) and non-condensable gases while preventing live steam from escaping",
                "To trap steam inside the boiler and prevent heat transfer",
                "To convert steam back into diesel fuel",
                "To increase steam pressure by 100 psi"
            ),
            correctIndex = 0,
            explanation = "Steam traps (inverted bucket, thermodynamic, or thermostatic) allow heavy water condensate to drain into return lines while holding back high-energy gaseous steam.",
            formulaOrRule = "Steam Trap: Drains condensate while retaining steam"
        ),
        Question(
            id = 113,
            category = Category.HVAC_BUILDING,
            crafts = listOf(CraftRole.BEM_9, CraftRole.AMT_9),
            questionText = "When a centrifugal pump mechanical seal leaks fluid continuously along the shaft, what is the proper maintenance action?",
            options = listOf(
                "Tighten the gland bolts with an impact gun until the shaft stops turning",
                "Lockout pump, isolate suction/discharge valves, drain casing, replace the mechanical seal assembly, and inspect shaft sleeve for scoring",
                "Pour glue on the rotating shaft",
                "Run the pump dry to burn the leak away"
            ),
            correctIndex = 1,
            explanation = "Mechanical seals utilize precision-lapped carbon and silicon carbide faces. When worn or cracked, the seal assembly must be replaced after proper electrical and hydraulic lockout.",
            formulaOrRule = "Mechanical Seal: Replace precision face assembly"
        ),
        Question(
            id = 114,
            category = Category.HVAC_BUILDING,
            crafts = listOf(CraftRole.BEM_9),
            questionText = "In an Air Handling Unit (AHU), what safety control shuts off the supply fan immediately when dense smoke is detected in the return air ductwork?",
            options = listOf("Duct smoke detector interlock", "Freeze stat", "High-limit pressure switch", "Humidistat"),
            correctIndex = 0,
            explanation = "Duct smoke detectors (NFPA 90A) interlock directly into the fan starter or fire alarm system to stop air circulation, preventing the fan from spreading toxic smoke throughout the facility.",
            formulaOrRule = "NFPA 90A: Duct smoke detector shuts down supply fan"
        ),
        Question(
            id = 115,
            category = Category.HVAC_BUILDING,
            crafts = listOf(CraftRole.BEM_9),
            questionText = "What is the purpose of an economizer damper cycle on a large commercial rooftop HVAC unit?",
            options = listOf(
                "To bring in cool outdoor air for 'free cooling' when outside enthalpy/temperature is lower than indoor return air, reducing compressor runtime",
                "To recycle old exhaust air indefinitely",
                "To heat the building using cooling tower fans",
                "To run the air conditioning during blizzards"
            ),
            correctIndex = 0,
            explanation = "Economizers open outside air dampers to cool the building with outdoor air when conditions are favorable (e.g. below 55°F), saving substantial electrical energy by keeping compressors off.",
            formulaOrRule = "Economizer: Free cooling using outdoor ambient air"
        ),
        Question(
            id = 116,
            category = Category.HVAC_BUILDING,
            crafts = listOf(CraftRole.BEM_9, CraftRole.AMT_9),
            questionText = "What instrument is used by building technicians to measure air velocity (FPM) and airflow volume (CFM) across supply diffusers and ductwork?",
            options = listOf("Anemometer (vane or hot-wire) or pitot tube with manometer", "Megohmmeter", "Hydrometer", "Pyrometer"),
            correctIndex = 0,
            explanation = "Anemometers and pitot tubes measure air velocity in feet per minute (FPM). Airflow volume CFM = Velocity (FPM) × Duct Cross-Sectional Area (sq ft).",
            formulaOrRule = "Airflow: CFM = Velocity (FPM) × Area (sq ft)"
        ),
        Question(
            id = 117,
            category = Category.HVAC_BUILDING,
            crafts = listOf(CraftRole.BEM_9),
            questionText = "What safety device on a chilled water coil or preheat coil prevents the water coils from freezing and bursting during freezing winter weather?",
            options = listOf("Freezestat (low temperature limit thermostat)", "High pressure cut-out", "Humidistat", "High water cutoff"),
            correctIndex = 0,
            explanation = "A freezestat (typically set at 35°F to 38°F with a 20-foot capillary tube) trips when any portion of the coil approaches freezing, shutting the outside dampers and starting the freeze pump.",
            formulaOrRule = "Freezestat: Protects water coils from freezing"
        ),
        Question(
            id = 118,
            category = Category.HVAC_BUILDING,
            crafts = listOf(CraftRole.BEM_9),
            questionText = "What is the effect of dirty, plugged air filters in an air handling unit on fan motor current and system air volume?",
            options = listOf(
                "Static pressure increases, airflow volume (CFM) drops, and indoor comfort suffers",
                "Airflow increases by 50%",
                "Motor speed doubles",
                "Duct temperature drops below zero"
            ),
            correctIndex = 0,
            explanation = "Clogged air filters create high differential static pressure resistance, drastically reducing airflow (CFM) through ductwork and causing evaporator coils to ice up.",
            formulaOrRule = "Air Filters: High static differential drops system CFM"
        ),
        Question(
            id = 119,
            category = Category.HVAC_BUILDING,
            crafts = listOf(CraftRole.BEM_9, CraftRole.AMT_9),
            questionText = "In building plumbing, what is the primary purpose of a P-trap installed under every sink and floor drain?",
            options = listOf(
                "To retain a liquid water seal that prevents sewer gases, methane, and foul odors from entering the building",
                "To speed up water drainage by creating a siphon",
                "To collect valuable coins",
                "To filter drinking water"
            ),
            correctIndex = 0,
            explanation = "A P-trap holds a 2 to 4 inch column of standing water that forms a gas-tight seal, preventing toxic and explosive sewer gases from escaping into occupied postal spaces.",
            formulaOrRule = "Plumbing Code: P-trap water seal blocks sewer gas"
        ),
        Question(
            id = 120,
            category = Category.HVAC_BUILDING,
            crafts = listOf(CraftRole.BEM_9, CraftRole.AMT_9),
            questionText = "On an emergency standby generator, what component keeps the diesel engine jacket water warm (typically 100°F - 120°F) 24/7 to ensure reliable, instantaneous starting and load acceptance?",
            options = listOf(
                "Electric jacket water block heater",
                "Solar panels",
                "Exhaust gas recirculation",
                "Running the alternator continuously"
            ),
            correctIndex = 0,
            explanation = "Emergency diesel generators utilize thermostatically controlled electric block heaters to keep engine coolant hot, ensuring rapid ignition and full load pickup within 10 seconds of power failure.",
            formulaOrRule = "Standby Generator: Jacket water block heater maintains starting temp"
        )
    )

    fun getQuestionsByCategory(category: Category): List<Question> {
        return allQuestions.filter { it.category == category }
    }

    fun getQuestionsForCraft(craft: CraftRole): List<Question> {
        return allQuestions.filter { it.crafts.contains(craft) }
    }
}
