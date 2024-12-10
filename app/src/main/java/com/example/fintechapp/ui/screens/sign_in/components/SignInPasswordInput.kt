package com.example.fintechapp.ui.sign_in.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fintechapp.common.AppColor
import com.example.fintechapp.common.AppIcon
import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.common.AppTextStyle
import com.example.fintechapp.common.Utils.Validation
import com.example.fintechapp.ui.components.CustomTextInput
import com.example.fintechapp.ui.sign_in.SignInViewModel

@Composable
fun SignInPasswordInput(viewModel: SignInViewModel){
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
        trailingIcon = {
            IconButton(
                onClick = viewModel::onChangeStatePasswordHide
            ) {
                Icon(
                    painter = painterResource(id = if(isPasswordHideState) AppIcon.icVisibility else AppIcon.icVisibilityOff),
                    contentDescription = null,
                    modifier = Modifier.size(25.dp)
                )
            }
        },
        onValidate = {
            Validation().validatePassword(viewModel.passwordTextInput)
        },
        isPasswordVisible = isPasswordHideState,
        onValueChanged = {
            viewModel.passwordTextInput = it
            viewModel.setValueButtonState()
        },
    )
}