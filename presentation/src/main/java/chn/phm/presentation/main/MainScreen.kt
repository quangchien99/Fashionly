package chn.phm.presentation.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import chn.phm.presentation.R
import chn.phm.presentation.base.navigation.BottomNavigationBar
import chn.phm.presentation.base.navigation.FashionlyNavHost
import chn.phm.presentation.base.navigation.Screen
import chn.phm.presentation.base.theme.BackgroundLight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavHostController
) {
    val snackBarHostState = SnackbarHostState()
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.SplashScreen.route && currentRoute != Screen.OnBoardingScreen.route) {
                BottomNavigationBar(navController)
            }
        },
        topBar = {
            if (currentRoute != Screen.SplashScreen.route && currentRoute != Screen.OnBoardingScreen.route) {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(id = R.string.home_screen_fashionly_name),
                            style = MaterialTheme.typography.displaySmall,
                        )
                    },
                    navigationIcon = {
                        Icon(
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                            painter = painterResource(id = R.drawable.ic_fashion_design),
                            contentDescription = "App Icon",
                            // Keep original color of the icon
                            tint = Color.Unspecified
                        )
                    },
                    colors = TopAppBarColors(
                        containerColor = BackgroundLight,
                        scrolledContainerColor = Color.White,
                        navigationIconContentColor = LocalContentColor.current,
                        titleContentColor = Color.Black,
                        actionIconContentColor = Color.Black,
                    )
                )
            }
        }
    ) {
        FashionlyNavHost(
            navController = navController,
            snackBarHostState = snackBarHostState,
            paddingValues = it
        )
    }
}
