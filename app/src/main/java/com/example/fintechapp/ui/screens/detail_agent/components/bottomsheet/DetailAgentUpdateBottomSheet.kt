package com.example.fintechapp.ui.screens.detail_agent.components.bottomsheet

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.common.AppTextStyle
import com.example.fintechapp.data.response.AgencyResponse
import com.example.fintechapp.ui.screens.detail_agent.DetailAgentViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailAgentUpdateBottomSheet(
    onDismissRequest: (Boolean) -> Unit,
    onAgencyResult: (AgencyResponse?) -> Unit,
    agencyValue: AgencyResponse,
    viewModel: DetailAgentViewModel
) {

    LaunchedEffect(Unit) {
        viewModel.onInitOpenSheet(agencyValue)
    }
    ModalBottomSheet(
        sheetState = rememberModalBottomSheetState(),
        onDismissRequest = {
            onDismissRequest(false)
        }) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f)
                .pointerInput(Unit) {
                    detectTapGestures {
                        viewModel.onSetValueIsExpandedDistrict(false)
                        viewModel.onSetValueIsExpandedWard(false)
                    }
                }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 15.dp, end = 15.dp, bottom = 15.dp)
                    .verticalScroll(rememberScrollState())
                    .imePadding()
            ) {
                Text(
                    AppLanguage.UPDATE_DETAIL.uppercase(),
                    style = AppTextStyle.latoBoldFontStyle,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(10.dp))
                DetailAgentNameInput(viewModel)
                Spacer(modifier = Modifier.height(10.dp))
                DetailAgentPhoneInput(viewModel)
                Spacer(modifier = Modifier.height(10.dp))
                DetailAgentProvinceInput(viewModel)
                Spacer(modifier = Modifier.height(10.dp))
                DetailAgentDistrictInput(viewModel)
                Spacer(modifier = Modifier.height(10.dp))
                DetailAgentWardInput(viewModel)
                Spacer(modifier = Modifier.height(10.dp))
                DetailAgentAddressInput(viewModel)
                Spacer(modifier = Modifier.height(10.dp))
                DetailAgentConfirmButton(
                    viewModel = viewModel,
                    onAgencyResult = onAgencyResult,
                    onDismissRequest = onDismissRequest,
                )
            }
        }
    }
}