package com.example.apimovie.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apimovie.MainScreen
import com.example.apimovie.presentation.screens.OnBoardingScreen.OnBoardingScreen
import com.example.apimovie.presentation.screens.OnBoardingScreen.ThirdScren


sealed class Scrrens(val rout: String) {
    object endOfONboardingScreen : Scrrens("EndOfOnBoarding")
    object mainScreen : Scrrens("mainScreen")
    object OnboardingScreen:Scrrens("OnBoarding")
}

@Composable
fun NavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = Scrrens.OnboardingScreen.rout) {
        composable(Scrrens.OnboardingScreen.rout){
            OnBoardingScreen(navController)
        }
        composable(Scrrens.endOfONboardingScreen.rout) {
            ThirdScren(navController)
        }
        composable(Scrrens.mainScreen.rout) {
            MainScreen()
        }

    }

}