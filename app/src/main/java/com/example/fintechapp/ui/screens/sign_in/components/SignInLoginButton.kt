package com.example.fintechapp.ui.sign_in.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fintechapp.common.AppColor
import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.ui.base.UIButtonState
import com.example.fintechapp.ui.components.CustomButton
import com.example.fintechapp.ui.sign_in.SignInViewModel

@Composable
fun SignInLoginButton(
    viewModel: SignInViewModel,
    onPressedLogin: () -> Unit
) {
    val buttonStateFlow: UIButtonState by viewModel.buttonStateFlow.collectAsStateWithLifecycle()
    CustomButton(
        buttonColor = AppColor.darkBlue,
        onClick = onPressedLogin,
        contentText = AppLanguage.LOGIN,
        modifier = Modifier.fillMaxWidth(),
        buttonState = buttonStateFlow,
    )
}