package com.example.fintechapp.ui.screens.create_agent.components

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
import com.example.fintechapp.data.response.ProvinceResponse
import com.example.fintechapp.ui.components.CustomTextFieldDropDown
import com.example.fintechapp.ui.screens.create_agent.CreateAgentViewModel

@Composable
fun CreateAgentCityInput(viewModel: CreateAgentViewModel) {
    val isExpandedProvince: Boolean by viewModel.isExpandedProvince.collectAsStateWithLifecycle()
    val provinceList: List<ProvinceResponse> by viewModel.provinceList.collectAsStateWithLifecycle()
    val isProvinceLoading: Boolean by viewModel.isProvinceLoading.collectAsStateWithLifecycle()

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
        onTapTextField = viewModel::fetchProvinces,
        setIsExpanded = { viewModel.setIsExpandedProvince(it) },
        onSelectedValue = { viewModel.setSelectedProvince(it) },
        optionList = provinceList.map { it.name },
        isLoading = isProvinceLoading,
        onValidation = { Validation().validateEmpty(it) }
    )
}