package com.inrotate.cpc.ui.main

import android.content.res.Configuration
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.inrotate.cpc.R
import com.inrotate.cpc.ui.navigation.NavBarItems
import com.inrotate.cpc.ui.navigation.NavRoutes
import com.inrotate.cpc.ui.theme.CPCTheme


@Composable
fun MainView() {
    val navController = rememberNavController()
    Column(
        modifier = Modifier,
    ) {
        NavHost(
            navController,
            startDestination = NavRoutes.Home.route,
            modifier = Modifier.weight(1f),
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
            popEnterTransition = { EnterTransition.None },
            popExitTransition = { ExitTransition.None },
        ) {
            composable(NavRoutes.Home.route) {
                MainScreen(
                    route = NavRoutes.Home.route,

                    )
            }
            composable(NavRoutes.Contacts.route) {
                MainScreen(
                    route = NavRoutes.Contacts.route,
                )
            }
            composable(NavRoutes.About.route) {
                MainScreen(
                    route = NavRoutes.About.route,
                )
            }
        }
        BottomNavigationBar(navController = navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    route: String
) {
    val model = remember { MainModel(route) }
    val state = model.state.observeAsState(initial = MainState()).value

    DisposableEffect(Unit) {
        onDispose { }
    }
    var floatValue by remember { mutableStateOf("") }
    var showResults by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(stringResource(id = state.title))
                }
            )
        },
        /*floatingActionButton = {
            FloatingActionButton(onClick = { }) {
                Icon(Icons.Default.Settings, contentDescription = "Settings")
            }
        }*/
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            ViewItem(
                title = stringResource(id = R.string.harvest_level)
            ) {

                FloatInputField(
                    value = floatValue,
                    onValueChange = { newValue ->
                        floatValue = newValue
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            TextButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                onClick = {
                    model.calculate(floatValue)
                    showResults = true
                },
                colors = ButtonDefaults.buttonColors(),
            ) {
                Box(
                    modifier = Modifier,
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = stringResource(id = R.string.calc_award))
                }
            }
            if (showResults) {
                LazyColumn {
                    item {
                        ViewItem(
                            title = stringResource(id = R.string.calc_award1)
                        ) {
                            Text(text = state.kilosPerHectare.toString())
                        }
                    }

                    item {
                        ViewItem(
                            title = stringResource(id = R.string.calc_award2)
                        ) {
                            Text(text = state.tonsPer100Hectares.toString())
                        }
                    }

                    item {
                        ViewItem(
                            title = stringResource(id = R.string.calc_award3) + " " + state.culture.coast1
                        ) {
                            Text(text = state.rublesPer100Hectares15.toString())
                        }
                    }
                    item {
                        ViewItem(
                            title = stringResource(id = R.string.calc_award4) + " " + state.culture.coast2
                        ) {
                            Text(text = state.rublesPer100Hectares18.toString())
                        }
                    }

                }

            }
        }
    }
}


@Composable
fun BottomNavigationBar(navController: NavController) {
    NavigationBar {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        NavBarItems.BarItems.forEach { navItem ->
            NavigationBarItem(
                selected = currentRoute == navItem.route,
                onClick = {
                    navController.navigate(navItem.route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        modifier = Modifier
                            .size(24.dp),
                        painter = painterResource(navItem.image),
                        contentDescription = stringResource(id = navItem.title)
                    )
                },
                label = {
                    Text(text = stringResource(id = navItem.title))
                }
            )
        }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark"
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "DefaultPreviewLight"
)
@Composable
fun ReplyAppPreview() {
    CPCTheme(dynamicColor = false) {
        MainView()
    }
}