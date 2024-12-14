package com.example.fintechapp.ui.screens.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fintechapp.common.type.PageType
import com.example.fintechapp.ui.base.DtgAppState
import com.example.fintechapp.ui.screens.agent.AgentScreen
import com.example.fintechapp.ui.screens.home.HomeViewModel
import com.example.fintechapp.ui.screens.qr_code_product.QRCodeProductScreen

@Composable
fun HomeView(
    paddingValues: PaddingValues,
    appState: DtgAppState,
    onNavigateToCreateAgent: (String) -> Unit,
    onNavigateDetailAgent: (String) -> Unit,
    viewModel: HomeViewModel,
) {
    val indexPageType: PageType by viewModel.indexPageType.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {
        when (indexPageType) {
            PageType.Agent -> AgentScreen(
                onNavigateToCreateAgent = onNavigateToCreateAgent,
                onNavigateDetailAgent = onNavigateDetailAgent,
                appState = appState
            )

            PageType.QRCodeProduct -> QRCodeProductScreen()
            else -> {
                Text("NO PATH RESULT")
            }
        }
    }
}