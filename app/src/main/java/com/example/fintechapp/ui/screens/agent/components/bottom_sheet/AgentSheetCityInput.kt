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
import com.example.fintechapp.data.response.AgencyResponse
import com.example.fintechapp.data.response.ProvinceResponse
import com.example.fintechapp.ui.base.UIState
import com.example.fintechapp.ui.components.CustomTextFieldDropDown
import com.example.fintechapp.ui.screens.agent.AgentViewModel

@Composable
fun AgentSheetCityInput(viewModel: AgentViewModel) {
    val isExpandedProvince: Boolean by viewModel.isExpandedProvince.collectAsStateWithLifecycle()
    val uiProvinceListState: UIState<List<ProvinceResponse>> by viewModel.uiProvinceListState.collectAsStateWithLifecycle()
    val selectedAgency: AgencyResponse? by viewModel.selectedAgency.collectAsStateWithLifecycle()

    Text("${AppLanguage.PROVINCE}/${AppLanguage.CITY}*", style = AppTextStyle.latoMediumFontStyle)
    Spacer(modifier = Modifier.height(5.dp))
    CustomTextFieldDropDown(
        isExpanded = isExpandedProvince,
        valueText = viewModel.provinceInput,
        hintText = AppLanguage.SELECT_PROVINCE_CITY,
        onValueChanged = {
            viewModel.provinceInput = it
            viewModel.setValueConfirmButtonState()
        },
        enable = selectedAgency == null,
        onTapTextField = {
            if (selectedAgency == null) {
                viewModel.fetchProvinces()
            }
        },
        setIsExpanded = {
            if (selectedAgency == null) {
                viewModel.setIsExpandedProvince(it)
            }
        },
        onSelectedValue = {
            if (selectedAgency == null) {
                viewModel.setSelectedProvince(it)
            }
        },
        optionList = if (uiProvinceListState is UIState.Success) (uiProvinceListState as UIState.Success).data!!.map { it.name } else emptyList(),
        isLoading = uiProvinceListState is UIState.Loading,
        onValidation = { Validation().validateEmpty(it) }
    )
}