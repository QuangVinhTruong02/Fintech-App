package com.example.fintechapp.ui.splash

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel() : ViewModel() {

    var onNavigateToOnBoarding: (() -> Unit)? = null

    init {
        viewModelScope.launch {
            delay(2000) // 2 seconds delay
            onNavigateToOnBoarding?.invoke() // Trigger navigation
        }
    }
}