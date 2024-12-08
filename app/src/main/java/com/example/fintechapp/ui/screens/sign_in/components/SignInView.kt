package com.example.fintechapp.ui.screens.sign_in.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fintechapp.common.AppColor
import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.common.AppTextStyle
import com.example.fintechapp.ui.components.AppTopBar
import com.example.fintechapp.ui.sign_in.SignInViewModel
import com.example.fintechapp.ui.sign_in.components.SignInLoginButton
import com.example.fintechapp.ui.sign_in.components.SignInPasswordInput
import com.example.fintechapp.ui.sign_in.components.SignInPhoneInput

@Composable
fun SignInView(
    viewModel: SignInViewModel,
    onNavigateBack: () -> Unit
) {
    val scrollState = rememberScrollState()
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(23.dp)
                .verticalScroll(scrollState)
                .imePadding()
        ) {
            Text(
                AppLanguage.WELCOME_TO_DTGENTA, style = AppTextStyle.latoBoldFontStyle.copy(
                    fontSize = 24.sp, color = AppColor.darkBlue
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                AppLanguage.PLEASE_SIGN_IN_TO_YOUR_ACCOUNT,
                style = AppTextStyle.latoRegularFontStyle.copy(
                    color = AppColor.darkBlue
                )
            )
            Spacer(modifier = Modifier.height(40.dp))
            SignInPhoneInput(viewModel)
            Spacer(modifier = Modifier.height(15.dp))
            SignInPasswordInput(viewModel)
            Spacer(modifier = Modifier.height(15.dp))
            Spacer(modifier = Modifier.height(15.dp))
            SignInLoginButton(viewModel, onPressedLogin = {
                viewModel.onLogin()
            })

        }
    }
}