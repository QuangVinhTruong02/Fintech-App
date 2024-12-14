package com.example.fintechapp.ui.screens.home

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fintechapp.common.AppColor
import com.example.fintechapp.common.AppIcon
import com.example.fintechapp.common.AppImage
import com.example.fintechapp.common.type.PageType
import com.example.fintechapp.ui.base.DtgAppState
import com.example.fintechapp.ui.components.AppTopBar
import com.example.fintechapp.ui.screens.agent.AgentScreen
import com.example.fintechapp.ui.screens.home.components.DropDownAvatar
import com.example.fintechapp.ui.screens.home.components.HomeDrawerContent
import com.example.fintechapp.ui.screens.home.components.HomeView
import com.example.fintechapp.ui.screens.qr_code_product.QRCodeProductScreen
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    appState: DtgAppState,
    viewModel: HomeViewModel = viewModel(),
    onNavigateToSignIn: () -> Unit,
    onNavigateToCreateAgent: (String) -> Unit,
    onNavigateDetailAgent:(String) -> Unit,
) {

    val drawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed
    )

    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.getUserPhoneLocal()
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = false,
        drawerContent = {
            ModalDrawerSheet(modifier = Modifier.fillMaxWidth(0.8f)) {
                HomeDrawerContent(
                    viewModel,
                    onCloseDrawer = {
                        scope.launch {
                            drawerState.close()
                        }
                    },
                )
            }
        }
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp),
            topBar = {
                AppTopBar(
                    onPressedLeading = {
                        scope.launch {
                            drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    },
                    leadingIcon = AppIcon.icMenu,
                    backgroundColor = AppColor.white,
                    actions = {
                        DropDownAvatar(
                            viewModel = viewModel,
                            onNavigateToSignIn = onNavigateToSignIn
                        )
                    }
                )
            }
        ) { paddingValues ->
            HomeView(
                appState = appState,
                paddingValues = paddingValues,
                viewModel = viewModel,
                onNavigateToCreateAgent = onNavigateToCreateAgent,
                onNavigateDetailAgent = onNavigateDetailAgent,
            )
        }
    }
}