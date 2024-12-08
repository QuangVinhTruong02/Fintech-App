package com.example.fintechapp.ui.navigation


import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fintechapp.ui.base.DtgAppState
import com.example.fintechapp.ui.screens.home.HomeScreen
import com.example.fintechapp.ui.screens.onboarding.OnboardingScreen
import com.example.fintechapp.ui.screens.splash.SplashScreen
import com.example.fintechapp.ui.sign_in.SignInScreen


@Composable
fun AppNavHost(
    appNavigation: AppNavigation,
    appState: DtgAppState,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = appNavigation.navController,
        startDestination = Screens.Splash.route,
        modifier = modifier,
        enterTransition = {
            EnterTransition.None
        },
        exitTransition = {
            ExitTransition.None
        }
    ) {
        composable(Screens.Splash.route) {
            SplashScreen(
                appState = appState,
                onNavigateToMain = appNavigation::navigateToHome,
//                onNavigateToLogin = appNavigation::navigateToLogin,
                onNavigateToOnBoarding = appNavigation::navigateToOnboarding
            )
        }

        composable(Screens.Onboarding.route) {
            OnboardingScreen(
                appState = appState,
//                onNavigateToSignUp = appNavigation::navigateToSignUp,
                onNavigateToSignIn = appNavigation::navigateToSignIn
            )
        }

        composable(Screens.SignIn.route) {
            SignInScreen(
                appState = appState,
                onNavigateBack = appNavigation::navigateBack,
                onNavigateToMain = appNavigation::navigateToHome
            )
        }
        composable(Screens.Home.route) {
            HomeScreen(
//                appState = appState,
                onNavigateToSignIn = appNavigation::navigateToSignIn
            )
        }

    }

}