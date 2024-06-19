package com.shockoe.navigationworkshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.shockoe.navigationworkshop.ui.screens.LoginScreen
import com.shockoe.navigationworkshop.ui.screens.MainScreen
import com.shockoe.navigationworkshop.ui.screens.RegisterScreen
import com.shockoe.navigationworkshop.ui.theme.NavigationWorkshopTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationWorkshopTheme {
                val navController = rememberNavController()
                RootNavigationGraph(navController = navController)
            }
        }
    }
}


@Composable
fun RootNavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = AppGraph.initRoute.route) {
        composable(AppGraph.AuthGraph.Login.route) {
            LoginScreen(
                onLoginClick = {
                    navController.navigate(AppGraph.MainGraph.initRoute.route, navOptions {
                        popUpTo(AppGraph.AuthGraph.Login.route) {
                            inclusive = true
                        }
                    })
                },
                onRegisterClick = {
                    navController.navigate(AppGraph.AuthGraph.Register.route)
                }
            )
        }

        composable(AppGraph.AuthGraph.Register.route) {
            RegisterScreen {
                navController.navigateUp()
            }
        }

        composable(AppGraph.MainGraph.initRoute.route) {
            MainScreen()
        }
    }
}
