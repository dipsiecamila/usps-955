package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.CraftRole
import com.example.data.ExamAttempt
import com.example.ui.MainViewModel
import com.example.ui.Screen
import com.example.ui.theme.*

@Composable
fun ExamResultScreen(
    result: ExamAttempt,
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val isOverallPassed = result.scorePercentage >= 70

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .testTag("exam_result_screen"),
        contentPadding = PaddingValues(vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Score Summary Header Card
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (isOverallPassed) SafetyGreenContainer.copy(alpha = 0.5f) else SafetyRedContainer.copy(alpha = 0.5f)
                )
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(72.dp)
                            .clip(CircleShape)
                            .background(if (isOverallPassed) SafetyGreen else SafetyRed),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = if (isOverallPassed) Icons.Default.Check else Icons.Default.Close,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(40.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "${result.scorePercentage}%",
                        style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.ExtraBold),
                        color = if (isOverallPassed) SafetyGreen else SafetyRed
                    )

                    Text(
                        text = if (isOverallPassed) "EXAM PASSED (QUALIFIED)" else "INELIGIBLE - BELOW 70% CUTOFF",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = if (isOverallPassed) SafetyGreen else SafetyRed
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "${result.correctCount} of ${result.totalQuestions} Questions Correct • Time: ${result.durationSeconds / 60}m ${result.durationSeconds % 60}s",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        // Craft Qualification Matrix
        item {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Postal Craft Qualification Matrix",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "Official USPS 955 standard passing score threshold is 70.0%",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(10.dp))
        }

        items(CraftRole.entries) { craft ->
            val isQualified = result.scorePercentage >= craft.passingScore
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = craft.code,
                                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                                color = PostalNavyPrimary
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "(${craft.title})",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        Text(
                            text = "Benchmark: ${craft.passingScore}% passing score",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = if (isQualified) SafetyGreenContainer else SafetyRedContainer
                    ) {
                        Text(
                            text = if (isQualified) "ELIGIBLE" else "NOT QUALIFIED",
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                            color = if (isQualified) SafetyGreen else SafetyRed
                        )
                    }
                }
            }
        }

        // Action Buttons
        item {
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = { viewModel.startExam() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .testTag("result_retake_exam_btn"),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PostalNavyPrimary)
            ) {
                Icon(Icons.Default.Refresh, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Retake Mock Exam", style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold))
            }

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedButton(
                onClick = { viewModel.startPractice(null) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .testTag("result_review_practice_btn"),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(Icons.Default.MenuBook, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Review Explanations in Practice Mode")
            }

            Spacer(modifier = Modifier.height(10.dp))

            TextButton(
                onClick = { viewModel.navigateTo(Screen.Home) },
                modifier = Modifier.testTag("result_home_btn")
            ) {
                Text("Return to Dashboard")
            }
        }
    }
}
