package com.example.fintechapp.ui.base

sealed interface UIButtonState {
    data object Enable : UIButtonState
    data object Success : UIButtonState
    data object Disable : UIButtonState
    data object Loading : UIButtonState
}