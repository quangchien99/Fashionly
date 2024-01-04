package chn.phm.presentation.screens.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import chn.phm.presentation.base.theme.BackgroundLight

@Composable
fun SettingScreen(
    navHostController: NavHostController,
    viewModel: SettingsViewModel,
    modifier: Modifier = Modifier.background(color = BackgroundLight)
) {

    val currentSettings by viewModel.currentSettings.collectAsState()

    var isEnableDarkMode by rememberSaveable { mutableStateOf(currentSettings.isEnableDarkMode) }
    var negativePrompt by rememberSaveable { mutableStateOf(currentSettings.fashionlySettings.negativePrompt) }
    var height by rememberSaveable { mutableStateOf(currentSettings.fashionlySettings.height.toString()) }
    var width by rememberSaveable { mutableStateOf(currentSettings.fashionlySettings.width.toString()) }
    var guidanceScale by rememberSaveable { mutableStateOf(currentSettings.fashionlySettings.guidanceScale.toString()) }
    var numInferenceSteps by rememberSaveable { mutableStateOf(currentSettings.fashionlySettings.numInferenceSteps.toString()) }
    var seed by rememberSaveable { mutableStateOf(currentSettings.fashionlySettings.seed.toString()) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Enable Dark Mode")
            Spacer(Modifier.width(8.dp))
            Switch(checked = isEnableDarkMode, onCheckedChange = {
                isEnableDarkMode = it
                viewModel.updateIsEnableDarkMode(isEnableDarkMode)
            })
        }

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = negativePrompt,
            onValueChange = { negativePrompt = it },
            label = { Text("Negative Prompt") }
        )

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = height,
            onValueChange = { height = it },
            label = { Text("Image Height") }
        )

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = width,
            onValueChange = { width = it },
            label = { Text("Image Width") }
        )

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = guidanceScale,
            onValueChange = { guidanceScale = it },
            label = { Text("Guidance Scale") }
        )

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = numInferenceSteps,
            onValueChange = { numInferenceSteps = it },
            label = { Text("Number of Inference Steps") }
        )

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = seed,
            onValueChange = { seed = it },
            label = { Text("Seed") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            viewModel.updateFashionlySettings(
                negativePrompt = negativePrompt,
                height = height.toInt(),
                width = width.toInt(),
                guidanceScale = guidanceScale.toDouble(),
                numInferenceSteps = numInferenceSteps.toInt(),
                seed = seed.toInt(),
            )
        }) {
            Text("Save Changes")
        }
    }
}
