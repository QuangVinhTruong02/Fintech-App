package com.example.fintechapp.ui.screens.create_code_product

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fintechapp.common.AppIcon
import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.common.AppTextStyle
import com.example.fintechapp.ui.base.DtgAppState
import com.example.fintechapp.ui.components.AppTopBar
import com.example.fintechapp.ui.screens.create_code_product.components.CreateCodeProductCreateButton
import com.example.fintechapp.ui.screens.create_code_product.components.CreateCodeProductDescriptionInput
import com.example.fintechapp.ui.screens.create_code_product.components.CreateCodeProductNameInput
import com.example.fintechapp.ui.screens.create_code_product.components.CreateCodeProductNextProduct
import com.example.fintechapp.ui.screens.create_code_product.components.CreateCodeProductNextProductDialog
import com.example.fintechapp.ui.screens.create_code_product.components.CreateCodeProductSelectImage

@Composable
fun CreateCodeProductScreen(
    appState: DtgAppState,
    viewModel: CreateCodeProductViewModel = viewModel(),
    onNavigateBack: () -> Unit,
) {

    val showNextProductDialog: Boolean by viewModel.showNextProductDialog.collectAsStateWithLifecycle()

    if (showNextProductDialog) {
        CreateCodeProductNextProductDialog(viewModel)
    }

    Scaffold(
        topBar = {
            AppTopBar(
                leadingIcon = AppIcon.icNavigateBack,
                titleText = AppLanguage.CREATE_PRODUCT,
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
            Spacer(modifier = Modifier.height(20.dp))
            CreateCodeProductNameInput(viewModel)
            Spacer(modifier = Modifier.height(10.dp))
            CreateCodeProductDescriptionInput(viewModel)
            Spacer(modifier = Modifier.height(10.dp))
            CreateCodeProductNextProduct(viewModel)
            Spacer(modifier = Modifier.height(20.dp))
            CreateCodeProductSelectImage(viewModel)
            Spacer(modifier = Modifier.weight(1f))
            CreateCodeProductCreateButton(
                viewModel = viewModel,
                onNavigateBack = onNavigateBack,
                appState = appState
            )
        }
    }
}