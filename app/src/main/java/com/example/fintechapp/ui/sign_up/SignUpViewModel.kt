package com.example.fintechapp.ui.sign_up

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fintechapp.core.helper.Validation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SignUpViewModel() : ViewModel() {
    var emailTextInput by mutableStateOf<String>("")
    var passwordTextInput by mutableStateOf<String>("")
    var confirmPasswordTextInput by mutableStateOf<String>("")

    private val _isPasswordHideState = MutableStateFlow<Boolean>(true)
    val isPasswordHideState: StateFlow<Boolean> = _isPasswordHideState

    private val _isConfirmPasswordHideState = MutableStateFlow<Boolean>(true)
    val isConfirmPasswordHideState: StateFlow<Boolean> = _isConfirmPasswordHideState

    private val _buttonState = MutableStateFlow<Boolean>(false)
    val buttonState: StateFlow<Boolean> = _buttonState


    fun setValueButtonState() {
        if (Validation().validateEmail(emailTextInput) == null
            && Validation().validatePassword(passwordTextInput) == null
            && Validation().validateConfirmPassword(
                password = passwordTextInput,
                confirmPassword = confirmPasswordTextInput
            ) == null
        ) {
            _buttonState.value = true
        } else {
            _buttonState.value = false
        }
    }

    fun onChangeStatePasswordHide() {
        _isPasswordHideState.value = !_isPasswordHideState.value
    }

    fun onChangeStateConfirmPasswordHide() {
        _isConfirmPasswordHideState.value = !_isConfirmPasswordHideState.value
    }
}