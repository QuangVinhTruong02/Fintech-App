package com.example.fintechapp.core.helper

sealed class Routers(val destination: String) {
    data object Splash : Routers("splash")
    data object Onboarding : Routers("onboarding")
    data object SignUp : Routers("singUp")
}