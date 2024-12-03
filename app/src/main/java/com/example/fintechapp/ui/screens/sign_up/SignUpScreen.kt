package com.example.fintechapp.ui.sign_up

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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.fintechapp.common.AppColor
import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.common.AppTextStyle
import com.example.fintechapp.ui.base.DtgAppState
import com.example.fintechapp.ui.components.AppTopBar
import com.example.fintechapp.ui.sign_up.components.SignUpAlreadyHaveAccount
import com.example.fintechapp.ui.sign_up.components.SignUpAnotherIcon
import com.example.fintechapp.ui.sign_up.components.SignUpConfirmPasswordInput
import com.example.fintechapp.ui.sign_up.components.SignUpContinueButton
import com.example.fintechapp.ui.sign_up.components.SignUpEmailInput
import com.example.fintechapp.ui.sign_up.components.SignUpOrText
import com.example.fintechapp.ui.sign_up.components.SignUpPasswordInput

@Composable
fun SignUpScreen(
    appState: DtgAppState,
    onNavigateToUserProfile: () -> Unit,
    viewModel: SignUpViewModel = viewModel()
) {
    val scrollState = rememberScrollState()
    Scaffold(topBar = {
        AppTopBar(onBackNavigation = { appState.appNavigation.navigateBack() }
        )
    }) { paddingValues ->
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
                AppLanguage.JOIN_US_CREATE_ACCOUNT_YOUR_TODAY,
                style = AppTextStyle.latoRegularFontStyle.copy(
                    color = AppColor.darkBlue
                )
            )
            Spacer(modifier = Modifier.height(40.dp))
            SignUpEmailInput(viewModel)
            Spacer(modifier = Modifier.height(15.dp))
            SignUpPasswordInput(viewModel)
            Spacer(modifier = Modifier.height(15.dp))
            SignUpConfirmPasswordInput(viewModel)
            Spacer(modifier = Modifier.height(15.dp))
            SignUpContinueButton(viewModel, onPressedContinue = onNavigateToUserProfile)
            Spacer(modifier = Modifier.height(20.dp))
            SignUpOrText()
            Spacer(modifier = Modifier.height(20.dp))
            SignUpAnotherIcon()
            Spacer(modifier = Modifier.weight(1f))
            SignUpAlreadyHaveAccount(
                onClickLogin = {}
            )
        }
    }
}
