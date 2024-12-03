package com.example.fintechapp.ui.sign_up.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fintechapp.common.AppColor
import com.example.fintechapp.common.AppIcon
import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.common.AppTextStyle
import com.example.fintechapp.core.helper.Validation
import com.example.fintechapp.ui.components.CustomTextInput
import com.example.fintechapp.ui.sign_up.SignUpViewModel

@Composable
fun SignUpPasswordInput(viewModel: SignUpViewModel) {
    val isPasswordHideState :Boolean by viewModel.isPasswordHideState.collectAsStateWithLifecycle()
    Text(
        AppLanguage.PASSWORD,
        style = AppTextStyle.latoRegularFontStyle.copy(
            color = AppColor.darkBlue,
            fontSize = 14.sp
        )
    )
    Spacer(modifier = Modifier.height(5.dp))
    CustomTextInput(
        valueText = viewModel.passwordTextInput,
        hintText = AppLanguage.ENTER_PASSWORD,
        leadingIcon = AppIcon.icLock,
        trailingIcon = if(isPasswordHideState) AppIcon.icVisibility else AppIcon.icVisibilityOff,
        onValidate = {
            Validation().validatePassword(viewModel.passwordTextInput)
        },
        isPasswordVisible = isPasswordHideState,
        onValueChanged = {
            viewModel.passwordTextInput = it
            viewModel.setValueButtonState()
        },
        onPressedTrailingIcon = {
            viewModel.onChangeStatePasswordHide()
        }
    )
}