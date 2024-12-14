package com.example.fintechapp.ui.screens.agent.components.bottom_sheet

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
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.example.fintechapp.data.response.AgencyResponse
import com.example.fintechapp.ui.screens.agent.AgentViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgentBottomSheet(
    onDismissRequest: (Boolean) -> Unit,
    onHasData: (Boolean) -> Unit,
    agencyValue: AgencyResponse?,
    viewModel: AgentViewModel,
) {
    LaunchedEffect(Unit) {
        viewModel.onInitOpenSheet(agencyValue)
    }

    val sheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()

    ModalBottomSheet(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.8f),
        sheetState = sheetState,
        onDismissRequest = {
            coroutineScope.launch { sheetState.hide() }
            onDismissRequest(false)
            viewModel.onClearInput()
        }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures {
                        viewModel.setIsExpandedProvince(false)
                        viewModel.setIsExpandedWard(false)
                        viewModel.setIsExpandedDistrict(false)
                    }
                }
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 10.dp, start = 10.dp, end = 10.dp)
                    .fillMaxSize()
                    .imePadding()
                    .verticalScroll(
                        rememberScrollState()
                    )
            ) {
                AgentSheetNameInput(viewModel = viewModel)
                Spacer(modifier = Modifier.height(10.dp))
                AgentSheetPhoneInput(viewModel = viewModel)
                Spacer(modifier = Modifier.height(10.dp))
                AgentSheetCityInput(viewModel)
                Spacer(modifier = Modifier.height(10.dp))
                AgentSheetDistrictInput(viewModel)
                Spacer(modifier = Modifier.height(10.dp))
                AgentSheetWardInput(viewModel)
                Spacer(modifier = Modifier.height(10.dp))
                AgentSheetAddressInput(viewModel)
                Spacer(modifier = Modifier.height(10.dp))
                AgentSheetButton(
                    viewModel = viewModel,
                    onDismissRequest = onDismissRequest,
                    onHasData = onHasData
                )
            }
        }
    }
}