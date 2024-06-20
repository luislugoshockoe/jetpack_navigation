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
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.shockoe.navigationworkshop.AppGraph

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
            startDestination = AppGraph.MainGraph.route
        ) {
            mainGraph(navController)
        }
    }
}

fun NavGraphBuilder.mainGraph(navController: NavController) {
    navigation(
        startDestination = AppGraph.MainGraph.initRoute,
        route = AppGraph.MainGraph.route
    ) {
        homeGraph(navController)

        faceGraph(navController)

        cartGraph(navController)
    }
}

fun NavGraphBuilder.homeGraph(navController: NavController) {
    navigation(
        startDestination = AppGraph.MainGraph.HomeGraph.initRoute,
        route = AppGraph.MainGraph.HomeGraph.route
    ) {
        composable(AppGraph.MainGraph.HomeGraph.Home.route) {
            TabContentScreen(
                title = AppGraph.MainGraph.HomeGraph.Home.title,
                onNext = {
                    navController.navigate(AppGraph.MainGraph.HomeGraph.Home1.route)
                },
                onBack = null
            )
        }
        composable(AppGraph.MainGraph.HomeGraph.Home1.route) {
            TabContentScreen(
                title = AppGraph.MainGraph.HomeGraph.Home1.title,
                onNext = {
                    navController.navigate(AppGraph.MainGraph.HomeGraph.Home2.route)
                },
                onBack = { navController.navigateUp() }
            )
        }
        composable(AppGraph.MainGraph.HomeGraph.Home2.route) {
            TabContentScreen(
                title = AppGraph.MainGraph.HomeGraph.Home2.title,
                onNext = null,
                onBack = { navController.navigateUp() }
            )
        }
    }
}

fun NavGraphBuilder.faceGraph(navController: NavController) {
    navigation(
        startDestination = AppGraph.MainGraph.FaceGraph.initRoute,
        route = AppGraph.MainGraph.FaceGraph.route
    ) {
        composable(AppGraph.MainGraph.FaceGraph.Face.route) {
            TabContentScreen(
                title = AppGraph.MainGraph.FaceGraph.Face.title,
                onNext = {
                    navController.navigate(AppGraph.MainGraph.FaceGraph.Face1.route)
                },
                onBack = null
            )
        }
        composable(AppGraph.MainGraph.FaceGraph.Face1.route) {
            TabContentScreen(
                title = AppGraph.MainGraph.FaceGraph.Face1.title,
                onNext = null,
                onBack = { navController.navigateUp() }
            )
        }
    }
}

fun NavGraphBuilder.cartGraph(navController: NavController) {
    navigation(
        startDestination = AppGraph.MainGraph.CartGraph.initRoute,
        route = AppGraph.MainGraph.CartGraph.route
    ) {
        composable(AppGraph.MainGraph.CartGraph.Cart.route) {
            TabContentScreen(
                title = AppGraph.MainGraph.CartGraph.Cart.title,
                onNext = null,
                onBack = null
            )
        }
    }
}


@Composable
fun BottomNavigation(navController: NavController) {
    val tabs = listOf(
        AppGraph.MainGraph.HomeGraph,
        AppGraph.MainGraph.FaceGraph,
        AppGraph.MainGraph.CartGraph,
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