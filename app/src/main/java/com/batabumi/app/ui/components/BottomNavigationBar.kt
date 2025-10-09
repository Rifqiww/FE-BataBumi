package com.batabumi.app.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.batabumi.app.ui.theme.Black
import com.batabumi.app.ui.theme.YellowBright

/**
 * Data class representing an item in the bottom navigation bar
 */
data class BottomNavItem(
    val title: String,
    val icon: ImageVector,
    val route: String
)

/**
 * Bottom navigation bar component for the main screens
 */
@Composable
fun BottomNavigationBar(
    currentRoute: String,
    onNavigate: (String) -> Unit
) {
    // List of navigation items
    val bottomNavItems = listOf(
        BottomNavItem("Home", Icons.Default.Home, "home"),
        BottomNavItem("Order", Icons.Default.List, "orders"),
        BottomNavItem("Consultation", Icons.Default.Chat, "consultation"),
        BottomNavItem("Profile", Icons.Default.Person, "profile")
    )

    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 8.dp
    ) {
        bottomNavItems.forEach { item ->
            val isSelected = when (currentRoute) {
                item.route -> true
                in listOf("tukang_konstruksi", "tukang_konstruksi2", "tukang_konstruksi3", 
                         "renov_rumah", "tukang_perbaikan", "tukang_bangunan") -> item.route == "home"
                else -> false
            }
            
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                        tint = if (isSelected) YellowBright else Color.Gray
                    )
                },
                label = { 
                    Text(
                        text = item.title,
                        color = if (isSelected) YellowBright else Color.Gray,
                        fontSize = 12.sp
                    ) 
                },
                selected = isSelected,
                onClick = { 
                    if (currentRoute != item.route) {
                        onNavigate(item.route)
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = YellowBright,
                    selectedTextColor = YellowBright,
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}
