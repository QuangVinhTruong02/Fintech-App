package com.example.fintechapp.ui.screens.update_code_product

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fintechapp.common.AppColor
import com.example.fintechapp.common.AppIcon
import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.common.AppTextStyle
import com.example.fintechapp.data.response.CodeProductResponse
import com.example.fintechapp.ui.base.DtgAppState
import com.example.fintechapp.ui.base.UIState
import com.example.fintechapp.ui.components.AppTopBar
import com.example.fintechapp.ui.screens.create_code_product.components.UpdateCodeProductDescriptionInput
import com.example.fintechapp.ui.screens.create_code_product.components.UpdateCodeProductNameInput
import com.example.fintechapp.ui.screens.create_code_product.components.UpdateCodeProductNextProduct
import com.example.fintechapp.ui.screens.create_code_product.components.UpdateCodeProductNextProductDialog
import com.example.fintechapp.ui.screens.create_code_product.components.UpdateCodeProductSelectImage
import com.example.fintechapp.ui.screens.update_code_product.components.UpdateCodeProductButton

@Composable
fun UpdateCodeProductScreen(
    appState: DtgAppState,
    viewModel: UpdateCodeProductViewModel = viewModel(),
    onNavigateBack: () -> Unit,
    productId: Int,
) {

    LaunchedEffect(Unit) {
        viewModel.fetchCodeProductCurrent(productId)
    }
    val uiProductCurrentState: UIState<CodeProductResponse> by viewModel.uiProductCurrentState.collectAsStateWithLifecycle()

    val showNextProductDialog: Boolean by viewModel.showNextProductDialog.collectAsStateWithLifecycle()
    if (showNextProductDialog) {
        UpdateCodeProductNextProductDialog(viewModel)
    }


    Scaffold(
        topBar = {
            AppTopBar(
                leadingIcon = AppIcon.icNavigateBack,
                titleText = AppLanguage.UPDATE_PRODUCT,
                onPressedLeading = onNavigateBack
            )
        }
    ) { paddingValue ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 15.dp, end = 15.dp, bottom = 15.dp)
                .padding(paddingValue)
                .verticalScroll(rememberScrollState())
        ) {
            when (uiProductCurrentState) {

                is UIState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is UIState.Success -> {
                    Spacer(modifier = Modifier.height(20.dp))
                    UpdateCodeProductNameInput(viewModel)
                    Spacer(modifier = Modifier.height(10.dp))
                    UpdateCodeProductDescriptionInput(viewModel)
                    Spacer(modifier = Modifier.height(10.dp))
                    UpdateCodeProductNextProduct(viewModel)
                    Spacer(modifier = Modifier.height(20.dp))
                    UpdateCodeProductSelectImage(viewModel)
                    Spacer(modifier = Modifier.weight(1f))
                    UpdateCodeProductButton(
                        viewModel = viewModel,
                        onNavigateBack = onNavigateBack,
                        appState = appState
                    )
                }

                is UIState.Failure -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "${(uiProductCurrentState as UIState.Failure).throwable}",
                            style = AppTextStyle.latoMediumFontStyle.copy(color = AppColor.red)
                        )
                    }
                }

                is UIState.Empty -> {
                }

            }
        }
    }
}