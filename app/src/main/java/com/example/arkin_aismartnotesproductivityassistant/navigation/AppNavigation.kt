package com.example.arkin_aismartnotesproductivityassistant.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.arkin_aismartnotesproductivityassistant.ui.screens.*
import com.example.arkin_aismartnotesproductivityassistant.viewmodel.AppViewModel

@Composable
fun AppNavigation(
    navController: NavHostController,
    viewModel: AppViewModel
) {
    NavHost(navController = navController, startDestination = "dashboard") {
        composable("dashboard") {
            DashboardScreen(navController = navController, viewModel = viewModel)
        }
        composable("notes") {
            NotesScreen(navController = navController, viewModel = viewModel)
        }
        composable("todo") {
            TodoScreen(navController = navController, viewModel = viewModel)
        }
        composable("homework") {
            HomeworkScreen(navController = navController, viewModel = viewModel)
        }
        composable("timetable") {
            TimetableScreen(navController = navController, viewModel = viewModel)
        }
        composable("locker") {
            LockerScreen(navController = navController, viewModel = viewModel)
        }
        composable("ai") {
            AiChatScreen(navController = navController, viewModel = viewModel)
        }
        composable("reminders") {
            RemindersScreen(navController = navController, viewModel = viewModel)
        }
        composable("analytics") {
            AnalyticsScreen(navController = navController, viewModel = viewModel)
        }
        composable("profile") {
            ProfileScreen(navController = navController, viewModel = viewModel)
        }
    }
}
