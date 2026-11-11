package com.example.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.common.learning.MasteryLevel
import com.example.data.Category
import com.example.data.Question
import com.example.data.QuestionBank
import com.example.ui.MainViewModel
import com.example.ui.components.SpatialNetCanvas
import com.example.ui.theme.*

private enum class FlashcardStudyMode(val label: String) {
    SPACED_REPETITION("Smart Spaced Repetition"),
    NEEDS_REVIEW_ONLY("Needs Review Queue"),
    MASTERED_ONLY("Mastered Cards"),
    ALL_CARDS("All Cards")
}

@Composable
fun FlashcardsScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val learningStates by viewModel.learningEngine.learningStates.collectAsState()
    var selectedCategory by remember { mutableStateOf<Category?>(null) }
    var selectedMode by remember { mutableStateOf(FlashcardStudyMode.SPACED_REPETITION) }

    var cardIndex by remember { mutableIntStateOf(0) }
    var isFlipped by remember { mutableStateOf(false) }

    // Derive active queue
    val activeQueue = remember(selectedCategory, selectedMode, learningStates) {
        val baseList = if (selectedCategory != null) {
            QuestionBank.allQuestions.filter { it.category == selectedCategory }
        } else {
            QuestionBank.allQuestions
        }

        when (selectedMode) {
            FlashcardStudyMode.SPACED_REPETITION -> {
                viewModel.learningEngine.getSpacedRepetitionQueue(baseList)
            }
            FlashcardStudyMode.NEEDS_REVIEW_ONLY -> {
                viewModel.learningEngine.getQuestionsByMastery(baseList, MasteryLevel.NEEDS_REVIEW)
                    .ifEmpty { baseList }
            }
            FlashcardStudyMode.MASTERED_ONLY -> {
                viewModel.learningEngine.getQuestionsByMastery(baseList, MasteryLevel.MASTERED)
                    .ifEmpty { baseList }
            }
            FlashcardStudyMode.ALL_CARDS -> {
                baseList
            }
        }
    }

    val safeIndex = cardIndex.coerceIn(0, (activeQueue.size - 1).coerceAtLeast(0))
    val currentQuestion: Question? = activeQueue.getOrNull(safeIndex)
    val currentState = currentQuestion?.let { learningStates[it.id] }

    val rotation by animateFloatAsState(
        targetValue = if (isFlipped) 180f else 0f,
        animationSpec = tween(durationMillis = 400),
        label = "cardFlipAnimation"
    )

    // Compute overall statistics
    val totalQuestions = QuestionBank.allQuestions.size
    val masteredCount = learningStates.values.count { it.mastery == MasteryLevel.MASTERED }
    val learningCount = learningStates.values.count { it.mastery == MasteryLevel.LEARNING }
    val needsReviewCount = totalQuestions - masteredCount - learningCount

    Column(
        modifier = modifier
            .fillMaxSize()
            .testTag("flashcards_screen_root")
    ) {
        // Study Mode & Topic Filters
        Surface(
            color = PostalNavyDark,
            tonalElevation = 2.dp
        ) {
            Column(modifier = Modifier.padding(vertical = 8.dp)) {
                // Topic Row
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    item {
                        FilterChip(
                            selected = selectedCategory == null,
                            onClick = {
                                selectedCategory = null
                                cardIndex = 0
                                isFlipped = false
                            },
                            label = { Text("All Domains") },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = PostalAmberAccent,
                                selectedLabelColor = PostalNavyDark
                            ),
                            modifier = Modifier.testTag("fc_filter_all_topics")
                        )
                    }

                    items(Category.entries) { cat ->
                        FilterChip(
                            selected = selectedCategory == cat,
                            onClick = {
                                selectedCategory = cat
                                cardIndex = 0
                                isFlipped = false
                            },
                            label = { Text(cat.title.split(" ").first()) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = PostalAmberAccent,
                                selectedLabelColor = PostalNavyDark
                            ),
                            modifier = Modifier.testTag("fc_filter_${cat.name}")
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                // Mode Row
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    items(FlashcardStudyMode.entries) { mode ->
                        FilterChip(
                            selected = selectedMode == mode,
                            onClick = {
                                selectedMode = mode
                                cardIndex = 0
                                isFlipped = false
                            },
                            label = { Text(mode.label) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = PostalBlueLight,
                                selectedLabelColor = PostalNavyDark
                            ),
                            modifier = Modifier.testTag("fc_mode_${mode.name}")
                        )
                    }
                }
            }
        }

        // Mastery Analytics Bar
        Surface(
            color = MaterialTheme.colorScheme.surfaceVariant,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(
                        text = "Needs Review: $needsReviewCount",
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                        color = SafetyRed
                    )
                    Text(
                        text = "Learning: $learningCount",
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                        color = PostalAmberAccent
                    )
                    Text(
                        text = "Mastered: $masteredCount",
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                        color = SafetyGreen
                    )
                }

                Text(
                    text = "${safeIndex + 1}/${activeQueue.size}",
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        // Active Flashcard Container
        if (currentQuestion == null) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No flashcards found in this queue.",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        } else {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer {
                            rotationY = rotation
                            cameraDistance = 12f * density
                        }
                        .clickable { isFlipped = !isFlipped }
                        .testTag("flashcard_flip_card"),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isFlipped) PostalBlueContainer.copy(alpha = 0.35f) else MaterialTheme.colorScheme.surface
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp)
                    ) {
                        if (rotation <= 90f) {
                            // FRONT OF CARD: QUESTION & HINT
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Surface(
                                            shape = RoundedCornerShape(6.dp),
                                            color = PostalBlueContainer
                                        ) {
                                            Text(
                                                text = currentQuestion.category.title,
                                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                                color = PostalNavyPrimary
                                            )
                                        }

                                        val currentLevel = currentState?.mastery ?: MasteryLevel.NEEDS_REVIEW
                                        Surface(
                                            shape = RoundedCornerShape(6.dp),
                                            color = when (currentLevel) {
                                                MasteryLevel.MASTERED -> SafetyGreenContainer
                                                MasteryLevel.LEARNING -> PostalAmberContainer
                                                MasteryLevel.NEEDS_REVIEW -> SafetyRedContainer
                                            }
                                        ) {
                                            Text(
                                                text = currentLevel.label,
                                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                                color = when (currentLevel) {
                                                    MasteryLevel.MASTERED -> SafetyGreen
                                                    MasteryLevel.LEARNING -> PostalAmberAccent
                                                    MasteryLevel.NEEDS_REVIEW -> SafetyRed
                                                }
                                            )
                                        }
                                    }

                                    Spacer(modifier = Modifier.height(18.dp))

                                    Text(
                                        text = currentQuestion.questionText,
                                        style = MaterialTheme.typography.titleMedium.copy(
                                            fontWeight = FontWeight.SemiBold,
                                            lineHeight = 26.sp
                                        ),
                                        color = MaterialTheme.colorScheme.onSurface
                                    )

                                    if (currentQuestion.category == Category.SPATIAL) {
                                        Spacer(modifier = Modifier.height(10.dp))
                                        SpatialNetCanvas(modifier = Modifier.height(130.dp))
                                    }
                                }

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.FlipToBack,
                                        contentDescription = null,
                                        tint = PostalAmberAccent,
                                        modifier = Modifier.size(18.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "Tap Card to Flip & View Answer",
                                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                                        color = PostalAmberAccent
                                    )
                                }
                            }
                        } else {
                            // BACK OF CARD: ANSWER & RATIONALE (Counter-rotated to read normally)
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .graphicsLayer { rotationY = 180f },
                                verticalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column {
                                    Surface(
                                        shape = RoundedCornerShape(6.dp),
                                        color = SafetyGreenContainer
                                    ) {
                                        Row(
                                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.CheckCircle,
                                                contentDescription = null,
                                                tint = SafetyGreen,
                                                modifier = Modifier.size(16.dp)
                                            )
                                            Spacer(modifier = Modifier.width(6.dp))
                                            Text(
                                                text = "CORRECT ANSWER",
                                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.ExtraBold),
                                                color = SafetyGreen
                                            )
                                        }
                                    }

                                    Spacer(modifier = Modifier.height(10.dp))

                                    Text(
                                        text = currentQuestion.options[currentQuestion.correctIndex],
                                        style = MaterialTheme.typography.titleMedium.copy(
                                            fontWeight = FontWeight.Bold,
                                            lineHeight = 24.sp
                                        ),
                                        color = PostalNavyPrimary
                                    )

                                    Spacer(modifier = Modifier.height(12.dp))

                                    Text(
                                        text = currentQuestion.explanation,
                                        style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 20.sp),
                                        color = MaterialTheme.colorScheme.onSurface
                                    )

                                    if (currentQuestion.formulaOrRule != null) {
                                        Spacer(modifier = Modifier.height(10.dp))
                                        Surface(
                                            shape = RoundedCornerShape(6.dp),
                                            color = PostalNavyPrimary
                                        ) {
                                            Text(
                                                text = "Key Rule: ${currentQuestion.formulaOrRule}",
                                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                                color = PostalAmberAccent
                                            )
                                        }
                                    }
                                }

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.FlipToFront,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "Tap Card to Flip Back to Question",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Spaced Repetition Rating Buttons (Acceptance & Repetition Control)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Rate Your Knowledge (Spaced Repetition):",
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(bottom = 6.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedButton(
                        onClick = {
                            viewModel.recordFlashcardReview(currentQuestion.id, MasteryLevel.NEEDS_REVIEW)
                            if (cardIndex < activeQueue.size - 1) {
                                cardIndex += 1
                                isFlipped = false
                            }
                        },
                        modifier = Modifier
                            .weight(1f)
                            .testTag("fc_rate_needs_review"),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = SafetyRed
                        )
                    ) {
                        Text("Needs Review", style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold))
                    }

                    OutlinedButton(
                        onClick = {
                            viewModel.recordFlashcardReview(currentQuestion.id, MasteryLevel.LEARNING)
                            if (cardIndex < activeQueue.size - 1) {
                                cardIndex += 1
                                isFlipped = false
                            }
                        },
                        modifier = Modifier
                            .weight(1f)
                            .testTag("fc_rate_learning"),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = PostalAmberAccent
                        )
                    ) {
                        Text("Learning", style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold))
                    }

                    OutlinedButton(
                        onClick = {
                            viewModel.recordFlashcardReview(currentQuestion.id, MasteryLevel.MASTERED)
                            if (cardIndex < activeQueue.size - 1) {
                                cardIndex += 1
                                isFlipped = false
                            }
                        },
                        modifier = Modifier
                            .weight(1f)
                            .testTag("fc_rate_mastered"),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = SafetyGreen
                        )
                    ) {
                        Text("Mastered", style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold))
                    }
                }
            }

            // Bottom Navigation Row: Prev and Next MUST use identical OutlinedButton styling!
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.surface,
                tonalElevation = 6.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedButton(
                        onClick = {
                            if (cardIndex > 0) {
                                cardIndex -= 1
                                isFlipped = false
                            }
                        },
                        enabled = cardIndex > 0,
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.testTag("fc_prev_btn")
                    ) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Prev")
                    }

                    Text(
                        text = "Card ${safeIndex + 1} of ${activeQueue.size}",
                        style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    // NEXT BUTTON: EXACT SAME STYLING (OutlinedButton, same border, text, and background)
                    OutlinedButton(
                        onClick = {
                            if (cardIndex < activeQueue.size - 1) {
                                cardIndex += 1
                                isFlipped = false
                            }
                        },
                        enabled = cardIndex < activeQueue.size - 1,
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.testTag("fc_next_btn")
                    ) {
                        Text("Next")
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, modifier = Modifier.size(16.dp))
                    }
                }
            }
        }
    }
}
