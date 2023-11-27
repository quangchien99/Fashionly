package chn.phm.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)
) {
    Text(text = "Home Screen")
}
