package com.example.fintechapp.ui.navigation


import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.fintechapp.common.AppConst
import com.example.fintechapp.common.extensions.findActivity
import com.example.fintechapp.data.request.AgencyRequest
import com.example.fintechapp.data.response.AgencyResponse
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

    fun navigateToSignIn() {
        navController.navigate(Screens.SignIn.route) {
            popUpTo(0)
        }
    }

    fun navigateToHome() {
        navController.navigate(Screens.Home.route) {
            popUpTo(0)
        }
    }

    fun navigateToCreateAgent(agency: String) {
        navController.navigate(Screens.CreateAgent.createRoute(agency))
    }

    fun navigateToDetailAgent(agencyCode: String){
        navController.navigate(Screens.DetailAgent.createRoute(agencyCode))
    }

    fun navigateToCreateProduct(){
        navController.navigate(Screens.CreateProduct.route)
    }

    fun navigateToUpdateProduct(productId: Int){
        navController.navigate(Screens.UpdateProduct.createRoute(productId))
    }

    fun previousBackStackAgency(hasNewData: Boolean) {
        navController.previousBackStackEntry?.savedStateHandle?.set(
            AppConst.AGENCY_RETURN_KEY,
            hasNewData
        )
        navController.popBackStack()
    }

    fun navigateBack() {
        if (!navController.popBackStack()) {
            context.findActivity()?.finish()
        }
    }
}
