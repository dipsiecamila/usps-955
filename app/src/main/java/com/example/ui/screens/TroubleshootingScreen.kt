package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import com.example.data.TroubleshootingData
import com.example.ui.MainViewModel
import com.example.ui.theme.*

@Composable
fun TroubleshootingScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val scenarioIndex by viewModel.currentScenarioIndex.collectAsState()
    val stepIndex by viewModel.currentScenarioStep.collectAsState()
    val selectedOption by viewModel.scenarioSelectedOption.collectAsState()
    val isSubmitted by viewModel.scenarioStepSubmitted.collectAsState()
    val isCompleted by viewModel.scenarioCompleted.collectAsState()

    val scenarios = TroubleshootingData.scenarios
    val currentScenario = scenarios.getOrNull(scenarioIndex) ?: return
    val currentStep = currentScenario.steps.getOrNull(stepIndex)

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .testTag("troubleshooting_screen_root"),
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        // Scenario Selection Tabs
        item {
            Text(
                text = "Postal Maintenance Diagnostic Cases",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(8.dp))

            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(scenarios.indices.toList()) { idx ->
                    val sc = scenarios[idx]
                    val isSelected = idx == scenarioIndex
                    FilterChip(
                        selected = isSelected,
                        onClick = { viewModel.selectScenario(idx) },
                        label = { Text(sc.machine.split(" ").first()) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = PostalNavyPrimary,
                            selectedLabelColor = Color.White
                        ),
                        modifier = Modifier.testTag("troubleshoot_case_tab_$idx")
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))
        }

        // Case Overview Card
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = PostalNavyPrimary)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            shape = RoundedCornerShape(4.dp),
                            color = PostalAmberAccent
                        ) {
                            Text(
                                text = currentScenario.machine,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.ExtraBold),
                                color = PostalNavyDark
                            )
                        }

                        Text(
                            text = "Step ${stepIndex + 1} of ${currentScenario.steps.size}",
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                            color = Color.White.copy(alpha = 0.8f)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Reported Symptom:",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White.copy(alpha = 0.7f)
                    )
                    Text(
                        text = currentScenario.reportedSymptom,
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Initial Conditions: ${currentScenario.initialConditions}",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White.copy(alpha = 0.85f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        // Active Step Content
        if (currentStep != null && !isCompleted) {
            item {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Handyman,
                            contentDescription = null,
                            tint = PostalNavyPrimary,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = currentStep.stepTitle,
                                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                                color = PostalNavyPrimary
                            )
                            Text(
                                text = "Tool: ${currentStep.tool}",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = currentStep.description,
                    style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 22.sp),
                    color = MaterialTheme.colorScheme.onBackground
                )

                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Select Diagnostic Action:",
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(6.dp))
            }

            // Options
            items(currentStep.testOptions.indices.toList()) { optIdx ->
                val optText = currentStep.testOptions[optIdx]
                val isSelected = selectedOption == optIdx
                val isCorrect = optIdx == currentStep.correctOption

                val bg = when {
                    !isSubmitted -> if (isSelected) PostalBlueContainer.copy(alpha = 0.5f) else MaterialTheme.colorScheme.surface
                    isCorrect -> SafetyGreenContainer.copy(alpha = 0.8f)
                    isSelected && !isCorrect -> SafetyRedContainer.copy(alpha = 0.8f)
                    else -> MaterialTheme.colorScheme.surface
                }

                val border = when {
                    !isSubmitted -> if (isSelected) PostalBlueSecondary else MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
                    isCorrect -> SafetyGreen
                    isSelected && !isCorrect -> SafetyRed
                    else -> MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .border(1.5.dp, border, RoundedCornerShape(12.dp))
                        .clip(RoundedCornerShape(12.dp))
                        .clickable(enabled = !isSubmitted) {
                            viewModel.selectScenarioOption(optIdx)
                        }
                        .testTag("troubleshoot_option_$optIdx"),
                    colors = CardDefaults.cardColors(containerColor = bg),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape)
                                .background(
                                    when {
                                        isSubmitted && isCorrect -> SafetyGreen
                                        isSubmitted && isSelected -> SafetyRed
                                        isSelected -> PostalNavyPrimary
                                        else -> MaterialTheme.colorScheme.surfaceVariant
                                    }
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            val letter = ('1' + optIdx).toString()
                            Text(
                                text = letter,
                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                color = if (isSelected || (isSubmitted && isCorrect)) Color.White else MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Text(
                            text = optText,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }

            // Action / Submit button
            item {
                Spacer(modifier = Modifier.height(14.dp))
                if (!isSubmitted) {
                    Button(
                        onClick = { viewModel.submitScenarioStep() },
                        enabled = selectedOption != null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .testTag("troubleshoot_perform_test_btn"),
                        colors = ButtonDefaults.buttonColors(containerColor = PostalNavyPrimary),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Icon(Icons.Default.PlayArrow, contentDescription = null)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Perform Diagnostic Test")
                    }
                } else {
                    // Finding Revelation Card
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (selectedOption == currentStep.correctOption) SafetyGreenContainer.copy(alpha = 0.5f) else SafetyRedContainer.copy(alpha = 0.5f)
                        )
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = if (selectedOption == currentStep.correctOption) Icons.Default.CheckCircle else Icons.Default.Cancel,
                                    contentDescription = null,
                                    tint = if (selectedOption == currentStep.correctOption) SafetyGreen else SafetyRed,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = if (selectedOption == currentStep.correctOption) "Test Finding Verified" else "Incorrect Diagnostic Action",
                                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                                    color = if (selectedOption == currentStep.correctOption) SafetyGreen else SafetyRed
                                )
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = currentStep.findingExplanation,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    if (selectedOption == currentStep.correctOption && stepIndex < currentScenario.steps.size - 1) {
                        Button(
                            onClick = { viewModel.advanceScenarioStep() },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                                .testTag("troubleshoot_next_step_btn"),
                            colors = ButtonDefaults.buttonColors(containerColor = PostalNavyPrimary),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text("Advance to Next Step")
                            Spacer(modifier = Modifier.width(6.dp))
                            Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, modifier = Modifier.size(16.dp))
                        }
                    }
                }
            }
        }

        // Case Completed Card
        if (isCompleted) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = SafetyGreenContainer.copy(alpha = 0.6f))
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.Verified,
                            contentDescription = null,
                            tint = SafetyGreen,
                            modifier = Modifier.size(48.dp)
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "Machine Repaired & Commissioned!",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold),
                            color = SafetyGreen
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "Root Cause:",
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = currentScenario.rootCauseSummary,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = MaterialTheme.colorScheme.surface
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(
                                    text = "Preventative Action:",
                                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                    color = PostalNavyPrimary
                                )
                                Text(
                                    text = currentScenario.preventativeAction,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = {
                                val nextIdx = (scenarioIndex + 1) % scenarios.size
                                viewModel.selectScenario(nextIdx)
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = PostalNavyPrimary),
                            modifier = Modifier.testTag("troubleshoot_next_case_btn")
                        ) {
                            Text("Next Diagnostic Case")
                        }
                    }
                }
            }
        }
    }
}
