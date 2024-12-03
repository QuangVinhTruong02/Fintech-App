package com.example.fintechapp.ui.base


import androidx.compose.runtime.Composable
import com.example.fintechapp.ui.navigation.AppNavigation
import com.example.fintechapp.ui.navigation.rememberAppNavigation


@Composable
fun rememberDoraLabClientAppState(
    appNavigation: AppNavigation = rememberAppNavigation(),
) = DtgAppState(
    appNavigation = appNavigation,
)

class DtgAppState(
    val appNavigation: AppNavigation,
) {

}

