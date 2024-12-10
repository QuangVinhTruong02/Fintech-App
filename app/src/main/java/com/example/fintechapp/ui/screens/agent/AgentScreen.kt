package com.example.fintechapp.ui.screens.agent

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.ui.components.CustomShowRemoveDialog
import com.example.fintechapp.ui.screens.agent.components.AgentView

@Composable
fun AgentScreen(
    onNavigateToCreateAgent: (String) -> Unit,
    viewModel: AgentViewModel = viewModel(),
    retrieveNewAgencyBoolean: Boolean = false
) {

    val isQuestionRemoveDialog: Boolean by viewModel.showDialog.collectAsStateWithLifecycle()

    if (retrieveNewAgencyBoolean) {
        viewModel.fetchAgencies("")
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

    AgentView(viewModel = viewModel, onNavigateToCreateAgent = onNavigateToCreateAgent)
}

