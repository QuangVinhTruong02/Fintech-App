package com.example.fintechapp.ui.sign_up.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fintechapp.common.AppColor
import com.example.fintechapp.common.AppIcon
import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.common.AppTextStyle
import com.example.fintechapp.core.helper.Validation
import com.example.fintechapp.ui.components.CustomTextInput
import com.example.fintechapp.ui.sign_up.SignUpViewModel

@Composable
fun SignUpConfirmPasswordInput(viewModel: SignUpViewModel) {
    val isConfirmPasswordHideState: Boolean by viewModel.isConfirmPasswordHideState.collectAsStateWithLifecycle()
    Text(
        AppLanguage.CONFIRM_PASSWORD,
        style = AppTextStyle.latoRegularFontStyle.copy(
            color = AppColor.darkBlue,
            fontSize = 14.sp
        )
    )
    Spacer(modifier = Modifier.height(5.dp))
    CustomTextInput(
        valueText = viewModel.confirmPasswordTextInput,
        hintText = AppLanguage.ENTER_CONFIRM_PASSWORD,
        leadingIcon = AppIcon.icLock,
        trailingIcon = if (isConfirmPasswordHideState) AppIcon.icVisibility else AppIcon.icVisibilityOff,
        onValidate = {
            Validation().validateConfirmPassword(
                password = viewModel.passwordTextInput,
                confirmPassword = viewModel.confirmPasswordTextInput
            )
        },
        isPasswordVisible = isConfirmPasswordHideState,
        onValueChanged = {
            viewModel.confirmPasswordTextInput = it
            viewModel.setValueButtonState()
        },
        onPressedTrailingIcon = {
            viewModel.onChangeStateConfirmPasswordHide()
        }
    )
}