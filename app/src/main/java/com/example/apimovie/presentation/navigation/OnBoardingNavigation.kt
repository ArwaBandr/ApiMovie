package com.example.apimovie.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apimovie.presentation.screens.OnBoardingScreen.OnBoardingScreen
import com.example.apimovie.presentation.screens.OnBoardingScreen.OnBoardingViewModel
import com.example.apimovie.presentation.screens.popular.MainScreen
import com.example.apimovie.presentation.screens.popular.PopularViewModel
import java.lang.reflect.Modifier


sealed class Scrrens(val rout: String) {
    object mainScreen : Scrrens("mainScreen")
    object OnboardingScreen : Scrrens("OnBoarding")

    object Search : Scrrens("search_rout")

    object Profile : Scrrens("profile_route")
}

data class BottomNavigationItem(
    val label: String = "",
    val icon: ImageVector = Icons.Filled.Home,
    val route: String = ""
) {

    fun bottomNavigationItem(): List<BottomNavigationItem> {

        return listOf(
            BottomNavigationItem(
                label = "Home",
                icon = Icons.Filled.Home,
                route = Scrrens.mainScreen.rout
            ),
            BottomNavigationItem(
                label = "Search",
                icon = Icons.Filled.Search,
                route = Scrrens.Search.rout
            ),
            BottomNavigationItem(
                label = "Profile",
                icon = Icons.Filled.AccountCircle,
                route = Scrrens.Profile.rout
            ),
        )
    }

}

@Composable
fun NavGraph(navController: NavHostController = rememberNavController()) {

    val OnBoardingViewModel: OnBoardingViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = OnBoardingViewModel.startDistination
    ) {
        composable(Scrrens.OnboardingScreen.rout) {
            OnBoardingScreen(OnBoardingViewModel, navController)
        }
        composable(Scrrens.mainScreen.rout) {
            val viewModel = hiltViewModel<PopularViewModel>()
            MainScreen(navController, viewModel.popularMovieState)
        }
        composable(Scrrens.Search.rout) {
            Column (modifier = androidx.compose.ui.Modifier.fillMaxSize().background(MaterialTheme.colorScheme.onSecondary)){

            }
        }
        composable(Scrrens.Profile.rout) {
            Column (modifier = androidx.compose.ui.Modifier.fillMaxSize().background(MaterialTheme.colorScheme.onSecondary)){

            }
        }

    }
}

fun NavOptionsBuilder.popUpToTop(navController: NavController) {
    popUpTo(navController.currentBackStackEntry?.destination?.route ?: return) {
        inclusive = true
    }
}