package com.example.fintechapp.ui.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fintechapp.common.AppShared
import com.example.fintechapp.di.AppModule
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel(private val appShared: AppShared = AppModule.appShared) : ViewModel() {

    var onNavigateToOnBoarding: (() -> Unit)? = null
    var onNavigateToMain: (() -> Unit)? = null

    init {
        viewModelScope.launch {
            delay(2000) // 2 seconds delay
            if (appShared.getAccessToken().isNotEmpty()) {
                onNavigateToMain?.invoke()
            } else {
                onNavigateToOnBoarding?.invoke()
            }
        }
    }
}
