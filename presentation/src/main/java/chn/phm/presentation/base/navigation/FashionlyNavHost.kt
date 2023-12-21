package chn.phm.presentation.base.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import chn.phm.presentation.screens.fashionly.FashionlyScreen
import chn.phm.presentation.screens.history.HistoryScreen
import chn.phm.presentation.screens.onboarding.OnBoardingScreen
import chn.phm.presentation.screens.setting.SettingScreen
import chn.phm.presentation.screens.splash.SplashScreen

@Composable
fun FashionlyNavHost(navController: NavHostController, snackBarHostState: SnackbarHostState) {

    val viewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {
        "No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"
    }

    NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {
        composable(Screen.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(Screen.OnBoardingScreen.route) {
            OnBoardingScreen(navController)
        }
        composable(Screen.FashionlyScreen.route) {
            FashionlyScreen(
                snackBarHostState = snackBarHostState,
                viewModel = hiltViewModel(viewModelStoreOwner)
            )
        }
        composable(Screen.HistoryScreen.route) {
            HistoryScreen(navHostController = navController)
        }
        composable(Screen.SettingsScreen.route) {
            SettingScreen(navHostController = navController)
        }
    }
}
