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
import com.example.fintechapp.data.response.DistrictResponse
import com.example.fintechapp.ui.base.UIState
import com.example.fintechapp.ui.components.CustomTextFieldDropDown
import com.example.fintechapp.ui.screens.detail_agent.DetailAgentViewModel

@Composable
fun DetailAgentDistrictInput(viewModel: DetailAgentViewModel) {
    val isExpandedDistrict: Boolean by viewModel.isExpandedDistrict.collectAsStateWithLifecycle()
    val uiDistrictState: UIState<List<DistrictResponse>> by viewModel.uiDistrictListState.collectAsStateWithLifecycle()

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
        setIsExpanded = {
            viewModel.onSetValueIsExpandedDistrict(it)
        },
        onSelectedValue = {
            viewModel.setSelectedDistrict(it)
        },
        optionList = if (uiDistrictState is UIState.Success) (uiDistrictState as UIState.Success).data!!.map { it.name } else emptyList(),
        isLoading = uiDistrictState is UIState.Loading,
        onTapTextField = {viewModel.fetchDistricts()},
        onValidation = { Validation().validateEmpty(it) }
    )
}