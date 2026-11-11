package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.MainViewModel
import com.example.ui.Screen
import com.example.ui.components.USPSBottomNavBar
import com.example.ui.components.USPSTopBar
import com.example.ui.screens.*
import com.example.ui.theme.USPS955Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            USPS955Theme {
                val viewModel: MainViewModel = viewModel()
                val currentScreen by viewModel.currentScreen.collectAsState()

                BackHandler {
                    val handled = viewModel.handleBack()
                    if (!handled) {
                        finish()
                    }
                }

                val title = when (currentScreen) {
                    is Screen.Home -> "USPS 955"
                    is Screen.Practice -> "Domain Practice"
                    is Screen.ExamActive -> "USPS 955 Mock Exam"
                    is Screen.ExamResultScreen -> "Exam Results"
                    is Screen.Flashcards -> "Study Flashcards"
                    is Screen.SpatialLab -> "Spatial Relations"
                    is Screen.Troubleshooting -> "Machine Diagnostics"
                    is Screen.Reference -> "Maintenance Handbook"
                }

                val subtitle = when (currentScreen) {
                    is Screen.Home -> "Postal Maintenance & Engineering"
                    is Screen.Practice -> (currentScreen as Screen.Practice).category?.title ?: "All Domains"
                    is Screen.ExamActive -> "Official 30-Question Simulation"
                    is Screen.ExamResultScreen -> "Postal Craft Scorecard"
                    is Screen.Flashcards -> "Spaced Repetition & Acceptance Queue"
                    is Screen.SpatialLab -> "2D-to-3D Net Folding Trainer"
                    is Screen.Troubleshooting -> "Interactive Fault Isolation"
                    is Screen.Reference -> "Formulas, Logic & Shop Tables"
                }

                val canNavigateBack = currentScreen !is Screen.Home

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        USPSTopBar(
                            title = title,
                            subtitle = subtitle,
                            canNavigateBack = canNavigateBack,
                            onBackClick = { viewModel.handleBack() }
                        )
                    },
                    bottomBar = {
                        // Hide bottom bar during active timed exam to avoid accidental tap navigation
                        if (currentScreen !is Screen.ExamActive) {
                            USPSBottomNavBar(
                                currentScreen = currentScreen,
                                onNavigate = { destination ->
                                    if (destination is Screen.ExamActive) {
                                        viewModel.startExam()
                                    } else {
                                        viewModel.navigateTo(destination)
                                    }
                                }
                            )
                        }
                    }
                ) { innerPadding ->
                    val screenModifier = Modifier.padding(innerPadding)
                    when (val screen = currentScreen) {
                        is Screen.Home -> HomeScreen(viewModel = viewModel, modifier = screenModifier)
                        is Screen.Practice -> PracticeScreen(viewModel = viewModel, modifier = screenModifier)
                        is Screen.ExamActive -> ExamScreen(viewModel = viewModel, modifier = screenModifier)
                        is Screen.ExamResultScreen -> ExamResultScreen(result = screen.result, viewModel = viewModel, modifier = screenModifier)
                        is Screen.Flashcards -> FlashcardsScreen(viewModel = viewModel, modifier = screenModifier)
                        is Screen.SpatialLab -> SpatialLabScreen(viewModel = viewModel, modifier = screenModifier)
                        is Screen.Troubleshooting -> TroubleshootingScreen(viewModel = viewModel, modifier = screenModifier)
                        is Screen.Reference -> ReferenceScreen(viewModel = viewModel, modifier = screenModifier)
                    }
                }
            }
        }
    }
}
