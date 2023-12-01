package chn.phm.presentation.screens.history

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import chn.phm.presentation.screens.history.components.NoHistory

@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier,
    onNavigateToFashionly: () -> Unit
) {
    NoHistory {
        onNavigateToFashionly.invoke()
    }
}
