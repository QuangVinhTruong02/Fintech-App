package com.example.fintechapp.ui.screens.create_agent

import android.util.Log
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fintechapp.common.AppColor
import com.example.fintechapp.common.AppIcon
import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.data.response.AgencyResponse
import com.example.fintechapp.ui.components.AppTopBar
import com.example.fintechapp.ui.screens.create_agent.components.CreateAgentAddressInput
import com.example.fintechapp.ui.screens.create_agent.components.CreateAgentButton
import com.example.fintechapp.ui.screens.create_agent.components.CreateAgentCityInput
import com.example.fintechapp.ui.screens.create_agent.components.CreateAgentDistrictInput
import com.example.fintechapp.ui.screens.create_agent.components.CreateAgentNameInput
import com.example.fintechapp.ui.screens.create_agent.components.CreateAgentPhoneInput
import com.example.fintechapp.ui.screens.create_agent.components.CreateAgentWardInput

@Composable
fun CreateAgentScreen(
    onNavigateToBack: () -> Unit,
    onPreviousBackStackAgency: (Boolean) -> Unit,
    agencyId: String,
    viewModel: CreateAgentViewModel = viewModel()
) {

    LaunchedEffect(Unit) {
        viewModel.setAgencyId(agencyId)
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp),
        topBar = {
            AppTopBar(
                onPressedLeading = onNavigateToBack,
                leadingIcon = AppIcon.icNavigateBack,
                backgroundColor = AppColor.white,
                titleText = if (agencyId.isEmpty()) AppLanguage.CREATE_AGENCY else AppLanguage.UPDATE_AGENCY,
            )
        }
    ) { paddingValues ->
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
                .padding(paddingValues)
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
                CreateAgentNameInput(viewModel = viewModel)
                Spacer(modifier = Modifier.height(10.dp))
                CreateAgentPhoneInput(viewModel = viewModel)
                Spacer(modifier = Modifier.height(10.dp))
                CreateAgentCityInput(viewModel)
                Spacer(modifier = Modifier.height(10.dp))
                CreateAgentDistrictInput(viewModel)
                Spacer(modifier = Modifier.height(10.dp))
                CreateAgentWardInput(viewModel)
                Spacer(modifier = Modifier.height(10.dp))
                CreateAgentAddressInput(viewModel)
                Spacer(modifier = Modifier.height(10.dp))
                CreateAgentButton(
                    onPreviousBackStackAgency = onPreviousBackStackAgency,
                    viewModel = viewModel
                )
            }
        }
    }
}