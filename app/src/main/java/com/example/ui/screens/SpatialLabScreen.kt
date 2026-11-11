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
import com.example.ui.MainViewModel
import com.example.ui.components.SpatialNetCanvas
import com.example.ui.theme.*

@Composable
fun SpatialLabScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val currentIndex by viewModel.spatialIndex.collectAsState()
    val selectedAnswer by viewModel.spatialSelected.collectAsState()
    val showResult by viewModel.spatialShowResult.collectAsState()
    val spatialQuestions = viewModel.spatialQuestions
    val currentQuestion = spatialQuestions.getOrNull(currentIndex)

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .testTag("spatial_lab_screen"),
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        // Spatial Strategy Guide Header Card
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = PostalNavyPrimary)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.ViewInAr,
                            contentDescription = null,
                            tint = PostalAmberAccent,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "USPS 955 Spatial Relations Trainer",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "The spatial section tests your ability to fold flat 2D patterns into 3D shapes. Master the 3 golden postal rules below:",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White.copy(alpha = 0.9f)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = PostalNavyDark
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Text(
                                text = "1. Opposite Faces Never Touch:",
                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                color = PostalAmberAccent
                            )
                            Text(
                                text = "Faces separated by one square in the net will be opposite in 3D and can NEVER be visible simultaneously.",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.White.copy(alpha = 0.8f)
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "2. Maximum 3 Faces in Perspective:",
                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                color = PostalAmberAccent
                            )
                            Text(
                                text = "Any isometric view shows at most 3 mutually adjacent faces meeting at a single corner vertex.",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.White.copy(alpha = 0.8f)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        // Interactive 2D Canvas Net
        item {
            SpatialNetCanvas()
            Spacer(modifier = Modifier.height(16.dp))
        }

        // Active Question
        if (currentQuestion != null) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Drill ${currentIndex + 1} of ${spatialQuestions.size}",
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Surface(
                        shape = RoundedCornerShape(4.dp),
                        color = PostalAmberContainer
                    ) {
                        Text(
                            text = "Spatial Perception",
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                            color = PostalOnAmberContainer
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = currentQuestion.questionText,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                    color = MaterialTheme.colorScheme.onBackground
                )

                Spacer(modifier = Modifier.height(12.dp))
            }

            // Options
            items(currentQuestion.options.indices.toList()) { optIdx ->
                val optText = currentQuestion.options[optIdx]
                val isSelected = selectedAnswer == optIdx
                val isCorrect = optIdx == currentQuestion.correctIndex
                val hasAnswered = selectedAnswer != null

                val bg = when {
                    !hasAnswered -> MaterialTheme.colorScheme.surface
                    isCorrect -> SafetyGreenContainer.copy(alpha = 0.8f)
                    isSelected && !isCorrect -> SafetyRedContainer.copy(alpha = 0.8f)
                    else -> MaterialTheme.colorScheme.surface
                }

                val border = when {
                    !hasAnswered -> MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
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
                        .clickable(enabled = !hasAnswered) {
                            viewModel.selectSpatialAnswer(optIdx)
                        }
                        .testTag("spatial_option_$optIdx"),
                    colors = CardDefaults.cardColors(containerColor = bg),
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
                                        hasAnswered && isCorrect -> SafetyGreen
                                        hasAnswered && isSelected -> SafetyRed
                                        else -> MaterialTheme.colorScheme.surfaceVariant
                                    }
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = ('A' + optIdx).toString(),
                                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                                color = if (hasAnswered && (isCorrect || isSelected)) Color.White else MaterialTheme.colorScheme.onSurfaceVariant
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

            // Technical Spatial Explanation
            if (showResult) {
                item {
                    Spacer(modifier = Modifier.height(14.dp))
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = PostalBlueContainer.copy(alpha = 0.5f))
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = null,
                                    tint = PostalNavyPrimary,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "Spatial Analysis",
                                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                                    color = PostalNavyPrimary
                                )
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = currentQuestion.explanation,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }

            // Next / Prev Drill
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    OutlinedButton(
                        onClick = { viewModel.prevSpatialQuestion() },
                        enabled = currentIndex > 0,
                        modifier = Modifier.testTag("spatial_prev_btn")
                    ) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Prev Drill")
                    }

                    Button(
                        onClick = { viewModel.nextSpatialQuestion() },
                        enabled = currentIndex < spatialQuestions.size - 1,
                        colors = ButtonDefaults.buttonColors(containerColor = PostalNavyPrimary),
                        modifier = Modifier.testTag("spatial_next_btn")
                    ) {
                        Text("Next Drill")
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, modifier = Modifier.size(16.dp))
                    }
                }
            }
        }
    }
}
