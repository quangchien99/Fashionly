package chn.phm.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import chn.phm.presentation.R
import chn.phm.presentation.screens.fashionly.FashionlyScreen
import chn.phm.presentation.screens.history.HistoryScreen
import chn.phm.presentation.screens.setting.SettingScreen

enum class HomeTab {
    Fashionly,
    History,
    Setting
}

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    modifier: Modifier = Modifier.background(Color.Transparent)
) {
    var currentTab by remember { mutableStateOf(HomeTab.Fashionly) }
    val snackbarHostState = SnackbarHostState()

    Scaffold(
        modifier = Modifier.background(Color.Transparent),
        bottomBar = {
            BottomNavigationBar(currentTab) { selectedTab ->
                currentTab = selectedTab
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) {
        when (currentTab) {
            HomeTab.Fashionly -> FashionlyScreen(
                snackbarHostState = snackbarHostState
            )
            HomeTab.History -> HistoryScreen(onNavigateToFashionly = {
                currentTab = HomeTab.Fashionly
            })
            HomeTab.Setting -> SettingScreen(navHostController)
        }
    }
}

@Composable
fun BottomNavigationBar(
    currentTab: HomeTab,
    onTabSelected: (HomeTab) -> Unit
) {
    NavigationBar(
        modifier = Modifier.height(64.dp),
        containerColor = Color.White
    ) {
        NavigationBarItem(
            selected = currentTab == HomeTab.Fashionly,
            label = { Text(stringResource(id = R.string.home_screen_fashionly_name)) },
            icon = {
                Icon(
                    Icons.Default.Home,
                    contentDescription = stringResource(id = R.string.home_screen_fashionly_name)
                )
            },
            onClick = { onTabSelected(HomeTab.Fashionly) }
        )

        NavigationBarItem(
            selected = currentTab == HomeTab.History,
            label = { Text(stringResource(id = R.string.home_screen_history_name)) },
            icon = {
                Icon(
                    painterResource(id = R.drawable.ic_history),
                    contentDescription = stringResource(id = R.string.home_screen_history_name)
                )
            },
            onClick = { onTabSelected(HomeTab.History) }
        )
        NavigationBarItem(
            selected = currentTab == HomeTab.Setting,
            label = { Text(stringResource(id = R.string.home_screen_setting_name)) },
            icon = {
                Icon(
                    Icons.Default.Settings,
                    contentDescription = stringResource(id = R.string.home_screen_setting_name)
                )
            },
            onClick = { onTabSelected(HomeTab.Setting) }
        )
    }
}
