package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.LotoDrillScenario
import com.example.data.ReferenceData
import com.example.ui.MainViewModel
import com.example.ui.theme.*

private enum class ReferenceTab(val title: String) {
    FORMULAS("Formulas"),
    TRUTH_TABLES("Logic Gates"),
    LOTO_STEPS("LOTO Safety"),
    LOTO_DRILL("LOTO Drills"),
    RESISTOR_CALC("Resistor Calc"),
    BOLT_GRADES("Bolt Specs")
}

@Composable
fun ReferenceScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    var selectedTab by remember { mutableStateOf(ReferenceTab.FORMULAS) }

    val band1 by viewModel.resistorBand1.collectAsState()
    val band2 by viewModel.resistorBand2.collectAsState()
    val mult by viewModel.resistorMultiplier.collectAsState()
    val tol by viewModel.resistorTolerance.collectAsState()

    // LOTO Interactive Drill State
    var drillIndex by remember { mutableIntStateOf(0) }
    val scenarios = ReferenceData.lotoDrillScenarios
    val currentScenario = scenarios[drillIndex.coerceIn(0, scenarios.size - 1)]

    var currentDrillItems by remember(drillIndex) {
        // Every time scenario changes, shuffle distinctly
        mutableStateOf(currentScenario.correctOrderedSteps.shuffled())
    }
    var isDrillValidated by remember(drillIndex) { mutableStateOf(false) }

    val resistorColors = listOf(
        Pair("Black", Color(0xFF1E293B)),
        Pair("Brown", Color(0xFF78350F)),
        Pair("Red", Color(0xFFDC2626)),
        Pair("Orange", Color(0xFFEA580C)),
        Pair("Yellow", Color(0xFFCA8A04)),
        Pair("Green", Color(0xFF16A34A)),
        Pair("Blue", Color(0xFF2563EB)),
        Pair("Violet", Color(0xFF7C3AED)),
        Pair("Gray", Color(0xFF64748B)),
        Pair("White", Color(0xFFF8FAFC))
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .testTag("reference_screen_root")
    ) {
        // Tab Row
        ScrollableTabRow(
            selectedTabIndex = selectedTab.ordinal,
            containerColor = PostalNavyDark,
            contentColor = Color.White,
            edgePadding = 12.dp
        ) {
            ReferenceTab.entries.forEach { tab ->
                Tab(
                    selected = selectedTab == tab,
                    onClick = { selectedTab = tab },
                    text = {
                        Text(
                            text = tab.title,
                            fontWeight = if (selectedTab == tab) FontWeight.Bold else FontWeight.Normal,
                            color = if (selectedTab == tab) PostalAmberAccent else Color.White
                        )
                    },
                    modifier = Modifier.testTag("ref_tab_${tab.name}")
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            when (selectedTab) {
                ReferenceTab.FORMULAS -> {
                    item {
                        Text(
                            text = "USPS 955 Essential Formulas & Equations",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }

                    items(ReferenceData.formulas) { item ->
                        // Background of card section/area is the light gray that the formula previously had
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                        ) {
                            Column(modifier = Modifier.padding(14.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = item.title,
                                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                                        color = PostalNavyPrimary
                                    )
                                    Surface(
                                        shape = RoundedCornerShape(4.dp),
                                        color = PostalNavyPrimary
                                    ) {
                                        Text(
                                            text = item.category,
                                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                            color = PostalAmberAccent
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(8.dp))

                                // Formula background is light blue (PostalBlueContainer)
                                Surface(
                                    shape = RoundedCornerShape(6.dp),
                                    color = PostalBlueContainer,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(
                                        text = item.formula,
                                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            fontWeight = FontWeight.Bold,
                                            color = PostalNavyDark
                                        )
                                    )
                                }

                                Spacer(modifier = Modifier.height(6.dp))

                                Text(
                                    text = item.description,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )

                                Spacer(modifier = Modifier.height(6.dp))

                                Text(
                                    text = "Example: ${item.example}",
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        color = PostalBlueSecondary,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                )
                            }
                        }
                    }
                }

                ReferenceTab.TRUTH_TABLES -> {
                    item {
                        Text(
                            text = "Digital Logic Gates & Boolean Expressions",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Text(
                            text = "Standard Expressions with Equivalent Electronic & Programming Symbols",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }

                    items(ReferenceData.logicGates) { gate ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                        ) {
                            Column(modifier = Modifier.padding(14.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = gate.name,
                                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                                        color = PostalNavyPrimary
                                    )

                                    // Light Blue Uppercase logic tag
                                    Surface(
                                        shape = RoundedCornerShape(4.dp),
                                        color = PostalBlueContainer
                                    ) {
                                        Text(
                                            text = gate.logicTag,
                                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.ExtraBold),
                                            color = PostalNavyPrimary
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(6.dp))

                                // Expression and Alternative symbols row
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = gate.standardExpression,
                                        style = MaterialTheme.typography.titleMedium.copy(
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = "equiv:",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    // Light green alternative symbols
                                    Surface(
                                        shape = RoundedCornerShape(4.dp),
                                        color = SafetyGreenContainer.copy(alpha = 0.5f)
                                    ) {
                                        Text(
                                            text = gate.alternativeSymbols,
                                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                                            color = Color(0xFF15803D) // crisp light green
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = gate.description,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )

                                Spacer(modifier = Modifier.height(10.dp))

                                // Truth table grid
                                Surface(
                                    shape = RoundedCornerShape(6.dp),
                                    color = MaterialTheme.colorScheme.surfaceVariant,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Column(modifier = Modifier.padding(8.dp)) {
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceAround
                                        ) {
                                            Text("Input(s)", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelSmall)
                                            Text("Output Y", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelSmall)
                                        }
                                        HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
                                        gate.truthTable.forEach { (inputs, out) ->
                                            Row(
                                                modifier = Modifier.fillMaxWidth(),
                                                horizontalArrangement = Arrangement.SpaceAround
                                            ) {
                                                Text(inputs, style = MaterialTheme.typography.bodySmall)
                                                Text(
                                                    out,
                                                    style = MaterialTheme.typography.bodySmall.copy(
                                                        fontWeight = FontWeight.Bold,
                                                        color = if (out == "1") SafetyGreen else MaterialTheme.colorScheme.onSurfaceVariant
                                                    )
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                ReferenceTab.LOTO_STEPS -> {
                    item {
                        Text(
                            text = "OSHA 1910.147 Lockout/Tagout (LOTO) 7-Step Sequence",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }

                    items(ReferenceData.lotoSteps) { step ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            shape = RoundedCornerShape(10.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                // Short title in burnt amber (PostalAmberAccent), except verification in red
                                Text(
                                    text = step.shortTitle,
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = if (step.isVerificationStep) SafetyRed else PostalAmberAccent
                                    )
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = step.detail,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }

                ReferenceTab.LOTO_DRILL -> {
                    item {
                        // Scenario Card
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = PostalNavyPrimary)
                        ) {
                            Column(modifier = Modifier.padding(14.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "Scenario ${drillIndex + 1} of ${scenarios.size}",
                                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                        color = PostalAmberAccent
                                    )
                                    Surface(
                                        shape = RoundedCornerShape(4.dp),
                                        color = PostalBlueContainer
                                    ) {
                                        Text(
                                            text = currentScenario.equipmentName,
                                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                            color = PostalNavyDark
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(6.dp))

                                Text(
                                    text = currentScenario.title,
                                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                                    color = Color.White
                                )

                                Text(
                                    text = currentScenario.description,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color.White.copy(alpha = 0.85f)
                                )

                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = "Reorder the shuffled steps into the correct OSHA LOTO order, then tap 'Validar':",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = PostalAmberAccent
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))
                    }

                    // Reorderable Steps List
                    itemsIndexed(currentDrillItems) { idx, itemText ->
                        val isCorrectPosition = currentScenario.correctOrderedSteps.getOrNull(idx) == itemText

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            shape = RoundedCornerShape(10.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = if (isDrillValidated) {
                                    if (isCorrectPosition) SafetyGreenContainer.copy(alpha = 0.5f) else SafetyRedContainer.copy(alpha = 0.5f)
                                } else {
                                    MaterialTheme.colorScheme.surface
                                }
                            ),
                            border = if (isDrillValidated) {
                                androidx.compose.foundation.BorderStroke(1.5.dp, if (isCorrectPosition) SafetyGreen else SafetyRed)
                            } else null
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                // Position indicator
                                Box(
                                    modifier = Modifier
                                        .size(28.dp)
                                        .clip(CircleShape)
                                        .background(
                                            if (isDrillValidated) {
                                                if (isCorrectPosition) SafetyGreen else SafetyRed
                                            } else {
                                                PostalNavyPrimary
                                            }
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "${idx + 1}",
                                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                        color = Color.White
                                    )
                                }

                                Spacer(modifier = Modifier.width(10.dp))

                                Text(
                                    text = itemText,
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        fontWeight = if (isDrillValidated && isCorrectPosition) FontWeight.Bold else FontWeight.Normal
                                    ),
                                    color = MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.weight(1f)
                                )

                                // Move Up & Down controls for tactile reordering
                                Column {
                                    IconButton(
                                        onClick = {
                                            if (idx > 0) {
                                                val list = currentDrillItems.toMutableList()
                                                val temp = list[idx]
                                                list[idx] = list[idx - 1]
                                                list[idx - 1] = temp
                                                currentDrillItems = list
                                                isDrillValidated = false
                                            }
                                        },
                                        enabled = idx > 0,
                                        modifier = Modifier.size(26.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.ArrowDropUp,
                                            contentDescription = "Move Up",
                                            tint = if (idx > 0) PostalNavyPrimary else Color.LightGray
                                        )
                                    }

                                    IconButton(
                                        onClick = {
                                            if (idx < currentDrillItems.size - 1) {
                                                val list = currentDrillItems.toMutableList()
                                                val temp = list[idx]
                                                list[idx] = list[idx + 1]
                                                list[idx + 1] = temp
                                                currentDrillItems = list
                                                isDrillValidated = false
                                            }
                                        },
                                        enabled = idx < currentDrillItems.size - 1,
                                        modifier = Modifier.size(26.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.ArrowDropDown,
                                            contentDescription = "Move Down",
                                            tint = if (idx < currentDrillItems.size - 1) PostalNavyPrimary else Color.LightGray
                                        )
                                    }
                                }
                            }
                        }
                    }

                    // Navigation Controls: Prev, Validar in Center, Next (with matching OutlinedButton styles!)
                    item {
                        Spacer(modifier = Modifier.height(14.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            OutlinedButton(
                                onClick = {
                                    if (drillIndex > 0) {
                                        drillIndex -= 1
                                        currentDrillItems = scenarios[drillIndex].correctOrderedSteps.shuffled()
                                        isDrillValidated = false
                                    }
                                },
                                enabled = drillIndex > 0,
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier.testTag("loto_prev_btn")
                            ) {
                                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Prev")
                            }

                            // Center Validate Button
                            Button(
                                onClick = { isDrillValidated = true },
                                colors = ButtonDefaults.buttonColors(containerColor = PostalAmberAccent),
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier.testTag("loto_validate_btn")
                            ) {
                                Icon(Icons.Default.Check, contentDescription = null, tint = PostalNavyDark, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Validar", color = PostalNavyDark, fontWeight = FontWeight.Bold)
                            }

                            // Next Button: EXACT SAME STYLING AS PREV (OutlinedButton)
                            OutlinedButton(
                                onClick = {
                                    if (drillIndex < scenarios.size - 1) {
                                        drillIndex += 1
                                        currentDrillItems = scenarios[drillIndex].correctOrderedSteps.shuffled()
                                        isDrillValidated = false
                                    }
                                },
                                enabled = drillIndex < scenarios.size - 1,
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier.testTag("loto_next_btn")
                            ) {
                                Text("Next")
                                Spacer(modifier = Modifier.width(4.dp))
                                Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, modifier = Modifier.size(16.dp))
                            }
                        }
                    }
                }

                ReferenceTab.RESISTOR_CALC -> {
                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.cardColors(containerColor = PostalNavyPrimary)
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "4-Band Resistor Value Calculator",
                                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                    color = Color.White
                                )
                                Spacer(modifier = Modifier.height(8.dp))

                                // Display calculated value
                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = PostalAmberAccent
                                ) {
                                    Text(
                                        text = viewModel.calculateResistanceValue(),
                                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.ExtraBold),
                                        color = PostalNavyDark
                                    )
                                }

                                Spacer(modifier = Modifier.height(14.dp))

                                // Visual Resistor Graphic
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(36.dp)
                                        .clip(RoundedCornerShape(18.dp))
                                        .background(Color(0xFFE2E8F0))
                                        .padding(horizontal = 24.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(modifier = Modifier.size(width = 12.dp, height = 36.dp).background(resistorColors[band1].second))
                                    Box(modifier = Modifier.size(width = 12.dp, height = 36.dp).background(resistorColors[band2].second))
                                    Box(modifier = Modifier.size(width = 12.dp, height = 36.dp).background(resistorColors[mult].second))
                                    Box(modifier = Modifier.size(width = 12.dp, height = 36.dp).background(if (tol == 5) Color(0xFFD4AF37) else Color(0xFFC0C0C0)))
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Band 1 Picker
                        Text("Band 1 (1st Digit): ${resistorColors[band1].first} ($band1)", fontWeight = FontWeight.Bold)
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp), modifier = Modifier.padding(vertical = 6.dp)) {
                            items(resistorColors.indices.toList()) { idx ->
                                Box(
                                    modifier = Modifier
                                        .size(36.dp)
                                        .clip(CircleShape)
                                        .background(resistorColors[idx].second)
                                        .border(if (idx == band1) 3.dp else 1.dp, if (idx == band1) PostalAmberAccent else Color.Gray, CircleShape)
                                        .clickable { viewModel.setResistorBand1(idx) },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text("$idx", color = if (idx in listOf(0, 1, 2, 6, 7)) Color.White else Color.Black, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // Band 2 Picker
                        Text("Band 2 (2nd Digit): ${resistorColors[band2].first} ($band2)", fontWeight = FontWeight.Bold)
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp), modifier = Modifier.padding(vertical = 6.dp)) {
                            items(resistorColors.indices.toList()) { idx ->
                                Box(
                                    modifier = Modifier
                                        .size(36.dp)
                                        .clip(CircleShape)
                                        .background(resistorColors[idx].second)
                                        .border(if (idx == band2) 3.dp else 1.dp, if (idx == band2) PostalAmberAccent else Color.Gray, CircleShape)
                                        .clickable { viewModel.setResistorBand2(idx) },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text("$idx", color = if (idx in listOf(0, 1, 2, 6, 7)) Color.White else Color.Black, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // Multiplier Picker
                        Text("Multiplier (10^x): ${resistorColors[mult].first} (×10^$mult)", fontWeight = FontWeight.Bold)
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp), modifier = Modifier.padding(vertical = 6.dp)) {
                            items((0..6).toList()) { idx ->
                                Box(
                                    modifier = Modifier
                                        .size(36.dp)
                                        .clip(CircleShape)
                                        .background(resistorColors[idx].second)
                                        .border(if (idx == mult) 3.dp else 1.dp, if (idx == mult) PostalAmberAccent else Color.Gray, CircleShape)
                                        .clickable { viewModel.setResistorMultiplier(idx) },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text("10^$idx", color = if (idx in listOf(0, 1, 2, 6)) Color.White else Color.Black, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // Tolerance Picker
                        Text("Tolerance: ±$tol%", fontWeight = FontWeight.Bold)
                        Row(horizontalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.padding(vertical = 6.dp)) {
                            FilterChip(
                                selected = tol == 5,
                                onClick = { viewModel.setResistorTolerance(5) },
                                label = { Text("Gold (±5%)") }
                            )
                            FilterChip(
                                selected = tol == 10,
                                onClick = { viewModel.setResistorTolerance(10) },
                                label = { Text("Silver (±10%)") }
                            )
                        }
                    }
                }

                ReferenceTab.BOLT_GRADES -> {
                    item {
                        Text(
                            text = "Standard Fastener & Bolt Grade Identification",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        val boltData = listOf(
                            Triple("SAE Grade 2", "No radial marks on head", "Tensile Strength: 74,000 psi. Low/medium carbon steel for light structural use."),
                            Triple("SAE Grade 5", "3 radial lines (120° apart)", "Tensile Strength: 120,000 psi. Quenched and tempered medium carbon steel. Common industrial standard."),
                            Triple("SAE Grade 8", "6 radial lines around perimeter", "Tensile Strength: 150,000 psi. Quenched and tempered alloy steel. High-stress sorting machinery."),
                            Triple("Metric Class 8.8", "Stamped '8.8' on head", "Tensile: 800 MPa (116,000 psi). Standard medium strength metric bolt."),
                            Triple("Metric Class 10.9", "Stamped '10.9' on head", "Tensile: 1,040 MPa (150,000 psi). High tensile alloy steel equivalent to Grade 8.")
                        )

                        boltData.forEach { (grade, markings, desc) ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp),
                                shape = RoundedCornerShape(10.dp),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                            ) {
                                Column(modifier = Modifier.padding(12.dp)) {
                                    Text(
                                        text = grade,
                                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                                        color = PostalNavyPrimary
                                    )
                                    Text(
                                        text = "Head Marking: $markings",
                                        style = MaterialTheme.typography.bodySmall.copy(
                                            fontWeight = FontWeight.SemiBold,
                                            color = PostalAmberAccent
                                        )
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = desc,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
