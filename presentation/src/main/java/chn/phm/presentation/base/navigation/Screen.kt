package chn.phm.presentation.base.navigation

sealed class Screen(val route: String) {
    object SplashScreen : Screen("splash")
    object OnBoardingScreen : Screen("on_boarding")
    object HomeScreen : Screen("home")
}
