package chn.phm.presentation.screens.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import chn.phm.presentation.base.theme.BackgroundLight

@Composable
fun SettingScreen(
    navHostController: NavHostController,
    modifier: Modifier = Modifier.background(color = BackgroundLight)
) {
    Column(modifier = modifier.fillMaxSize()) {
        Text(text = "Setting")
    }
}
