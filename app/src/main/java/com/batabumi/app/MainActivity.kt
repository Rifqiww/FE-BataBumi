package com.batabumi.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.batabumi.app.data.local.ChatDatabase
import com.batabumi.app.data.repository.ChatRepository
import com.batabumi.app.ui.navigation.AppNavigation
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.batabumi.app.ui.theme.BataBumiTheme
import com.batabumi.app.config.ApiConfig
import com.batabumi.app.viewmodel.ChatViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// Screens
import com.batabumi.app.ui.screens.LoginScreen
import com.batabumi.app.ui.screens.ForgotPasswordScreen
import com.batabumi.app.ui.screens.CreateAccountScreen
import com.batabumi.app.ui.navigation.HomeScreen
import com.batabumi.app.ui.screens.servicecategory.*
import com.batabumi.app.ui.screens.ConsultationScreen
import com.batabumi.app.ui.screens.OrderScreen
import com.batabumi.app.ui.screens.ProfileScreen
import com.batabumi.app.ui.components.BottomNavigationBar
import com.batabumi.app.ui.screens.payment.FormPaymentScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var isLoading by remember { mutableStateOf(true) }
            val scope = rememberCoroutineScope()

            // Navigation state
            val screenStack = remember { mutableStateListOf("FormPaymentScreen") }
            val currentRoute = screenStack.last()

            val canGoBack = remember { derivedStateOf { screenStack.size > 1 } }

            fun navigateTo(route: String, clearStack: Boolean = false) {
                if (clearStack) {
                    screenStack.clear()
                }
                if (route != currentRoute) {
                    screenStack.add(route)
                }
            }

            fun goBack() {
                if (screenStack.size > 1) {
                    screenStack.removeAt(screenStack.lastIndex)
                }
            }

            // Handle back press
            BackHandler(enabled = canGoBack.value) {
                goBack()
            }

            // Splash Screen
            LaunchedEffect(Unit) {
                scope.launch {
                    delay(2000)
                    isLoading = false
                }
            }
            splashScreen.setKeepOnScreenCondition { isLoading }

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
                // Define screens where bottom navigation should be visible
                val showBottomBar = currentRoute in listOf(
                    "home", "orders", "consultation", "profile",
                    "tukang_konstruksi", "tukang_konstruksi2", "tukang_konstruksi3",
                    "renov_rumah", "tukang_perbaikan", "tukang_bangunan"
                )

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        if (showBottomBar) {
                            BottomNavigationBar(
                                currentRoute = when (currentRoute) {
                                    in listOf(
                                        "tukang_konstruksi", "tukang_konstruksi2", "tukang_konstruksi3",
                                        "renov_rumah", "tukang_perbaikan", "tukang_bangunan"
                                    ) -> "home"

                                    else -> currentRoute
                                },
                                onNavigate = { route: String ->
                                    if (route in listOf("home", "orders", "consultation", "profile")) {
                                        // For main navigation items, clear the stack and go to the selected screen
                                        navigateTo(route, clearStack = true)
                                    } else {
                                        // For other screens, just navigate normally
                                        navigateTo(route)
                                    }
                                }
                            )
                        }
                    }
                ) { innerPadding ->
                    if (!isLoading) {
                        // Main content with navigation
                        AnimatedContent(
                            targetState = currentRoute,
                            transitionSpec = {
                                slideInHorizontally(
                                    initialOffsetX = { fullWidth -> fullWidth },
                                    animationSpec = tween(300)
                                ) + fadeIn(animationSpec = tween(300)) togetherWith
                                        slideOutHorizontally(
                                            targetOffsetX = { fullWidth -> -fullWidth },
                                            animationSpec = tween(300)
                                        ) + fadeOut(animationSpec = tween(300))
                            },
                            label = "screen_transition"
                        ) { route ->
                            when (route) {
                                // 🔹 Auth Screens
                                "login" -> LoginScreen(
                                    modifier = Modifier.padding(innerPadding),
                                    onForgotPasswordClick = { navigateTo("forgot") },
                                    onCreateAccountClick = { navigateTo("create") },
                                    onLoginSuccess = { navigateTo("home") }
                                )

                                "forgot" -> ForgotPasswordScreen(modifier = Modifier.padding(innerPadding))
                                "create" -> CreateAccountScreen(
                                    modifier = Modifier.padding(innerPadding),
                                    onLoginClick = { navigateTo("login") }
                                )

                                // 🔹 Main Screens (with BottomNavigation)
                                "home" -> {
                                    LaunchedEffect(Unit) {
                                        // Ensure we're at the top of the home stack
                                        if (screenStack.any { it == "home" && screenStack.last() != "home" }) {
                                            screenStack.removeAll { it == "home" }
                                            screenStack.add("home")
                                        }
                                    }
                                    HomeScreen(
                                        modifier = Modifier.padding(innerPadding),
                                        onNavigateToHome = { navigateTo("home") },
                                        onNavigateToService = { serviceRoute -> navigateTo(serviceRoute) }
                                    )
                                }

                                "orders" -> OrderScreen(
                                    modifier = Modifier.padding(innerPadding),
                                    onBack = { goBack() },
                                    onNavigate = { route: String -> navigateTo(route) }
                                )

                                "consultation" -> ConsultationScreen(
                                    modifier = Modifier.padding(innerPadding),
                                    onBack = { goBack() },
                                    onNavigate = { route: String -> navigateTo(route) }
                                )

                                "profile" -> ProfileScreen(
                                    modifier = Modifier.padding(innerPadding),
                                    onBack = { goBack() },
                                    onNavigate = { route: String -> navigateTo(route) }
                                )

                                // 🔹 Service Category Screens
                                "tukang_konstruksi" -> TukangKonstruksiScreen(
                                    currentRoute = route,
                                    onNavigate = { newRoute: String -> navigateTo(newRoute) },
                                    onBack = { goBack() }
                                )

                                "renov_rumah" -> RenovRumahScreen(
                                    currentRoute = route,
                                    onNavigate = { newRoute: String -> navigateTo(newRoute) },
                                    onBack = { goBack() }
                                )

                                "tukang_perbaikan" -> TukangPerbaikanScreen(
                                    currentRoute = route,
                                    onNavigate = { newRoute: String -> navigateTo(newRoute) },
                                    onBack = { goBack() }
                                )

                                "tukang_bangunan" -> TukangBangunanScreen(
                                    currentRoute = route,
                                    onNavigate = { newRoute: String -> navigateTo(newRoute) },
                                    onBack = { goBack() }
                                )

                                "tukang_konstruksi2" -> TukangKonstruksi2Screen(
                                    currentRoute = route,
                                    onNavigate = { newRoute: String -> navigateTo(newRoute) },
                                    onBack = { goBack() }
                                )

                                "tukang_konstruksi3" -> TukangKonstruksi3Screen(
                                    currentRoute = route,
                                    onNavigate = { newRoute: String -> navigateTo(newRoute) },
                                    onBack = { goBack() }
                                )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}