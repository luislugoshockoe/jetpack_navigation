package com.shockoe.navigationworkshop

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

object AppGraph {
    val initRoute: NavItem = AuthGraph.Login

    data object AuthGraph {
        data object Login : NavItem("LoginScreen")
        data object Register : NavItem("RegisterScreen")
    }

    data object MainGraph {
        val initRoute: NavItem = HomeGraph.initRoute

        data object HomeGraph {
            val initRoute: NavItem = Tab1

            data object Tab1 : NavItem.BottomNavItem("Tab1", "Tab1", Icons.Default.Home)
            data object Tab2 : NavItem.BottomNavItem("Tab2", "Tab2", Icons.Default.Face)
            data object Tab3 : NavItem.BottomNavItem("Tab3", "Tab3", Icons.Default.ShoppingCart)
        }
    }
}

sealed class NavItem(open var route: String) {
    sealed class BottomNavItem(
        override var route: String,
        var title: String,
        var icon: ImageVector
    ) : NavItem(route)
}

