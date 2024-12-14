package com.example.fintechapp.ui.screens.detail_agent

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fintechapp.common.AppColor
import com.example.fintechapp.common.AppConst
import com.example.fintechapp.common.AppIcon
import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.common.AppTextStyle
import com.example.fintechapp.data.response.AgencyResponse
import com.example.fintechapp.ui.base.DtgAppState
import com.example.fintechapp.ui.base.UIState
import com.example.fintechapp.ui.components.AppTopBar
import com.example.fintechapp.ui.screens.detail_agent.components.DetailAgentView
import com.example.fintechapp.ui.screens.detail_agent.components.bottomsheet.DetailAgentUpdateBottomSheet

@Composable
fun DetailAgentScreen(
    onNavigateBack: () -> Unit,
    appState: DtgAppState,
    agencyCode: String,
    viewModel: DetailAgentViewModel = viewModel()
) {
    val uiAgencyState: UIState<AgencyResponse> by viewModel.uiAgencyState.collectAsStateWithLifecycle()
    val isOpenSheet: Boolean by viewModel.isOpenSheet.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.onInitOpenSheet(agencyCodeValue = agencyCode)
    }

    Scaffold(
        topBar = {
            AppTopBar(
                leadingIcon = AppIcon.icNavigateBack,
                titleText = AppLanguage.DETAIL_AGENCY,
                onPressedLeading = onNavigateBack
            )
        }
    ) { paddingValues ->
        when (uiAgencyState) {
            is UIState.Success -> {
                if (isOpenSheet) {
                    DetailAgentUpdateBottomSheet(
                        agencyValue = (uiAgencyState as UIState.Success<AgencyResponse>).data!!,
                        onDismissRequest = { viewModel.onSetValueOpenSheet(it) },
                        onAgencyResult = { agencyResult ->
                            if (agencyResult != null) {
                                viewModel.onRetrieveAgencyResult(agencyResult)
                                appState.appNavigation.navController.previousBackStackEntry?.savedStateHandle?.set(
                                    AppConst.AGENCY_RETURN_KEY,
                                    true
                                )
                            }
                        },
                        viewModel = viewModel
                    )
                }

                DetailAgentView(
                    paddingValues = paddingValues, viewModel = viewModel,
                    agency = (uiAgencyState as UIState.Success<AgencyResponse>).data!!
                )
            }

            is UIState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is UIState.Failure -> {
                Box(
                    modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    Text(
                        (uiAgencyState as UIState.Failure<AgencyResponse>).throwable?.message.toString(),
                        style = AppTextStyle.latoMediumFontStyle.copy(color = AppColor.red)
                    )
                }
            }

            is UIState.Empty -> {}
        }
    }
}