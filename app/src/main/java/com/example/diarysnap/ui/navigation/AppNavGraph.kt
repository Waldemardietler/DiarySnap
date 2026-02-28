package com.example.diarysnap.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.diarysnap.ui.screens.create.CreateEntryScreen
import com.example.diarysnap.ui.screens.detail.DetailScreen
import com.example.diarysnap.ui.screens.home.HomeScreen
import com.example.diarysnap.ui.screens.login.LoginScreen
import com.example.diarysnap.ui.screens.profile.ProfileScreen

@Composable
fun AppNavGraph(
    darkMode: Boolean,
    onDarkModeChange: (Boolean) -> Unit
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN
    ) {

        composable(Routes.LOGIN) {
            LoginScreen(
                onLoggedIn = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.HOME) {
            HomeScreen(
                onCreate = { navController.navigate(Routes.CREATE) },
                onOpenDetail = { id -> navController.navigate("${Routes.DETAIL}/$id") },
                onOpenProfile = { navController.navigate(Routes.PROFILE) }
            )
        }

        composable(Routes.CREATE) {
            CreateEntryScreen(onBack = { navController.popBackStack() })
        }

        composable(
            route = "${Routes.DETAIL}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.LongType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getLong("id") ?: 0L
            DetailScreen(
                entryId = id,
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.PROFILE) {
            ProfileScreen(
                onBack = { navController.popBackStack() },
                onLogout = {
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.HOME) { inclusive = true }
                    }
                },
                darkMode = darkMode,
                onDarkModeChange = onDarkModeChange
            )
        }
    }
}