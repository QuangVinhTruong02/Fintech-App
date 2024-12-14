package com.example.fintechapp.ui.screens.detail_agent.components.bottomsheet

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.common.AppTextStyle
import com.example.fintechapp.common.Utils.Validation
import com.example.fintechapp.ui.components.CustomTextFieldDropDown
import com.example.fintechapp.ui.screens.detail_agent.DetailAgentViewModel

@Composable
fun DetailAgentProvinceInput(viewModel: DetailAgentViewModel) {

    Text("${AppLanguage.PROVINCE}/${AppLanguage.CITY}*", style = AppTextStyle.latoMediumFontStyle)
    Spacer(modifier = Modifier.height(5.dp))
    CustomTextFieldDropDown(
        enable = false,
        isExpanded = false,
        valueText = viewModel.provinceInput,
        hintText = AppLanguage.SELECT_PROVINCE_CITY,
        onValueChanged = {},
        onTapTextField = {},
        setIsExpanded = {},
        onSelectedValue = {},
        optionList = emptyList(),
        isLoading = false,
        onValidation = { Validation().validateEmpty(it) }
    )
}