package com.batabumi.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.batabumi.app.data.local.ChatDatabase
import com.batabumi.app.data.repository.ChatRepository
import com.batabumi.app.ui.navigation.AppNavigation
import com.batabumi.app.ui.theme.BataBumiTheme
import com.batabumi.app.config.ApiConfig
import com.batabumi.app.viewmodel.ChatViewModel
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BataBumiTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val database = ChatDatabase.getDatabase(this)
                    val repository = ChatRepository(database)
                    val viewModel = ChatViewModel(repository)
                    
                    val apiKey = ApiConfig.GROQ_API_KEY
                    
                    AppNavigation(
                        navController = navController,
                        chatViewModel = viewModel,
                        apiKey = apiKey
                    )
                }
            }
        }
    }
}