package com.example.fintechapp.ui.screens.agent

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fintechapp.common.AppConst
import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.data.response.AgencyResponse
import com.example.fintechapp.ui.base.DtgAppState
import com.example.fintechapp.ui.components.CustomShowRemoveDialog
import com.example.fintechapp.ui.screens.agent.components.AgentView
import com.example.fintechapp.ui.screens.agent.components.bottom_sheet.AgentBottomSheet

@Composable
fun AgentScreen(
    appState: DtgAppState,
    onNavigateToCreateAgent: (String) -> Unit,
    onNavigateDetailAgent: (String) -> Unit,
    viewModel: AgentViewModel = viewModel(),
) {

    val isQuestionRemoveDialog: Boolean by viewModel.showDialog.collectAsStateWithLifecycle()
    val isOpenSheet: Boolean by viewModel.isOpenSheet.collectAsStateWithLifecycle()
    val selectedAgency: AgencyResponse? by viewModel.selectedAgency.collectAsStateWithLifecycle()
    val updateData =
        appState.appNavigation.navController.currentBackStackEntry?.savedStateHandle?.getStateFlow<Boolean?>(
            AppConst.AGENCY_RETURN_KEY,
            null
        )?.collectAsState()

    LaunchedEffect(updateData) {
        updateData?.value?.let {
            viewModel.fetchAgencies("")
            appState.appNavigation.navController.currentBackStackEntry?.savedStateHandle?.remove<Boolean?>(
                AppConst.AGENCY_RETURN_KEY
            )
        }
    }

    if (isQuestionRemoveDialog) {
        CustomShowRemoveDialog(
            title = AppLanguage.DELETE_AGENCY,
            message = AppLanguage.ARE_YOU_SURE_YOU_WANT_TO_DELETE_THIS_AGENCY,
            onDismiss = { viewModel.setValueShowDialog(false) },
            onConfirm = {
                viewModel.setValueShowDialog(false)
                viewModel.onDeleteAgencyById()
            },
        )
    }

    if(isOpenSheet){
        AgentBottomSheet(
            onDismissRequest = {viewModel.onSetValueOpenSheet(it)},
            onHasData = { hasNewData ->
                if(hasNewData){
                    viewModel.fetchAgencies("")
                }
            },
            agencyValue = selectedAgency,
            viewModel = viewModel
        )
    }

    AgentView(
        viewModel = viewModel,
        onNavigateToCreateAgent = onNavigateToCreateAgent,
        onNavigateDetailAgent = onNavigateDetailAgent
    )
}

