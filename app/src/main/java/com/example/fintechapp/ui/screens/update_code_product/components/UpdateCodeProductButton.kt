package com.example.fintechapp.ui.screens.update_code_product.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fintechapp.common.AppColor
import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.ui.base.DtgAppState
import com.example.fintechapp.ui.base.UIButtonState
import com.example.fintechapp.ui.components.CustomButton
import com.example.fintechapp.ui.screens.update_code_product.UpdateCodeProductViewModel

@Composable
fun UpdateCodeProductButton(
    viewModel: UpdateCodeProductViewModel,
    onNavigateBack: () -> Unit,
    appState: DtgAppState,
) {
    val uiButtonState: UIButtonState by viewModel.uiButtonState.collectAsStateWithLifecycle()

    CustomButton(
        modifier = Modifier.fillMaxWidth(),
        buttonColor = AppColor.darkBlue,
        buttonState = uiButtonState,
        contentText = AppLanguage.UPDATE_PRODUCT,
        onClick = { viewModel.onUpdateProduct(
            appState = appState,
            onNavigateBack = onNavigateBack,
        ) }
    )
}