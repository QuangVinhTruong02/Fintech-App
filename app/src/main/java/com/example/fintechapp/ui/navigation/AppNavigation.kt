package com.example.fintechapp.ui.navigation


import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.fintechapp.common.extensions.findActivity
import kotlinx.coroutines.CoroutineScope


@Composable
fun rememberAppNavigation(
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    context: Context = LocalContext.current
): AppNavigation = remember {
    AppNavigation(navController, coroutineScope, context)
}

class AppNavigation(
    val navController: NavHostController,
    private val coroutineScope: CoroutineScope,
    private val context: Context
) {

    fun navigateToOnboarding() {
        navController.navigate(Screens.Onboarding.route) {
            popUpTo(0)
        }
    }

    fun navigateToSignUp() {
        navController.navigate(Screens.SignUp.route)
    }

    fun navigateToSignIn() {
        navController.navigate(Screens.SignIn.route)
    }

    fun navigateToUserProfile() {
        navController.navigate(Screens.UserProfile.route)
    }

    fun navigateToHome(){
        navController.navigate(Screens.Home.route) {
            popUpTo(0)
        }
    }

//    fun navigateToHome() {
//        navController.navigate(Screens.Home.route) {
//            popUpTo(0)
//        }
//    }
//
//    fun navigateToLogin() {
//        navController.navigate(Screens.Login.route) {
//            popUpTo(0)
//        }
//    }


    fun navigateBack() {
        if (!navController.popBackStack()) {
            context.findActivity()?.finish()
        }
    }
}
