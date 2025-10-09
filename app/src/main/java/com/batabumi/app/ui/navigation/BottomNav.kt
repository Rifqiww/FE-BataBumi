package com.batabumi.app.ui.navigation

sealed class BottomNav(val route: String) {
    object Home : BottomNav("home")
    object Order : BottomNav("order")
    object Consultation : BottomNav("consultation")
    object Profile : BottomNav("profile")
    
    companion object {
        val bottomNavItems = listOf(
            Home,
            Order,
            Consultation,
            Profile
        )
    }
}
