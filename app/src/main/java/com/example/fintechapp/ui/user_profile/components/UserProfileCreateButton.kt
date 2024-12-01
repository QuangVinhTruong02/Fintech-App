package com.example.fintechapp.ui.user_profile.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fintechapp.common.AppColor
import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.ui.fun_compose.CustomButton
import com.example.fintechapp.ui.user_profile.UserProfileViewModel

@Composable
fun UserProfileCreateButton(viewModel: UserProfileViewModel) {
    val buttonState: Boolean by viewModel.buttonState.collectAsStateWithLifecycle()
    CustomButton(
        buttonColor = if (buttonState) AppColor.darkBlue else AppColor.darkBlue.copy(alpha = 0.2f),
        onClick = {},
        contentText = AppLanguage.CREATE_ACCOUNT,
        modifier = Modifier.fillMaxWidth(),
        enable = buttonState
    )
}