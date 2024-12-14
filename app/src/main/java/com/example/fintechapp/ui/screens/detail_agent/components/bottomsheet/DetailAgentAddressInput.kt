package com.example.fintechapp.ui.screens.detail_agent.components.bottomsheet

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.common.AppTextStyle
import com.example.fintechapp.common.Utils.Validation
import com.example.fintechapp.ui.components.CustomTextInput
import com.example.fintechapp.ui.screens.detail_agent.DetailAgentViewModel

@Composable
fun DetailAgentAddressInput(viewModel: DetailAgentViewModel) {
    Text("${AppLanguage.ADDRESS}*", style = AppTextStyle.latoMediumFontStyle)
    Spacer(modifier = Modifier.height(5.dp))
    CustomTextInput(
        hintText = AppLanguage.ENTER_ADDRESS,
        valueText = viewModel.addressInput,
        onValidate = { Validation().validateEmpty(it) },
        onValueChanged = {
            viewModel.addressInput = it
            viewModel.setValueConfirmButtonState()
        }
    )
}