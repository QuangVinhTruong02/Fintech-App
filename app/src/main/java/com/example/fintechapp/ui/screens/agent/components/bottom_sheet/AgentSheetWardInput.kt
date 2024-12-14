package com.example.fintechapp.ui.screens.agent.components.bottom_sheet

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.common.AppTextStyle
import com.example.fintechapp.common.Utils.Validation
import com.example.fintechapp.data.response.WardResponse
import com.example.fintechapp.ui.base.UIState
import com.example.fintechapp.ui.components.CustomTextFieldDropDown
import com.example.fintechapp.ui.screens.agent.AgentViewModel

@Composable
fun AgentSheetWardInput(viewModel: AgentViewModel) {
    val isExpandedWard: Boolean by viewModel.isExpandedWard.collectAsStateWithLifecycle()
    val uiWardListState: UIState<List<WardResponse>> by viewModel.uiWardListState.collectAsStateWithLifecycle()

    Text("${AppLanguage.WARD}/${AppLanguage.COMMUNE}*", style = AppTextStyle.latoMediumFontStyle)
    Spacer(modifier = Modifier.height(5.dp))
    CustomTextFieldDropDown(
        isExpanded = isExpandedWard,
        valueText = viewModel.wardInput,
        hintText = AppLanguage.SELECT_WARD_COMMUNE,
        onValueChanged = {
            viewModel.wardInput = it
            viewModel.setValueConfirmButtonState()
        },
        onTapTextField = {},
        setIsExpanded = { viewModel.setIsExpandedWard(it) },
        onSelectedValue = { viewModel.setSelectedWard(it) },
        optionList = if (uiWardListState is UIState.Success) (uiWardListState as UIState.Success).data!!.map { it.name } else emptyList(),
        isLoading = uiWardListState is UIState.Loading,
        onValidation = { Validation().validateEmpty(viewModel.wardInput) }
    )
}