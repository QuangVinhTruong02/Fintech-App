package com.example.fintechapp.ui.screens.detail_agent

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fintechapp.common.AppIcon
import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.ui.components.AppTopBar

@Composable
fun DetailAgentScreen() {
    Scaffold(
        topBar = {
            AppTopBar(
                leadingIcon = AppIcon.icNavigateBack,
                titleText = AppLanguage.DETAIL_AGENCY
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .padding(top = 10.dp, start = 10.dp, end = 10.dp)
        ) {
            Column {

            }
        }
    }
}