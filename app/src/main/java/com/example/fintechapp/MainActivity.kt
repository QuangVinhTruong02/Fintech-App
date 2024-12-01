package com.example.fintechapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.fintechapp.ui.base.NavHostNavigation
import com.example.fintechapp.ui.sign_up.SignUpScreen
import com.example.fintechapp.ui.theme.FintechAppTheme
import com.example.fintechapp.ui.user_profile.UserProfileScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            FintechAppTheme {
                NavHostNavigation()
//                UserProfileScreen()
            }
        }
    }
}
