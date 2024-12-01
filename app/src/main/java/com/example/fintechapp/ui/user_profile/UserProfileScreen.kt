package com.example.fintechapp.ui.user_profile

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
import com.example.fintechapp.ui.fun_compose.AppTopBar
import com.example.fintechapp.ui.sign_up.components.SignUpAlreadyHaveAccount
import com.example.fintechapp.ui.sign_up.components.SignUpAnotherIcon
import com.example.fintechapp.ui.sign_up.components.SignUpConfirmPasswordInput
import com.example.fintechapp.ui.sign_up.components.SignUpContinueButton
import com.example.fintechapp.ui.sign_up.components.SignUpEmailInput
import com.example.fintechapp.ui.sign_up.components.SignUpOrText
import com.example.fintechapp.ui.sign_up.components.SignUpPasswordInput
import com.example.fintechapp.ui.user_profile.components.UserProfileCreateButton
import com.example.fintechapp.ui.user_profile.components.UserProfileDateOfBirthInput
import com.example.fintechapp.ui.user_profile.components.UserProfileFirstNameInput
import com.example.fintechapp.ui.user_profile.components.UserProfileLastNameInput

@Composable
fun UserProfileScreen(
    navController: NavController,
    viewModel: UserProfileViewModel = viewModel()
) {
    val scrollState = rememberScrollState()
    Scaffold() { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(start = 23.dp, end = 23.dp, top = 55.dp)
                .verticalScroll(scrollState)
                .imePadding()
        ) {
            Text(
                AppLanguage.PERSONAL_DETAILS, style = AppTextStyle.latoBoldFontStyle.copy(
                    fontSize = 24.sp, color = AppColor.darkBlue
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                AppLanguage.ENTER_YOUR_DETAILS,
                style = AppTextStyle.latoRegularFontStyle.copy(
                    color = AppColor.darkBlue
                )
            )
            Spacer(modifier = Modifier.height(40.dp))
            UserProfileFirstNameInput(viewModel)
            Spacer(modifier = Modifier.height(15.dp))
            UserProfileLastNameInput(viewModel)
            Spacer(modifier = Modifier.height(15.dp))
            UserProfileDateOfBirthInput(viewModel)
            Spacer(modifier = Modifier.height(15.dp))
            UserProfileCreateButton(viewModel)
        }
    }
}