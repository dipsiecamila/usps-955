package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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

    val correctCount = examQuestions.count { q -> answers[q.id] == q.correctIndex }
    val answeredCount = answers.size
    val incorrectCount = answeredCount - correctCount

    Column(
        modifier = modifier
            .fillMaxSize()
            .testTag("exam_screen_root")
    ) {
        // Sticky Header: Timer, Instant Accuracy Stats & Submit
        Surface(
            color = PostalNavyDark,
            tonalElevation = 4.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp, vertical = 8.dp)
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
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Timer,
                                contentDescription = "Time Remaining",
                                tint = if (isLowTime) Color.White else PostalAmberAccent,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = timeFormatted,
                                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                                color = Color.White
                            )
                        }
                    }

                    // Real-time Good/Bad Score Indicator
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = SafetyGreenContainer
                        ) {
                            Text(
                                text = "✓ $correctCount",
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp),
                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.ExtraBold),
                                color = SafetyGreen
                            )
                        }
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = SafetyRedContainer
                        ) {
                            Text(
                                text = "✗ $incorrectCount",
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp),
                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.ExtraBold),
                                color = SafetyRed
                            )
                        }
                    }

                    // Flag Button
                    if (currentQuestion != null) {
                        IconButton(
                            onClick = { viewModel.toggleExamFlag(currentQuestion.id) },
                            modifier = Modifier.size(36.dp).testTag("exam_flag_button")
                        ) {
                            Icon(
                                imageVector = if (isFlagged) Icons.Default.Flag else Icons.Default.OutlinedFlag,
                                contentDescription = "Flag Question",
                                tint = if (isFlagged) PostalAmberAccent else Color.White.copy(alpha = 0.8f),
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }

                    // Submit Button
                    Button(
                        onClick = { showSubmitDialog = true },
                        colors = ButtonDefaults.buttonColors(containerColor = PostalAmberAccent),
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
                        modifier = Modifier.testTag("exam_submit_open_dialog")
                    ) {
                        Text(
                            text = "Submit",
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                            color = PostalNavyDark
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Question Boxes (Exact count = number of questions, e.g. 30 boxes in rows of 10)
                // Color coded immediately: Green = Bueno, Red = Malo, Dark = Unanswered
                val totalQ = examQuestions.size
                val columnsPerRow = 10
                val totalRows = (totalQ + columnsPerRow - 1) / columnsPerRow

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    for (row in 0 until totalRows) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            for (col in 0 until columnsPerRow) {
                                val idx = row * columnsPerRow + col
                                if (idx < totalQ) {
                                    val q = examQuestions[idx]
                                    val userAns = answers[q.id]
                                    val isAnswered = userAns != null
                                    val isCorrect = isAnswered && userAns == q.correctIndex
                                    val isQFlagged = flagged.contains(q.id)
                                    val isCurrent = idx == currentIndex

                                    val boxBg = when {
                                        !isAnswered -> Color(0xFF334155) // Unanswered neutral
                                        isCorrect -> SafetyGreen          // Bueno
                                        else -> SafetyRed                 // Malo
                                    }

                                    Box(
                                        modifier = Modifier
                                            .weight(1f)
                                            .height(24.dp)
                                            .clip(RoundedCornerShape(4.dp))
                                            .background(boxBg)
                                            .border(
                                                width = if (isCurrent) 2.dp else if (isQFlagged) 1.5.dp else 0.dp,
                                                color = if (isCurrent) Color.White else if (isQFlagged) PostalAmberAccent else Color.Transparent,
                                                shape = RoundedCornerShape(4.dp)
                                            )
                                            .clickable { viewModel.setExamCurrentIndex(idx) }
                                            .testTag("exam_box_$idx"),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        if (isAnswered) {
                                            Text(
                                                text = if (isCorrect) "✓" else "✗",
                                                style = MaterialTheme.typography.labelSmall.copy(
                                                    fontWeight = FontWeight.ExtraBold,
                                                    fontSize = 11.sp
                                                ),
                                                color = Color.White
                                            )
                                        } else {
                                            Text(
                                                text = "${idx + 1}",
                                                style = MaterialTheme.typography.labelSmall.copy(
                                                    fontWeight = if (isCurrent) FontWeight.Bold else FontWeight.Normal,
                                                    fontSize = 10.sp
                                                ),
                                                color = if (isCurrent) Color.White else Color.White.copy(alpha = 0.8f)
                                            )
                                        }
                                    }
                                } else {
                                    Spacer(modifier = Modifier.weight(1f))
                                }
                            }
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
                contentPadding = PaddingValues(vertical = 14.dp)
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

                    Spacer(modifier = Modifier.height(12.dp))

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
                    val hasAnswered = selectedOption != null
                    val isOptionCorrect = optIdx == currentQuestion.correctIndex

                    val cardBg = when {
                        !hasAnswered -> MaterialTheme.colorScheme.surface
                        isSelected && isOptionCorrect -> SafetyGreenContainer.copy(alpha = 0.8f)
                        isSelected && !isOptionCorrect -> SafetyRedContainer.copy(alpha = 0.8f)
                        isOptionCorrect -> SafetyGreenContainer.copy(alpha = 0.4f)
                        else -> MaterialTheme.colorScheme.surface
                    }

                    val borderColor = when {
                        !hasAnswered -> if (isSelected) PostalBlueSecondary else MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
                        isSelected && isOptionCorrect -> SafetyGreen
                        isSelected && !isOptionCorrect -> SafetyRed
                        isOptionCorrect -> SafetyGreen.copy(alpha = 0.5f)
                        else -> MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)
                    }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .border(width = 1.5.dp, color = borderColor, shape = RoundedCornerShape(12.dp))
                            .clip(RoundedCornerShape(12.dp))
                            .clickable {
                                viewModel.selectExamAnswer(currentQuestion.id, optIdx)
                            }
                            .testTag("exam_option_$optIdx"),
                        colors = CardDefaults.cardColors(containerColor = cardBg),
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
                                        when {
                                            hasAnswered && isSelected && isOptionCorrect -> SafetyGreen
                                            hasAnswered && isSelected && !isOptionCorrect -> SafetyRed
                                            hasAnswered && isOptionCorrect -> SafetyGreen.copy(alpha = 0.6f)
                                            isSelected -> PostalNavyPrimary
                                            else -> MaterialTheme.colorScheme.surfaceVariant
                                        }
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                if (hasAnswered && isOptionCorrect) {
                                    Icon(
                                        imageVector = Icons.Default.Check,
                                        contentDescription = "Correct",
                                        tint = Color.White,
                                        modifier = Modifier.size(16.dp)
                                    )
                                } else if (hasAnswered && isSelected && !isOptionCorrect) {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = "Incorrect",
                                        tint = Color.White,
                                        modifier = Modifier.size(16.dp)
                                    )
                                } else {
                                    Text(
                                        text = ('A' + optIdx).toString(),
                                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                                        color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.width(12.dp))

                            Text(
                                text = optText,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = if (isSelected || (hasAnswered && isOptionCorrect)) FontWeight.Bold else FontWeight.Normal
                                ),
                                color = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }

                // If answered, show instant feedback banner so the user knows why
                if (selectedOption != null) {
                    item {
                        Spacer(modifier = Modifier.height(10.dp))
                        val isUserCorrect = selectedOption == currentQuestion.correctIndex
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = if (isUserCorrect) SafetyGreenContainer.copy(alpha = 0.6f) else SafetyRedContainer.copy(alpha = 0.6f),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier.padding(10.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = if (isUserCorrect) Icons.Default.CheckCircle else Icons.Default.Cancel,
                                    contentDescription = null,
                                    tint = if (isUserCorrect) SafetyGreen else SafetyRed,
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = if (isUserCorrect) "Bueno (Correct!) - Recorded to your profile." else "Malo (Incorrect!) - Tagged for flashcard review.",
                                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                    color = if (isUserCorrect) SafetyGreen else SafetyRed
                                )
                            }
                        }
                    }
                }
            }
        }

        // Bottom Navigation Bar: Prev & Next MUST USE IDENTICAL OutlinedButton STYLING!
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

                // NEXT BUTTON: EXACT SAME COLORS & STYLING AS PREV (OutlinedButton)
                OutlinedButton(
                    onClick = { viewModel.setExamCurrentIndex(currentIndex + 1) },
                    enabled = currentIndex < examQuestions.size - 1,
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
                    Text("• Correct (Bueno): $correctCount")
                    Text("• Incorrect (Malo): $incorrectCount")
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
