package chn.phm.presentation.base.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import chn.phm.presentation.screens.home.HomeScreen
import chn.phm.presentation.screens.onboarding.OnBoardingScreen
import chn.phm.presentation.screens.splash.SplashScreen

@Composable
fun FashionlyNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {
        composable(Screen.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(Screen.OnBoardingScreen.route) {
            OnBoardingScreen(navController)
        }
        composable(Screen.HomeScreen.route) {
            HomeScreen(navController)
        }
    }
}
