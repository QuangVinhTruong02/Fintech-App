package com.example.fintechapp.ui.navigation

import com.example.fintechapp.data.response.AgencyResponse

open class Screens(val route: String) {

    object Splash : Screens("splash")

    object Onboarding : Screens("onboarding")

    object Home : Screens("home")

    object SignIn : Screens("SignIn")

    object CreateAgent : Screens("CreateAgent/{agencyId}"){
        fun createRoute(agencyId: String?): String = "CreateAgent/$agencyId"
    }
}