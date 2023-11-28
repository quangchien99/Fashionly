package chn.phm.presentation.base.navigation

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import chn.phm.presentation.screens.home.HomeScreen
import chn.phm.presentation.screens.onboarding.OnBoardingScreen
import chn.phm.presentation.screens.splash.SplashScreen
import kotlin.system.exitProcess

@Composable
fun FashionlyNavHost(navController: NavHostController) {
    val context = LocalContext.current

    NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {
        composable(Screen.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(Screen.OnBoardingScreen.route) {
            OnBoardingScreen(navController)
        }
        composable(Screen.HomeScreen.route) {
            HomeScreen(navController)

            BackHandler {
                (context as? Activity)?.moveTaskToBack(true)
            }
        }
    }
}
