package com.example.data

object QuestionBank {

    val allQuestions: List<Question> = listOf(
        // ELECTRONICS & ELECTRICITY
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
            options = listOf(
                "NOR gate",
                "NAND gate",
                "XOR gate",
                "AND gate"
            ),
            correctIndex = 1,
            explanation = "A NAND (NOT-AND) gate produces a HIGH output if any of its inputs are LOW. It only outputs a LOW when every input is HIGH (truth table: 1,1 -> 0; all other combinations -> 1).",
            formulaOrRule = "NAND: Y = NOT(A AND B)"
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
            options = listOf(
                "0010 1111",
                "0011 1110",
                "0010 1101",
                "0001 1111"
            ),
            correctIndex = 0,
            explanation = "Hexadecimal digit '2' in 4-bit binary is 0010. Hexadecimal digit 'F' (decimal 15) in binary is 1111 (8+4+2+1). Combining the nibbles gives 0010 1111.",
            formulaOrRule = "Hex '2' = 0010, Hex 'F' = 1111 -> 0010 1111"
        ),

        // MECHANICAL & POWER TRANSMISSION
        Question(
            id = 9,
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
            id = 10,
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
            id = 11,
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
            id = 12,
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
            id = 13,
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
            id = 14,
            category = Category.MECHANICAL,
            crafts = listOf(CraftRole.MM_7, CraftRole.MPE_9),
            questionText = "In a multi-pulley block and tackle system with 4 supporting rope strands holding a suspended 800 lb load, what pulling force (effort) is required to hold the load (ignoring friction)?",
            options = listOf(
                "800 lbs",
                "400 lbs",
                "200 lbs",
                "100 lbs"
            ),
            correctIndex = 2,
            explanation = "Mechanical Advantage (MA) equals the number of rope strands directly supporting the movable load pulley. Here MA = 4. Ideal Effort = Load / MA = 800 lbs / 4 = 200 lbs.",
            formulaOrRule = "Effort = Load / Supporting Strands = 800 / 4 = 200 lbs"
        ),

        // HYDRAULICS & PNEUMATICS (FLUID POWER)
        Question(
            id = 15,
            category = Category.FLUID_POWER,
            crafts = listOf(CraftRole.MPE_9, CraftRole.BEM_9, CraftRole.MM_7),
            questionText = "According to Pascal's Law, if 100 psi of pressure is supplied to a hydraulic cylinder with a piston diameter of 4 inches, what is the theoretical thrust force generated on extension?",
            options = listOf(
                "400 lbs",
                "1,257 lbs",
                "800 lbs",
                "314 lbs"
            ),
            correctIndex = 1,
            explanation = "Piston Area A = π × r² = π × (2 in)² ≈ 3.1416 × 4 = 12.57 sq inches. Force F = Pressure × Area = 100 psi × 12.57 sq in = 1,257 lbs.",
            formulaOrRule = "F = P × A; Area = π × (d/2)²"
        ),
        Question(
            id = 16,
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
            id = 17,
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
            id = 18,
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

        // MAINTENANCE & TROUBLESHOOTING METHODOLOGY
        Question(
            id = 19,
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
            id = 20,
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
            id = 21,
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
            id = 22,
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

        // SPATIAL RELATIONS (USPS 955 EXAM BENCHMARK SECTION)
        Question(
            id = 23,
            category = Category.SPATIAL,
            crafts = listOf(CraftRole.MM_7, CraftRole.MPE_9, CraftRole.ET_10, CraftRole.BEM_9, CraftRole.AMT_9),
            questionText = "A standard 2D cross net for a cube has 6 faces: Top, Bottom, and 4 wrap-around sides (Front, Right, Back, Left). If the Front face is labeled 'F' and the Right face is labeled 'R', which face will be OPPOSITE to the Front face when folded into a 3D box?",
            options = listOf(
                "The Left face",
                "The Back face",
                "The Top face",
                "The Right face"
            ),
            correctIndex = 1,
            explanation = "When a standard cube net is folded, opposite faces can never touch along an edge. The four side faces wrap in sequence: Front -> Right -> Back -> Left. Thus Front is directly opposite to Back, Left is opposite to Right, and Top is opposite to Bottom.",
            formulaOrRule = "Cube folding rule: Opposite faces never share an edge",
            diagramType = "CUBE_NET_CROSS",
            diagramData = "TOP:T | SIDES: L, F, R, B | BOTTOM:D"
        ),
        Question(
            id = 24,
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
            id = 25,
            category = Category.SPATIAL,
            crafts = listOf(CraftRole.MM_7, CraftRole.MPE_9, CraftRole.ET_10, CraftRole.BEM_9),
            questionText = "In an orthographic projection of a machine mounting bracket, the Top View shows a circle and the Front View shows a plain rectangle. What 3D geometric shape is represented?",
            options = listOf(
                "A rectangular prism",
                "A solid cylinder",
                "A sphere",
                "A triangular pyramid"
            ),
            correctIndex = 1,
            explanation = "A right circular cylinder projects as a circle when viewed from the end (Top View) and as a rectangle when viewed from the side (Front View).",
            formulaOrRule = "Orthographic: Circle top + Rectangle front = Cylinder"
        ),
        Question(
            id = 26,
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

        // HVAC & PLANT/BUILDING SYSTEMS (BEM & AMT DOMAIN)
        Question(
            id = 27,
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
            id = 28,
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
            id = 29,
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
            id = 30,
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
        )
    )

    fun getQuestionsByCategory(category: Category): List<Question> {
        return allQuestions.filter { it.category == category }
    }

    fun getQuestionsForCraft(craft: CraftRole): List<Question> {
        return allQuestions.filter { it.crafts.contains(craft) }
    }
}
