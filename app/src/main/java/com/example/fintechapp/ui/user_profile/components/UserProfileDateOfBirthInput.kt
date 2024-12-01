package com.example.fintechapp.ui.user_profile.components

import android.util.Log
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fintechapp.common.AppColor
import com.example.fintechapp.common.AppIcon
import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.common.AppTextStyle
import com.example.fintechapp.core.helper.Validation
import com.example.fintechapp.ui.fun_compose.CustomDatePicker
import com.example.fintechapp.ui.fun_compose.CustomTextInput
import com.example.fintechapp.ui.user_profile.UserProfileViewModel

@Composable
fun UserProfileDateOfBirthInput(viewModel: UserProfileViewModel) {
    val showDatePicker = remember { mutableStateOf(false) }

    if (showDatePicker.value) {
        CustomDatePicker(
            onSelectedDateValue = {
                viewModel.dateOfBirthTextInput = it
                viewModel.setValueButtonState()
                showDatePicker.value = false
            },
            initialDate = viewModel.dateOfBirthTextInput.ifEmpty { null }
        )

    }
    Text(
        AppLanguage.DATE_OF_BIRTH,
        style = AppTextStyle.latoRegularFontStyle.copy(
            color = AppColor.darkBlue,
            fontSize = 14.sp
        )
    )
    Spacer(modifier = Modifier.height(5.dp))
    CustomTextInput(
        valueText = viewModel.dateOfBirthTextInput,
        hintText = AppLanguage.DD_MM_YY,
        leadingIcon = AppIcon.icCalendar,
        onValidate = { Validation().validateEmpty(viewModel.dateOfBirthTextInput) },
        readOnly = true,
        onTapTextField = { showDatePicker.value = true },
        onValueChanged = {},
    )
}