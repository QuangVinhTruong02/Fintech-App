package com.example.fintechapp.ui.screens.detail_agent.components.bottomsheet

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
import com.example.fintechapp.ui.screens.detail_agent.DetailAgentViewModel

@Composable
fun DetailAgentWardInput(viewModel: DetailAgentViewModel) {

    val isExpandedWard: Boolean by viewModel.isExpandedWard.collectAsStateWithLifecycle()
    val uiWardsState: UIState<List<WardResponse>> by viewModel.uiWardListState.collectAsStateWithLifecycle()

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
        onTapTextField = {viewModel.fetchWards()},
        setIsExpanded = { viewModel.onSetValueIsExpandedWard(it) },
        onSelectedValue = {
            viewModel.setSelectedWard(it)
        },
        optionList = if (uiWardsState is UIState.Success) (uiWardsState as UIState.Success).data!!.map { it.name } else emptyList(),
        isLoading = uiWardsState is UIState.Loading,
        onValidation = { Validation().validateEmpty(viewModel.wardInput) }
    )
}