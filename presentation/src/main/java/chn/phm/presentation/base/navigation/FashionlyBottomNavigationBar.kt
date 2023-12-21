package chn.phm.presentation.base.navigation

import android.util.Log
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import chn.phm.presentation.R

@Composable
fun BottomNavigationBar(
    navController: NavController
) {

    NavigationBar(
        modifier = Modifier.height(64.dp),
        containerColor = Color.White
    ) {
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

        NavigationBarItem(
            selected = currentRoute == Screen.FashionlyScreen.route,
            icon = {
                Icon(
                    painterResource(id = R.drawable.ic_home),
                    contentDescription = stringResource(id = R.string.home_screen_fashionly_name)
                )
            },
            onClick = {
                if (currentRoute != Screen.FashionlyScreen.route) {
                    navController.navigate(Screen.FashionlyScreen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
        )

        NavigationBarItem(
            selected = currentRoute == Screen.HistoryScreen.route,
            icon = {
                Icon(
                    painterResource(id = R.drawable.ic_history),
                    contentDescription = stringResource(id = R.string.home_screen_history_name)
                )
            },
            onClick = {
                Log.e("Chien", "Check 1 $currentRoute")
                if (currentRoute != Screen.HistoryScreen.route) {
                    Log.e("Chien", "Check 2")
                    navController.navigate(Screen.HistoryScreen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
        )
        NavigationBarItem(
            selected = currentRoute == Screen.SettingsScreen.route,
            icon = {
                Icon(
                    painterResource(id = R.drawable.ic_settings),
                    contentDescription = stringResource(id = R.string.home_screen_setting_name)
                )
            },
            onClick = {
                if (currentRoute != Screen.SettingsScreen.route) {
                    navController.navigate(Screen.SettingsScreen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
        )
    }
}
