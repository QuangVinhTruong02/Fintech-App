package com.example.fintechapp.ui.base

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fintechapp.core.helper.Routers
import com.example.fintechapp.ui.sign_up.SignUpScreen
import com.example.fintechapp.ui.onboarding.OnboardingScreen
import com.example.fintechapp.ui.splash.SplashScreen

private const val SLASH = "/"


@Composable
fun NavHostNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = Routers.Splash.destination) {
        composable(Routers.Splash.destination) {
            SplashScreen(navController)
        }
        composable(Routers.Onboarding.destination) {
            OnboardingScreen(navController)
        }
        composable(Routers.SignUp.destination) {
            SignUpScreen(navController)
        }
    }
}