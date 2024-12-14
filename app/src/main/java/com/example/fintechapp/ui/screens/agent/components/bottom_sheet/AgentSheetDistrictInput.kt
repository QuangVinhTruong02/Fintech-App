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
import com.example.fintechapp.data.response.DistrictResponse
import com.example.fintechapp.ui.base.UIState
import com.example.fintechapp.ui.components.CustomTextFieldDropDown
import com.example.fintechapp.ui.screens.agent.AgentViewModel

@Composable
fun AgentSheetDistrictInput(viewModel: AgentViewModel) {
    val isExpandedDistrict: Boolean by viewModel.isExpandedDistrict.collectAsStateWithLifecycle()
    val uiDistrictListState : UIState<List<DistrictResponse>> by viewModel.uiDistrictListState.collectAsStateWithLifecycle()

    Text("${AppLanguage.DISTRICT}*", style = AppTextStyle.latoMediumFontStyle)
    Spacer(modifier = Modifier.height(5.dp))
    CustomTextFieldDropDown(
        isExpanded = isExpandedDistrict,
        valueText = viewModel.districtInput,
        hintText = AppLanguage.SELECT_DISTRICT,
        onValueChanged = {
            viewModel.districtInput = it
            viewModel.setValueConfirmButtonState()
        },
        setIsExpanded = { viewModel.setIsExpandedDistrict(it) },
        onSelectedValue = { viewModel.setSelectedDistrict(it) },
        optionList = if(uiDistrictListState is UIState.Success) (uiDistrictListState as UIState.Success).data!!.map { it.name } else emptyList(),
        isLoading = uiDistrictListState is UIState.Loading,
        onTapTextField = {},
        onValidation = { Validation().validateEmpty(it) }
    )
}