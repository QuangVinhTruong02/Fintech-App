package com.example.fintechapp.ui.sign_up.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fintechapp.common.AppColor
import com.example.fintechapp.common.AppIcon
import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.common.AppTextStyle
import com.example.fintechapp.core.helper.Validation
import com.example.fintechapp.ui.fun_compose.CustomTextInput
import com.example.fintechapp.ui.sign_up.SignUpViewModel

@Composable
fun SignUpEmailInput(viewModel: SignUpViewModel) {
    Text(
        AppLanguage.EMAIL,
        style = AppTextStyle.latoRegularFontStyle.copy(
            color = AppColor.darkBlue,
            fontSize = 14.sp
        )
    )
    Spacer(modifier = Modifier.height(5.dp))
    CustomTextInput(
        valueText = viewModel.emailTextInput,
        hintText = AppLanguage.ENTER_EMAIL,
        leadingIcon = AppIcon.icLetter,
        onValidate = {
            Validation().validateEmail(viewModel.emailTextInput)
        },
        onValueChanged = {
            viewModel.emailTextInput = it
            viewModel.setValueButtonState()
        },
    )
}