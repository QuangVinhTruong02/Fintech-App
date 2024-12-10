package com.example.fintechapp.ui.screens.create_agent.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fintechapp.common.AppColor
import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.ui.base.UIButtonState
import com.example.fintechapp.ui.components.CustomButton
import com.example.fintechapp.ui.screens.create_agent.CreateAgentViewModel

@Composable
fun CreateAgentButton(
//    onNavigateBack: (Boolean) -> Unit,
    onPreviousBackStackAgency: (Boolean) -> Unit,
    viewModel: CreateAgentViewModel
) {
    val uiConfirmButtonState: UIButtonState by viewModel.uiButtonConfirmState.collectAsStateWithLifecycle()
    Row(
        modifier = Modifier.fillMaxWidth(),
    ) {
        CustomButton(
            modifier = Modifier.weight(1f),
            contentText = AppLanguage.CONFIRM,
            onClick = {viewModel.onConfirmCreateAgency(onPreviousBackStackAgency)},
            buttonState = uiConfirmButtonState,
            buttonColor = AppColor.darkBlue,
        )
        Spacer(modifier = Modifier.width(15.dp))
        CustomButton(
            modifier = Modifier.weight(1f),
            contentText = AppLanguage.CANCEL,
            onClick = {onPreviousBackStackAgency(false)},
            buttonState = UIButtonState.Enable,
            buttonColor = AppColor.grey,
        )
    }
}