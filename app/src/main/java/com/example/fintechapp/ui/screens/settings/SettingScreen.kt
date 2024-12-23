package com.example.fintechapp.ui.screens.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fintechapp.common.AppColor
import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.common.AppTextStyle
import com.example.fintechapp.ui.base.UIState
import com.example.fintechapp.ui.screens.settings.components.SettingAdvertisingTime
import com.example.fintechapp.ui.screens.settings.components.SettingCodeScanningTime

@Composable
fun SettingScreen(viewModel: SettingViewModel = viewModel()) {

    val uiState: UIState<Boolean> by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.fetchAll()
    }

    when (uiState) {
        is UIState.Success -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 15.dp)
            ) {
                Text(
                    AppLanguage.SETTING,
                    style = AppTextStyle.latoBoldFontStyle.copy(fontSize = 18.sp)
                )
                Spacer(modifier = Modifier.height(20.dp))
                SettingAdvertisingTime(viewModel)
                Spacer(modifier = Modifier.height(10.dp))
                SettingCodeScanningTime(viewModel)
            }
        }

        is UIState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is UIState.Failure -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    "${(uiState as UIState.Failure<Boolean>).throwable}",
                    style = AppTextStyle.latoMediumFontStyle.copy(color = AppColor.lightGrey)
                )
            }
        }
        is UIState.Empty -> {}
    }
}