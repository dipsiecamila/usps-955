package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.CraftRole
import com.example.ui.Screen
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun USPSTopBar(
    title: String,
    subtitle: String? = null,
    canNavigateBack: Boolean = false,
    onBackClick: () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {}
) {
    TopAppBar(
        title = {
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onPrimary
                )
                if (subtitle != null) {
                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f)
                    )
                }
            }
        },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier.testTag("top_bar_back_button")
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            } else {
                Box(
                    modifier = Modifier
                        .padding(start = 12.dp, end = 8.dp)
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(PostalAmberAccent),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.PrecisionManufacturing,
                        contentDescription = "USPS 955",
                        tint = PostalNavyDark,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        },
        actions = actions,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = PostalNavyPrimary,
            titleContentColor = Color.White
        )
    )
}

@Composable
fun USPSBottomNavBar(
    currentScreen: Screen,
    onNavigate: (Screen) -> Unit
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 6.dp,
        windowInsets = WindowInsets.navigationBars
    ) {
        val isHome = currentScreen is Screen.Home
        val isPractice = currentScreen is Screen.Practice
        val isExam = currentScreen is Screen.ExamActive || currentScreen is Screen.ExamResultScreen
        val isSpatial = currentScreen is Screen.SpatialLab
        val isTroubleshoot = currentScreen is Screen.Troubleshooting
        val isRef = currentScreen is Screen.Reference

        NavigationBarItem(
            selected = isHome,
            onClick = { onNavigate(Screen.Home) },
            icon = { Icon(Icons.Default.Dashboard, contentDescription = "Home") },
            label = { Text("Home") },
            modifier = Modifier.testTag("nav_home")
        )
        NavigationBarItem(
            selected = isPractice,
            onClick = { onNavigate(Screen.Practice()) },
            icon = { Icon(Icons.Default.MenuBook, contentDescription = "Practice") },
            label = { Text("Practice") },
            modifier = Modifier.testTag("nav_practice")
        )
        NavigationBarItem(
            selected = isExam,
            onClick = { onNavigate(Screen.ExamActive) },
            icon = { Icon(Icons.Default.Timer, contentDescription = "Mock Exam") },
            label = { Text("Exam") },
            modifier = Modifier.testTag("nav_exam")
        )
        NavigationBarItem(
            selected = isSpatial,
            onClick = { onNavigate(Screen.SpatialLab) },
            icon = { Icon(Icons.Default.ViewInAr, contentDescription = "Spatial") },
            label = { Text("Spatial") },
            modifier = Modifier.testTag("nav_spatial")
        )
        NavigationBarItem(
            selected = isTroubleshoot,
            onClick = { onNavigate(Screen.Troubleshooting) },
            icon = { Icon(Icons.Default.Build, contentDescription = "Diagnostics") },
            label = { Text("Troubleshoot") },
            modifier = Modifier.testTag("nav_troubleshoot")
        )
        NavigationBarItem(
            selected = isRef,
            onClick = { onNavigate(Screen.Reference) },
            icon = { Icon(Icons.Default.Calculate, contentDescription = "Handbook") },
            label = { Text("Handbook") },
            modifier = Modifier.testTag("nav_reference")
        )
    }
}

@Composable
fun CraftBadge(
    craft: CraftRole,
    isQualified: Boolean? = null,
    modifier: Modifier = Modifier
) {
    val containerColor = when (isQualified) {
        true -> SafetyGreenContainer
        false -> SafetyRedContainer
        null -> MaterialTheme.colorScheme.surfaceVariant
    }
    val contentColor = when (isQualified) {
        true -> SafetyGreen
        false -> SafetyRed
        null -> MaterialTheme.colorScheme.onSurfaceVariant
    }

    Surface(
        shape = RoundedCornerShape(8.dp),
        color = containerColor,
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = craft.code,
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                color = contentColor
            )
            if (isQualified != null) {
                Icon(
                    imageVector = if (isQualified) Icons.Default.CheckCircle else Icons.Default.Cancel,
                    contentDescription = if (isQualified) "Passed" else "Not Passed",
                    tint = contentColor,
                    modifier = Modifier.size(14.dp)
                )
            }
        }
    }
}

@Composable
fun SpatialNetCanvas(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Unfolded 2D Cube Net Pattern",
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(8.dp))

            Canvas(
                modifier = Modifier
                    .size(width = 240.dp, height = 180.dp)
                    .background(Color.White, RoundedCornerShape(8.dp))
                    .padding(8.dp)
            ) {
                val boxSize = size.width / 4.5f
                val strokeW = 3f

                // Draw standard Cross pattern
                // Row 0: Top Face (col 1)
                // Row 1: Left (col 0), Front (col 1), Right (col 2), Back (col 3)
                // Row 2: Bottom Face (col 1)

                val col0 = 0f
                val col1 = boxSize
                val col2 = boxSize * 2
                val col3 = boxSize * 3

                val row0 = 0f
                val row1 = boxSize
                val row2 = boxSize * 2

                fun drawFace(x: Float, y: Float, label: String, color: Color = Color(0xFFE2E8F0)) {
                    drawRect(
                        color = color,
                        topLeft = Offset(x, y),
                        size = Size(boxSize, boxSize)
                    )
                    drawRect(
                        color = Color(0xFF0A2540),
                        topLeft = Offset(x, y),
                        size = Size(boxSize, boxSize),
                        style = Stroke(strokeW)
                    )
                }

                // Row 0: Top face
                drawFace(col1, row0, "TOP", Color(0xFFD0E4FF))

                // Row 1: 4 horizontal faces
                drawFace(col0, row1, "LEFT", Color(0xFFFFDDB3))
                drawFace(col1, row1, "FRONT", Color(0xFFA7F3D0))
                drawFace(col2, row1, "RIGHT", Color(0xFFFFDDB3))
                drawFace(col3, row1, "BACK", Color(0xFFA7F3D0))

                // Row 2: Bottom face
                drawFace(col1, row2, "BOTTOM", Color(0xFFD0E4FF))

                // Draw decorative symbols inside faces to illustrate 955 spatial tests
                // Circle inside Front face
                drawCircle(
                    color = Color(0xFF0A2540),
                    radius = boxSize * 0.25f,
                    center = Offset(col1 + boxSize / 2, row1 + boxSize / 2),
                    style = Stroke(4f)
                )

                // Diagonal line inside Right face
                drawLine(
                    color = Color(0xFFD97706),
                    start = Offset(col2 + 8f, row1 + boxSize - 8f),
                    end = Offset(col2 + boxSize - 8f, row1 + 8f),
                    strokeWidth = 4f
                )

                // Solid dot inside Back face
                drawCircle(
                    color = Color(0xFF10B981),
                    radius = boxSize * 0.2f,
                    center = Offset(col3 + boxSize / 2, row1 + boxSize / 2)
                )

                // Plus sign inside Top face
                drawLine(
                    color = Color(0xFF1E88E5),
                    start = Offset(col1 + boxSize / 2, row0 + 8f),
                    end = Offset(col1 + boxSize / 2, row0 + boxSize - 8f),
                    strokeWidth = 4f
                )
                drawLine(
                    color = Color(0xFF1E88E5),
                    start = Offset(col1 + 8f, row0 + boxSize / 2),
                    end = Offset(col1 + boxSize - 8f, row0 + boxSize / 2),
                    strokeWidth = 4f
                )
            }

            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Rule: Green Front and Green Back are opposite faces (never meet).",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
