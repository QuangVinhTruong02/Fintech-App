package com.example.fintechapp.ui.screens.agent.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fintechapp.common.AppColor
import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.common.AppTextStyle
import com.example.fintechapp.data.response.AgencyResponse
import com.example.fintechapp.ui.base.UIState
import com.example.fintechapp.ui.components.CustomErrorDialog
import com.example.fintechapp.ui.screens.agent.AgentViewModel

@Composable
fun AgentState(viewModel: AgentViewModel){
    val uiAgencyListState: UIState<List<AgencyResponse>> by viewModel.uiAgencyListState.collectAsStateWithLifecycle()

    if (uiAgencyListState is UIState.Loading) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 10.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(32.dp),
                color = AppColor.darkBlue,
                strokeWidth = 3.dp
            )
        }
    }
    if (uiAgencyListState is UIState.Empty || (uiAgencyListState is UIState.Success && (uiAgencyListState as UIState.Success).data!!.isEmpty())) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 10.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                AppLanguage.NO_DATA_AVAILABLE,
                style = AppTextStyle.latoBoldFontStyle.copy(color = AppColor.lightGrey)
            )
        }
    }

    if(uiAgencyListState is UIState.Failure){
        CustomErrorDialog(
            title = AppLanguage.FAILED,
            message = AppLanguage.INCORRECT_PHONE_NUMBER_OR_PASSWORD,
            onConfirm = viewModel::onTurnOffShowDialog,
            onDismiss = viewModel::onTurnOffShowDialog
        )
    }
}