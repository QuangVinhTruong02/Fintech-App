package com.example.fintechapp.ui.base


//import SnackbarController
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.fintechapp.ui.navigation.AppNavHost
//import androidx.hilt.navigation.compose.hiltViewModel
//import com.tekup.io.bams_mobile.common.CustomTopSnackbarHost
//import com.tekup.io.bams_mobile.common.ObserveAsEvents
//import com.tekup.io.bams_mobile.ui.navigation.AppNavHost
//import com.tekup.io.bams_mobile.ui.theme.BamsTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun DtgComposeApp(
    appState: DtgAppState = rememberDoraLabClientAppState(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
) {

    val snackbarHostState = remember {
        SnackbarHostState()
    }
//    ObserveAsEvents(
//        flow = SnackbarController.events,
//        snackbarHostState
//    ) { event ->
//        coroutineScope.launch {
//            snackbarHostState.currentSnackbarData?.dismiss()
//
//            val result = snackbarHostState.showSnackbar(
//                message = event.message,
//                actionLabel = event.action?.name,
//                duration = SnackbarDuration.Short
//            )
//
//            if (result == SnackbarResult.ActionPerformed) {
//                event.action?.action?.invoke()
//            }
//        }
//    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Transparent,
        snackbarHost = {
//            CustomTopSnackbarHost(
//                snackbarHostState = snackbarHostState
//            )
        },
    ) { padding ->
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(padding.calculateTopPadding())
//                    .background(color = BamsTheme.colors.backgroundColor)
            )
            Box(
                modifier = Modifier
                    .imePadding()
                    .weight(1f)
//                    .background(color = BamsTheme.colors.backgroundColor)
            ) {
                AppNavHost(
                    modifier = Modifier.fillMaxSize(),
                    appNavigation = appState.appNavigation,
                    appState = appState,
                )
            }
        }
    }
}

