package com.shockoe.navigationworkshop

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

data object AppGraph : NavItem.NavGraph {
    override var initRoute: String = AuthGraph.route
    override var route: String = "App"

    data object AuthGraph : NavItem.NavGraph {
        override var initRoute: String = Login.route
        override var route: String = "Auth"

        data object Login : NavItem {
            override var route: String = "LoginScreen"

        }

        data object Register : NavItem {
            override var route: String = "RegisterScreen"

        }
    }

    data object MainGraph : NavItem.NavGraph {
        override var route: String = "Main"
        override var initRoute: String = HomeGraph.route

        data object HomeGraph : NavItem.BottomNavGraph {
            override var route = "Home"
            override var initRoute: String = Home.route
            override var title: String = "Home"
            override var icon: ImageVector = Icons.Default.Home

            data object Home : NavItem.BottomNavItem {
                override var title: String = "Home"
                override var route: String = "Home0"
            }

            data object Home1 : NavItem.BottomNavItem {
                override var title: String = "Home1.1"
                override var route: String = "Home1"
            }

            data object Home2 : NavItem.BottomNavItem {
                override var title: String = "Home1.2"
                override var route: String = "Home2"
            }
        }

        data object FaceGraph : NavItem.BottomNavGraph {
            override var route = "Face"
            override var initRoute: String = Face.route
            override var title: String = "Face"
            override var icon: ImageVector = Icons.Default.Face

            data object Face : NavItem.BottomNavItem {
                override var title: String = "Face"
                override var route: String = "Face0"
            }

            data object Face1 : NavItem.BottomNavItem {
                override var title: String = "Face1.1"
                override var route: String = "Face1"
            }
        }

        data object CartGraph : NavItem.BottomNavGraph {
            override var route = "Cart"
            override var initRoute: String = Cart.route
            override var title: String = "Cart"
            override var icon: ImageVector = Icons.Default.ShoppingCart

            data object Cart : NavItem.BottomNavItem {
                override var title: String = "Cart"
                override var route: String = "Cart0"
            }
        }

    }
}

sealed interface NavItem {
    var route: String

    interface BottomNavItem : NavItem {
        var title: String
    }

    interface NavGraph : NavItem {
        var initRoute: String
    }

    interface BottomNavGraph : NavItem {
        var initRoute: String
        var title: String
        var icon: ImageVector
    }
}

