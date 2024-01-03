package chn.phm.presentation.screens.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import chn.phm.domain.model.fashionly.FashionlyResultDomain
import chn.phm.presentation.base.navigation.Screen
import chn.phm.presentation.base.theme.BackgroundLight
import chn.phm.presentation.screens.history.components.HistoryCard
import chn.phm.presentation.screens.history.components.NoHistory

@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier
        .fillMaxSize()
        .background(color = BackgroundLight),
    navHostController: NavHostController,
    viewModel: HistoryViewModel
) {
    val results = viewModel.fashionlyResults.collectAsState()

    if (results.value.isEmpty()) {
        NoHistory {
            navHostController.navigate(Screen.FashionlyScreen.route) {
                popUpTo(navHostController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    } else {
        HistoryList(results.value, modifier)
    }
}

@Composable
fun HistoryList(
    results: List<FashionlyResultDomain>,
    modifier: Modifier
) {
    LazyColumn(modifier = modifier) {
        items(results) { result ->
            HistoryCard(fashionlyResult = result)
        }
    }
}
