package com.example.fintechapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.FragmentActivity
import com.example.fintechapp.di.AppModule
import com.example.fintechapp.ui.base.DtgComposeApp
import com.example.fintechapp.ui.screens.detail_agent.DetailAgentScreen
import com.example.fintechapp.ui.screens.home.HomeScreen

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current
            AppModule.initContext(context)
            DtgComposeApp()
//            DetailAgentScreen()
        }
    }
}
