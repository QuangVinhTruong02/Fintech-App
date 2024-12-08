package com.example.fintechapp.ui.sign_in

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.data.request.LoginRequest
import com.example.fintechapp.data.response.LoginResponse
import com.example.fintechapp.ui.base.DtgAppState
import com.example.fintechapp.ui.base.UIState
import com.example.fintechapp.ui.components.CustomErrorDialog
import com.example.fintechapp.ui.screens.sign_in.components.SignInView

@Composable
fun SignInScreen(
    appState: DtgAppState,
    onNavigateBack: () -> Unit,
    onNavigateToMain: () -> Unit,
    viewModel: SignInViewModel = viewModel()
) {
    val uiStateFlow: UIState<LoginResponse> by viewModel.uiStateFlow.collectAsStateWithLifecycle()

    when (uiStateFlow) {
        is UIState.Success -> {
            onNavigateToMain.invoke()
            Toast.makeText(LocalContext.current, "Đăng nhập thành công", Toast.LENGTH_SHORT).show()
        }

        is UIState.Failure -> {
            CustomErrorDialog(
                title = AppLanguage.FAILED,
                message = AppLanguage.INCORRECT_PHONE_NUMBER_OR_PASSWORD,
                onConfirm = viewModel::onTurnOffShowDialog,
                onDismiss = viewModel::onTurnOffShowDialog
            )
        }

        is UIState.Loading -> {}
        is UIState.Empty -> {}
    }

    SignInView(viewModel = viewModel, onNavigateBack = onNavigateBack)
}

