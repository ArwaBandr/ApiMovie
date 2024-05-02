package com.example.apimovie.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apimovie.MainScreen
import com.example.apimovie.presentation.screens.OnBoardingScreen.OnBoardingScreen
import com.example.apimovie.presentation.screens.OnBoardingScreen.OnBoardingViewModel
import com.example.apimovie.presentation.screens.OnBoardingScreen.ThirdScren
import com.example.apimovie.presentation.screens.popular.PopularViewModel


sealed class Scrrens(val rout: String) {
    object endOfONboardingScreen : Scrrens("EndOfOnBoarding")
    object mainScreen : Scrrens("mainScreen")
    object OnboardingScreen:Scrrens("OnBoarding")
}


@Composable
fun NavGraph(navController: NavHostController = rememberNavController()) {

    val OnBoardingViewModel:OnBoardingViewModel= hiltViewModel()

    NavHost(navController = navController, startDestination = OnBoardingViewModel.startDistination ) {
        composable(Scrrens.OnboardingScreen.rout){
            OnBoardingScreen(OnBoardingViewModel,navController)
        }
        composable(Scrrens.endOfONboardingScreen.rout) {
            ThirdScren(navController) {
                OnBoardingViewModel.saveOnBoardingState(true)
            }
        }
        composable(Scrrens.mainScreen.rout) {
           val viewModel= hiltViewModel<PopularViewModel>()
            MainScreen(navController,viewModel.popularMovieState)
        }

    }

}