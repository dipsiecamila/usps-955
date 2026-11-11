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
import com.example.data.CraftRole
import com.example.ui.MainViewModel
import com.example.ui.components.SpatialNetCanvas
import com.example.ui.theme.*

@Composable
fun PracticeScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val selectedCategory by viewModel.practiceCategory.collectAsState()
    val isBookmarkOnly by viewModel.practiceFilterBookmarkOnly.collectAsState()
    val currentIndex by viewModel.practiceQuestionIndex.collectAsState()
    val selectedAnswer by viewModel.practiceSelectedAnswer.collectAsState()
    val showExplanation by viewModel.practiceShowExplanation.collectAsState()
    val bookmarkedIds by viewModel.bookmarkedIds.collectAsState()

    val questions = viewModel.getFilteredPracticeQuestions()
    val currentQuestion = questions.getOrNull(currentIndex)
    val isBookmarked = currentQuestion != null && bookmarkedIds.contains(currentQuestion.id)

    Column(
        modifier = modifier
            .fillMaxSize()
            .testTag("practice_screen_root")
    ) {
        // Filter pills row
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .background(PostalNavyDark)
                .padding(horizontal = 12.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                FilterChip(
                    selected = selectedCategory == null && !isBookmarkOnly,
                    onClick = { viewModel.startPractice(null, bookmarkOnly = false) },
                    label = { Text("All (${questions.size})") },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = PostalAmberAccent,
                        selectedLabelColor = PostalNavyDark
                    ),
                    modifier = Modifier.testTag("practice_filter_all")
                )
            }

            item {
                FilterChip(
                    selected = isBookmarkOnly,
                    onClick = { viewModel.startPractice(selectedCategory, bookmarkOnly = !isBookmarkOnly) },
                    label = { Text("Bookmarks (${bookmarkedIds.size})") },
                    leadingIcon = {
                        Icon(
                            imageVector = if (isBookmarkOnly) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = PostalAmberAccent,
                        selectedLabelColor = PostalNavyDark
                    ),
                    modifier = Modifier.testTag("practice_filter_bookmarks")
                )
            }

            items(Category.entries) { cat ->
                FilterChip(
                    selected = selectedCategory == cat && !isBookmarkOnly,
                    onClick = { viewModel.startPractice(cat, bookmarkOnly = false) },
                    label = { Text(cat.title.split(" ").first()) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = PostalAmberAccent,
                        selectedLabelColor = PostalNavyDark
                    ),
                    modifier = Modifier.testTag("practice_filter_${cat.name}")
                )
            }
        }

        if (currentQuestion == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.BookmarkBorder,
                        contentDescription = null,
                        modifier = Modifier.size(64.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "No questions found matching this filter.",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = { viewModel.startPractice(null, false) }) {
                        Text("Reset Filters")
                    }
                }
            }
        } else {
            // Main question scrollable content
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                // Header: Question counter & bookmark
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant
                        ) {
                            Text(
                                text = "Question ${currentIndex + 1} of ${questions.size}",
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        IconButton(
                            onClick = { viewModel.toggleBookmark(currentQuestion.id) },
                            modifier = Modifier.testTag("practice_bookmark_toggle")
                        ) {
                            Icon(
                                imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                                contentDescription = if (isBookmarked) "Bookmarked" else "Not Bookmarked",
                                tint = if (isBookmarked) PostalAmberAccent else MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Craft tags & Category badge
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
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

                        currentQuestion.crafts.take(3).forEach { craft ->
                            Surface(
                                shape = RoundedCornerShape(4.dp),
                                color = MaterialTheme.colorScheme.surfaceVariant
                            ) {
                                Text(
                                    text = craft.code,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Question Card Text
                    Text(
                        text = currentQuestion.questionText,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.SemiBold,
                            lineHeight = 24.sp
                        ),
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    // Render spatial net canvas if it's a spatial question
                    if (currentQuestion.category == Category.SPATIAL) {
                        SpatialNetCanvas(modifier = Modifier.padding(bottom = 14.dp))
                    }
                }

                // Options (A, B, C, D)
                items(currentQuestion.options.indices.toList()) { optionIndex ->
                    val optionText = currentQuestion.options[optionIndex]
                    val isSelected = selectedAnswer == optionIndex
                    val isCorrect = optionIndex == currentQuestion.correctIndex
                    val hasAnswered = selectedAnswer != null

                    val backgroundColor = when {
                        !hasAnswered -> MaterialTheme.colorScheme.surface
                        isCorrect -> SafetyGreenContainer.copy(alpha = 0.8f)
                        isSelected && !isCorrect -> SafetyRedContainer.copy(alpha = 0.8f)
                        else -> MaterialTheme.colorScheme.surface
                    }

                    val borderColor = when {
                        !hasAnswered -> MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
                        isCorrect -> SafetyGreen
                        isSelected && !isCorrect -> SafetyRed
                        else -> MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)
                    }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .border(1.5.dp, borderColor, RoundedCornerShape(12.dp))
                            .clip(RoundedCornerShape(12.dp))
                            .clickable(enabled = !hasAnswered) {
                                viewModel.selectPracticeAnswer(optionIndex)
                            }
                            .testTag("practice_option_$optionIndex"),
                        colors = CardDefaults.cardColors(containerColor = backgroundColor),
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
                                val letter = ('A' + optionIndex).toString()
                                if (hasAnswered && isCorrect) {
                                    Icon(
                                        imageVector = Icons.Default.Check,
                                        contentDescription = "Correct",
                                        tint = Color.White,
                                        modifier = Modifier.size(18.dp)
                                    )
                                } else if (hasAnswered && isSelected) {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = "Wrong",
                                        tint = Color.White,
                                        modifier = Modifier.size(18.dp)
                                    )
                                } else {
                                    Text(
                                        text = letter,
                                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.width(12.dp))

                            Text(
                                text = optionText,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = if (isSelected || (hasAnswered && isCorrect)) FontWeight.Bold else FontWeight.Normal
                                ),
                                color = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }

                // Explanation card
                if (showExplanation) {
                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = PostalBlueContainer.copy(alpha = 0.5f)
                            )
                        ) {
                            Column(modifier = Modifier.padding(14.dp)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Default.Lightbulb,
                                        contentDescription = null,
                                        tint = PostalAmberAccent,
                                        modifier = Modifier.size(20.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "Technical Rationale & Explanation",
                                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                                        color = PostalNavyPrimary
                                    )
                                }

                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    text = currentQuestion.explanation,
                                    style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 20.sp),
                                    color = MaterialTheme.colorScheme.onSurface
                                )

                                if (currentQuestion.formulaOrRule != null) {
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Surface(
                                        shape = RoundedCornerShape(6.dp),
                                        color = PostalNavyPrimary
                                    ) {
                                        Text(
                                            text = "Rule: ${currentQuestion.formulaOrRule}",
                                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                            color = PostalAmberAccent
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // Bottom Navigation Controls
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
                        onClick = { viewModel.prevPracticeQuestion() },
                        enabled = currentIndex > 0,
                        modifier = Modifier.testTag("practice_prev_btn"),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Prev")
                    }

                    Text(
                        text = "${currentIndex + 1} / ${questions.size}",
                        style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Button(
                        onClick = { viewModel.nextPracticeQuestion() },
                        enabled = currentIndex < questions.size - 1,
                        modifier = Modifier.testTag("practice_next_btn"),
                        colors = ButtonDefaults.buttonColors(containerColor = PostalNavyPrimary),
                        shape = RoundedCornerShape(10.dp)
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
