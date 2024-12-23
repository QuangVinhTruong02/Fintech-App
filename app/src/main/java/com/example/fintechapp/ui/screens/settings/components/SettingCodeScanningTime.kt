package com.example.fintechapp.ui.screens.settings.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fintechapp.common.AppColor
import com.example.fintechapp.common.AppIcon
import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.common.AppTextStyle
import com.example.fintechapp.common.Utils.Validation
import com.example.fintechapp.ui.base.UIButtonState
import com.example.fintechapp.ui.components.CustomButton
import com.example.fintechapp.ui.components.CustomTextInput
import com.example.fintechapp.ui.screens.settings.SettingViewModel

@Composable
fun SettingCodeScanningTime(viewModel: SettingViewModel) {

    val switchCodeScanningTime: Boolean by viewModel.switchCodeScanningTimeState.collectAsStateWithLifecycle()
    val uiCodeScanSpamButtonState: UIButtonState by viewModel.uiCodeScanSpamButtonState.collectAsStateWithLifecycle()

    Text(
        AppLanguage.CODE_SCANNING_TIME,
        style = AppTextStyle.latoMediumFontStyle
    )
    Spacer(modifier = Modifier.height(5.dp))
    Row(modifier = Modifier.fillMaxWidth()) {
        CustomTextInput(
            modifier = Modifier.fillMaxWidth(0.4f),
            hintText = "",
            valueText = viewModel.codeScanningTimeInput,
            onValueChanged = {
                viewModel.codeScanningTimeInput = it
                viewModel.onValidCodeScanSpamButton()
            },
            leadingIcon = AppIcon.icTimer,
            onValidate = { Validation().validateNumber(viewModel.codeScanningTimeInput) },
            keyBoardType = KeyboardType.Number
        )
        Spacer(modifier = Modifier.width(10.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Switch(
                checked = switchCodeScanningTime,
                onCheckedChange = viewModel::onChangeSwitchCodeScanningTime,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = AppColor.darkBlue,
                    uncheckedThumbColor = AppColor.lightGrey,
                )
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(AppLanguage.ACTIVE, style = AppTextStyle.latoMediumFontStyle)
        }
        Spacer(modifier = Modifier.width(10.dp))
        CustomButton(
            onClick = viewModel::updateCodeScanSpam,
            buttonState = uiCodeScanSpamButtonState,
            showContentToast = AppLanguage.SUCCESSFULLY_UPDATE_CODE_SCAN_TIME,
            contentText = AppLanguage.SAVE,
            buttonColor = AppColor.darkBlue
        )
    }
}