package com.example.fintechapp.ui.screens.create_code_product.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.common.AppTextStyle
import com.example.fintechapp.ui.components.CustomTextInput
import com.example.fintechapp.ui.screens.create_code_product.CreateCodeProductViewModel

@Composable
fun CreateCodeProductDescriptionInput(
    viewModel: CreateCodeProductViewModel
){
    Text(AppLanguage.DESCRIPTION, style = AppTextStyle.latoMediumFontStyle)
    Spacer(modifier = Modifier.height(10.dp))
    CustomTextInput(
        valueText = viewModel.descriptionInput,
        onValueChanged = {
            viewModel.descriptionInput = it
        },
        hintText = "",
        singleLine = false,
        modifier = Modifier.heightIn(min = 80.dp)
    )
}