package com.batabumi.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.batabumi.app.ui.screens.ChatScreen
import com.batabumi.app.viewmodel.ChatViewModel

@Composable
fun AppNavigation(
    navController: NavHostController,
    chatViewModel: ChatViewModel,
    apiKey: String
) {
    NavHost(
        navController = navController,
        startDestination = "chat"
    ) {
        composable("chat") {
            ChatScreen(
                viewModel = chatViewModel,
                apiKey = apiKey
            )
        }
    }
}
