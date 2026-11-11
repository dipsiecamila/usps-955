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
import com.example.data.Category
import com.example.ui.MainViewModel
import com.example.ui.components.SpatialNetCanvas
import com.example.ui.theme.*

@Composable
fun ExamScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val examQuestions by viewModel.examQuestions.collectAsState()
    val currentIndex by viewModel.examCurrentIndex.collectAsState()
    val answers by viewModel.examAnswers.collectAsState()
    val flagged by viewModel.examFlagged.collectAsState()
    val secondsRemaining by viewModel.examSecondsRemaining.collectAsState()

    var showSubmitDialog by remember { mutableStateOf(false) }

    val currentQuestion = examQuestions.getOrNull(currentIndex)
    val isFlagged = currentQuestion != null && flagged.contains(currentQuestion.id)
    val selectedOption = currentQuestion?.let { answers[it.id] }

    val minutes = secondsRemaining / 60
    val seconds = secondsRemaining % 60
    val timeFormatted = String.format("%02d:%02d", minutes, seconds)
    val isLowTime = secondsRemaining < 300 // under 5 mins

    Column(
        modifier = modifier
            .fillMaxSize()
            .testTag("exam_screen_root")
    ) {
        // Sticky Header: Timer & Actions
        Surface(
            color = PostalNavyDark,
            tonalElevation = 4.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Timer Chip
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = if (isLowTime) SafetyRed else PostalNavyPrimary
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Timer,
                                contentDescription = "Time Remaining",
                                tint = if (isLowTime) Color.White else PostalAmberAccent,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = timeFormatted,
                                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                                color = Color.White
                            )
                        }
                    }

                    // Flag Button
                    if (currentQuestion != null) {
                        TextButton(
                            onClick = { viewModel.toggleExamFlag(currentQuestion.id) },
                            modifier = Modifier.testTag("exam_flag_button")
                        ) {
                            Icon(
                                imageVector = if (isFlagged) Icons.Default.Flag else Icons.Default.OutlinedFlag,
                                contentDescription = "Flag Question",
                                tint = if (isFlagged) PostalAmberAccent else Color.White.copy(alpha = 0.8f),
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = if (isFlagged) "Flagged" else "Flag",
                                color = if (isFlagged) PostalAmberAccent else Color.White.copy(alpha = 0.8f)
                            )
                        }
                    }

                    // Submit Button
                    Button(
                        onClick = { showSubmitDialog = true },
                        colors = ButtonDefaults.buttonColors(containerColor = PostalAmberAccent),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.testTag("exam_submit_open_dialog")
                    ) {
                        Text(
                            text = "Submit",
                            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                            color = PostalNavyDark
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Question Palette (Horizontal Chips)
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(examQuestions.indices.toList()) { idx ->
                        val q = examQuestions[idx]
                        val isAnswered = answers.containsKey(q.id)
                        val isQFlagged = flagged.contains(q.id)
                        val isCurrent = idx == currentIndex

                        val chipBg = when {
                            isCurrent -> PostalBlueLight
                            isQFlagged -> PostalAmberAccent
                            isAnswered -> SafetyGreen
                            else -> Color(0xFF334155)
                        }

                        val textColor = when {
                            isCurrent -> PostalNavyDark
                            isQFlagged -> PostalNavyDark
                            isAnswered -> Color.White
                            else -> Color.White
                        }

                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(RoundedCornerShape(6.dp))
                                .background(chipBg)
                                .clickable { viewModel.setExamCurrentIndex(idx) }
                                .testTag("exam_palette_item_$idx"),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "${idx + 1}",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = if (isCurrent) FontWeight.ExtraBold else FontWeight.Normal
                                ),
                                color = textColor
                            )
                        }
                    }
                }
            }
        }

        // Active Question Content
        if (currentQuestion != null) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Question ${currentIndex + 1} of ${examQuestions.size}",
                            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Surface(
                            shape = RoundedCornerShape(4.dp),
                            color = PostalBlueContainer
                        ) {
                            Text(
                                text = currentQuestion.category.title,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                color = PostalNavyPrimary
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = currentQuestion.questionText,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.SemiBold,
                            lineHeight = 24.sp
                        ),
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    if (currentQuestion.category == Category.SPATIAL) {
                        SpatialNetCanvas(modifier = Modifier.padding(bottom = 14.dp))
                    }
                }

                items(currentQuestion.options.indices.toList()) { optIdx ->
                    val optText = currentQuestion.options[optIdx]
                    val isSelected = selectedOption == optIdx

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .border(
                                width = if (isSelected) 2.dp else 1.dp,
                                color = if (isSelected) PostalBlueSecondary else MaterialTheme.colorScheme.outline.copy(alpha = 0.3f),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .clip(RoundedCornerShape(12.dp))
                            .clickable {
                                viewModel.selectExamAnswer(currentQuestion.id, optIdx)
                            }
                            .testTag("exam_option_$optIdx"),
                        colors = CardDefaults.cardColors(
                            containerColor = if (isSelected) PostalBlueContainer.copy(alpha = 0.5f) else MaterialTheme.colorScheme.surface
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .clip(CircleShape)
                                    .background(
                                        if (isSelected) PostalNavyPrimary else MaterialTheme.colorScheme.surfaceVariant
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = ('A' + optIdx).toString(),
                                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                                    color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }

                            Spacer(modifier = Modifier.width(12.dp))

                            Text(
                                text = optText,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                ),
                                color = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }
        }

        // Bottom Navigation Bar
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 8.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedButton(
                    onClick = { viewModel.setExamCurrentIndex(currentIndex - 1) },
                    enabled = currentIndex > 0,
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.testTag("exam_prev_btn")
                ) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Prev")
                }

                Text(
                    text = "${answers.size}/${examQuestions.size} Answered",
                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Button(
                    onClick = { viewModel.setExamCurrentIndex(currentIndex + 1) },
                    enabled = currentIndex < examQuestions.size - 1,
                    colors = ButtonDefaults.buttonColors(containerColor = PostalNavyPrimary),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.testTag("exam_next_btn")
                ) {
                    Text("Next")
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, modifier = Modifier.size(16.dp))
                }
            }
        }
    }

    // Submit Confirmation Dialog
    if (showSubmitDialog) {
        val answeredCount = answers.size
        val totalCount = examQuestions.size
        val unansweredCount = totalCount - answeredCount

        AlertDialog(
            onDismissRequest = { showSubmitDialog = false },
            title = {
                Text(
                    text = "Submit USPS 955 Mock Exam?",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
            },
            text = {
                Column {
                    Text("Are you ready to submit your exam for grading?")
                    Spacer(modifier = Modifier.height(12.dp))
                    Text("• Answered: $answeredCount of $totalCount")
                    if (unansweredCount > 0) {
                        Text(
                            text = "• Unanswered: $unansweredCount question(s)",
                            color = SafetyRed,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Text("• Flagged for review: ${flagged.size}")
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        showSubmitDialog = false
                        viewModel.submitExam()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = PostalNavyPrimary),
                    modifier = Modifier.testTag("exam_confirm_submit_btn")
                ) {
                    Text("Grade Exam")
                }
            },
            dismissButton = {
                TextButton(onClick = { showSubmitDialog = false }) {
                    Text("Continue Exam")
                }
            }
        )
    }
}
