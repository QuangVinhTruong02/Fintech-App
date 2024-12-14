package com.example.fintechapp.ui.screens.detail_agent.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.fintechapp.common.AppColor
import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.ui.base.UIButtonState
import com.example.fintechapp.ui.components.CustomButton
import com.example.fintechapp.ui.screens.detail_agent.DetailAgentViewModel

@Composable
fun DetailAgentUpdateButton(viewModel: DetailAgentViewModel){
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        CustomButton(
            buttonColor = AppColor.darkBlue,
            buttonState = UIButtonState.Enable,
            contentText = AppLanguage.UPDATE,
            onClick = {viewModel.onSetValueOpenSheet(true)}
        )
    }
}