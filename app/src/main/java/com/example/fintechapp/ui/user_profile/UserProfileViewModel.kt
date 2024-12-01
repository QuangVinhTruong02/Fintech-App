package com.example.fintechapp.ui.user_profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.fintechapp.core.helper.Validation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UserProfileViewModel() : ViewModel() {
    var firstNameTextInput by mutableStateOf<String>("")
    var lastNameTextInput by mutableStateOf<String>("")
    var dateOfBirthTextInput by mutableStateOf<String>("")

    private val _buttonState = MutableStateFlow<Boolean>(false)
    val buttonState: StateFlow<Boolean> = _buttonState

    fun setValueButtonState() {
        _buttonState.value = (Validation().validateEmpty(firstNameTextInput) == null
                && Validation().validateEmpty(lastNameTextInput) == null
                && Validation().validateEmpty(dateOfBirthTextInput) == null)
    }
}