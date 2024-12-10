package com.example.fintechapp.ui.sign_in.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fintechapp.common.AppColor
import com.example.fintechapp.common.AppIcon
import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.common.AppTextStyle
import com.example.fintechapp.common.Utils.Validation
import com.example.fintechapp.ui.components.CustomTextInput
import com.example.fintechapp.ui.sign_in.SignInViewModel

@Composable
fun SignInPhoneInput(viewModel: SignInViewModel){
    Text(
        AppLanguage.PHONE_NUMBER,
        style = AppTextStyle.latoRegularFontStyle.copy(
            color = AppColor.darkBlue,
            fontSize = 14.sp
        )
    )
    Spacer(modifier = Modifier.height(5.dp))
    CustomTextInput(
        keyBoardType = KeyboardType.Number,
        valueText = viewModel.phoneNumberTextInput,
        hintText = AppLanguage.ENTER_PHONE_NUMBER,
        leadingIcon = AppIcon.icPerson,
        onValidate = {
            Validation().validatePhoneNumber(viewModel.phoneNumberTextInput)
        },
        onValueChanged = {
            viewModel.phoneNumberTextInput = it
            viewModel.setValueButtonState()
        },
    )
}