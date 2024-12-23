package com.example.fintechapp.ui.screens.create_code_product.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.common.AppTextStyle
import com.example.fintechapp.common.Utils.Validation
import com.example.fintechapp.ui.components.CustomTextInput
import com.example.fintechapp.ui.screens.create_code_product.CreateCodeProductViewModel
import com.example.fintechapp.ui.screens.update_code_product.UpdateCodeProductViewModel

@Composable
fun UpdateCodeProductNameInput(viewModel: UpdateCodeProductViewModel){
    Text(AppLanguage.NAME_PRODUCT, style = AppTextStyle.latoMediumFontStyle)
    Spacer(modifier = Modifier.height(10.dp))
    CustomTextInput(
        valueText = viewModel.nameInput,
        onValueChanged = {
            viewModel.nameInput = it
            viewModel.onValidateButtonState()
        },
        hintText = AppLanguage.ENTER_NAME_PRODUCT,
        onValidate = {Validation().validateEmpty(viewModel.nameInput)}
    )
}