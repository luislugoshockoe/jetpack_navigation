package com.shockoe.navigationworkshop.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.shockoe.navigationworkshop.AppGraph
import com.shockoe.navigationworkshop.ui.screens.Home.Tab1Screen
import com.shockoe.navigationworkshop.ui.screens.Home.Tab2Screen
import com.shockoe.navigationworkshop.ui.screens.Home.Tab3Screen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigation(navController = navController) },
        modifier = Modifier.fillMaxSize()
    ) { _ ->
        NavHost(
            navController = navController,
            startDestination = AppGraph.MainGraph.initRoute.route
        ) {
            composable(AppGraph.MainGraph.HomeGraph.Tab1.route) {
                Tab1Screen()
            }
            composable(AppGraph.MainGraph.HomeGraph.Tab2.route) {
                Tab2Screen()
            }
            composable(AppGraph.MainGraph.HomeGraph.Tab3.route) {
                Tab3Screen()
            }
        }
    }
}


@Composable
fun BottomNavigation(navController: NavController) {
    val tabs = listOf(
        AppGraph.MainGraph.HomeGraph.Tab1,
        AppGraph.MainGraph.HomeGraph.Tab2,
        AppGraph.MainGraph.HomeGraph.Tab3,
    )
    var selectedItem by remember { mutableStateOf(0) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
        tabs.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                onClick = {
                    selectedItem = index
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painter = rememberVectorPainter(image = item.icon),
                        contentDescription = item.title
                    )
                },
                label = {
                    Text(text = item.title)
                },
                alwaysShowLabel = true
            )
        }
    }
}