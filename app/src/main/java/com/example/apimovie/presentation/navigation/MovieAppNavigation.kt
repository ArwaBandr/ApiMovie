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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.apimovie.presentation.screens.OnBoardingScreen.OnBoardingScreen
import com.example.apimovie.presentation.screens.OnBoardingScreen.OnBoardingViewModel
import com.example.apimovie.presentation.screens.details.DetailsScreen
import com.example.apimovie.presentation.screens.details.DetailsViewModel
import com.example.apimovie.presentation.screens.popular.MainScreen
import com.example.apimovie.presentation.screens.popular.PopularViewModel
import com.example.apimovie.presentation.screens.search.SearchScreen
import com.example.apimovie.presentation.screens.search.SearchViewModel


sealed class Scrrens(val rout: String) {
    object mainScreen : Scrrens("mainScreen")
    object OnboardingScreen : Scrrens("OnBoarding")

    object Search : Scrrens("search_rout")

    object Profile : Scrrens("profile_route")

    object Details : Scrrens("movie_details")
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
            val viewModel= hiltViewModel<SearchViewModel>()
          SearchScreen(viewModel,navController)
        }
        composable(Scrrens.Profile.rout) {
            Column(
                modifier = androidx.compose.ui.Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.onSecondary)
            ) {

            }
        }

        composable("${Scrrens.Details.rout}/{moviId}",
            arguments = listOf(navArgument("moviId") {
            type= NavType.IntType
        })
        ) {
            val viewModel = hiltViewModel<DetailsViewModel>()
            DetailsScreen(
                it.arguments?.getInt("moviId"),
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}

fun NavOptionsBuilder.popUpToTop(navController: NavController) {
    popUpTo(navController.currentBackStackEntry?.destination?.route ?: return) {
        inclusive = true
        saveState=true
    }
}