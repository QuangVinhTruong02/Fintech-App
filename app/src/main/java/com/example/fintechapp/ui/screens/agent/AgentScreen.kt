package com.example.fintechapp.ui.screens.agent

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.ui.components.CustomShowRemoveDialog
import com.example.fintechapp.ui.screens.agent.components.AgentView

@Composable
fun AgentScreen(viewModel: AgentViewModel = viewModel()) {

    val isQuestionRemoveDialog: Boolean by viewModel.showDialog.collectAsStateWithLifecycle()
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

    AgentView(viewModel)
}

