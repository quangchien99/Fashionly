package chn.phm.presentation.main

import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import chn.phm.presentation.base.navigation.BottomNavigationBar
import chn.phm.presentation.base.navigation.FashionlyNavHost
import chn.phm.presentation.base.navigation.Screen

@Composable
fun MainScreen(
    navController: NavHostController
) {
    val snackBarHostState = SnackbarHostState()
    Scaffold(
        bottomBar = {
            val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
            if (currentRoute != Screen.SplashScreen.route && currentRoute != Screen.OnBoardingScreen.route) {
                BottomNavigationBar(navController)
            }
        }
    ) {
        FashionlyNavHost(
            navController = navController,
            snackBarHostState = snackBarHostState
        )
    }
}
