package com.example.fintechapp.ui.sign_in

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fintechapp.common.AppShared
import com.example.fintechapp.core.helper.Validation
import com.example.fintechapp.data.remote.ResultApi
import com.example.fintechapp.data.request.LoginRequest
import com.example.fintechapp.data.response.LoginResponse
import com.example.fintechapp.di.AppModule
import com.example.fintechapp.repository.AuthRepositoryImpl
import com.example.fintechapp.ui.base.UIButtonState
import com.example.fintechapp.ui.base.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SignInViewModel(
    private val authRepository: AuthRepositoryImpl = AppModule.authRepository,
    private val appShared : AppShared = AppModule.appShared
) :
    ViewModel() {
    var phoneNumberTextInput by mutableStateOf<String>("")
    var passwordTextInput by mutableStateOf<String>("")

    private val _enableButtonState = MutableStateFlow<Boolean>(false)
    val enableButtonState: StateFlow<Boolean> = _enableButtonState

    private val _isPasswordHideState = MutableStateFlow<Boolean>(true)
    val isPasswordHideState: StateFlow<Boolean> = _isPasswordHideState

    private val _buttonStateFlow = MutableStateFlow<UIButtonState>(UIButtonState.Disable)
    val buttonStateFlow: StateFlow<UIButtonState> = _buttonStateFlow

    private val _uiStateFlow = MutableStateFlow<UIState<LoginResponse>>(UIState.Empty)
    val uiStateFlow : StateFlow<UIState<LoginResponse>> = _uiStateFlow

    fun setValueButtonState() {
        if ((Validation().validatePhoneNumber(phoneNumberTextInput) == null
                    && Validation().validatePassword(passwordTextInput) == null)
        ) {
            _buttonStateFlow.value = UIButtonState.Enable
        } else {
            _buttonStateFlow.value = UIButtonState.Disable
        }
    }

    fun onChangeStatePasswordHide() {
        _isPasswordHideState.value = !_isPasswordHideState.value
    }

    fun onTurnOffShowDialog(){
        _uiStateFlow.value = UIState.Empty
    }

    fun onLogin() {
        viewModelScope.launch {
            val accessToken = appShared.getAccessToken()
            _buttonStateFlow.emit(UIButtonState.Loading)
            val loginRequest: LoginRequest =
                LoginRequest(phone = phoneNumberTextInput, password = passwordTextInput)
            val response = authRepository.signIn(loginRequest)
            if (response is ResultApi.Success) {
                _uiStateFlow.emit(UIState.Success(null))
            } else {
                _uiStateFlow.emit(UIState.Failure(null))
            }
            _buttonStateFlow.emit(UIButtonState.Enable)
        }
    }
}