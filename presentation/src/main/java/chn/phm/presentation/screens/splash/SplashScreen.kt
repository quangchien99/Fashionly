package chn.phm.presentation.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import chn.phm.presentation.R
import chn.phm.presentation.base.navigation.Screen
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun SplashScreen(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)
) {
    val viewModel: SplashViewModel = hiltViewModel()

    val isFirstOpen by viewModel.isFirstOpen.observeAsState()

    LaunchedEffect(key1 = isFirstOpen) {
        isFirstOpen?.let {
            if (it) {
                navHostController.navigate(Screen.OnBoardingScreen.route) {
                    popUpTo(Screen.SplashScreen.route) { inclusive = true }
                }
            } else {
                navHostController.navigate(Screen.FashionlyScreen.route) {
                    popUpTo(Screen.SplashScreen.route) { inclusive = true }
                }
            }
        }
    }

    Box(
        modifier = modifier
    ) {
        val compositionLoading by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))
        LottieAnimation(
            composition = compositionLoading,
            iterations = LottieConstants.IterateForever,
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
        )
    }
}
