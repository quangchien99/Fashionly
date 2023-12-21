package chn.phm.presentation.screens.history

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import chn.phm.presentation.base.navigation.Screen
import chn.phm.presentation.screens.history.components.NoHistory

@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
) {
    val currentRoute = navHostController.currentBackStackEntryAsState().value?.destination?.route
    NoHistory {
        if (currentRoute != Screen.HistoryScreen.route) {
            navHostController.navigate(Screen.FashionlyScreen.route) {
                popUpTo(navHostController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    }
}
