package com.example.fintechapp.ui.screens.agent.components.bottom_sheet

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import com.example.fintechapp.ui.screens.agent.AgentViewModel

@Composable
fun AgentSheetButton(
    viewModel: AgentViewModel,
    onDismissRequest: (Boolean) -> Unit,
    onHasData: (Boolean) -> Unit
) {
    val uiConfirmButtonState: UIButtonState by viewModel.uiButtonConfirmState.collectAsStateWithLifecycle()
    Row(
        modifier = Modifier.fillMaxWidth(),
    ) {
        CustomButton(
            modifier = Modifier.weight(1f),
            contentText = AppLanguage.CONFIRM,
            onClick = {
                viewModel.onConfirmCreateAgency(
                    onHasData = onHasData,
                    onDismissRequest = onDismissRequest
                )
            },
            buttonState = uiConfirmButtonState,
            buttonColor = AppColor.darkBlue,
        )
        Spacer(modifier = Modifier.width(15.dp))
        CustomButton(
            modifier = Modifier.weight(1f),
            contentText = AppLanguage.CANCEL,
            onClick = {
                viewModel.onClearInput()
                onHasData(false)
                onDismissRequest(false)
            },
            buttonState = UIButtonState.Enable,
            buttonColor = AppColor.grey,
        )
    }
}