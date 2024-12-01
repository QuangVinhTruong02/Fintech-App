package com.example.fintechapp.ui.user_profile.components

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
import com.example.fintechapp.ui.user_profile.UserProfileViewModel

@Composable
fun UserProfileLastNameInput(viewModel: UserProfileViewModel) {
    Text(
        AppLanguage.LAST_NAME,
        style = AppTextStyle.latoRegularFontStyle.copy(
            color = AppColor.darkBlue,
            fontSize = 14.sp
        )
    )
    Spacer(modifier = Modifier.height(5.dp))
    CustomTextInput(
        valueText = viewModel.lastNameTextInput,
        hintText = AppLanguage.LAST_NAME,
        leadingIcon = AppIcon.icPerson,
        onValidate = { Validation().validateEmpty(viewModel.lastNameTextInput) },
        onValueChanged = {
            viewModel.lastNameTextInput = it
            viewModel.setValueButtonState()
        },
    )
}