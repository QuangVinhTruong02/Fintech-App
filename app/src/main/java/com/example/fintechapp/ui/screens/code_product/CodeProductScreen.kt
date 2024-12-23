package com.example.fintechapp.ui.screens.code_product

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fintechapp.common.AppColor
import com.example.fintechapp.common.AppConst
import com.example.fintechapp.common.AppIcon
import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.common.AppTextStyle
import com.example.fintechapp.ui.base.DtgAppState
import com.example.fintechapp.ui.base.UIButtonState
import com.example.fintechapp.ui.base.UIState
import com.example.fintechapp.ui.components.CustomButton
import com.example.fintechapp.ui.components.CustomShowRemoveDialog
import com.example.fintechapp.ui.components.CustomTextInput
import com.example.fintechapp.ui.screens.agent.components.AgentDataTable
import com.example.fintechapp.ui.screens.code_product.components.CodeProductDataTable
import com.example.fintechapp.ui.screens.code_product.components.CodeProductDetailDialog
import com.example.fintechapp.ui.screens.code_product.components.CodeProductView

@Composable
fun CodeProductScreen(
    appState: DtgAppState,
    viewModel: CodeProductViewModel = viewModel(),
    onNavigateToCreateProduct: () -> Unit,
    onNavigateToUpdateProduct: (Int) -> Unit
) {

    val updateData =
        appState.appNavigation.navController.currentBackStackEntry?.savedStateHandle?.getStateFlow<Boolean?>(
            AppConst.PRODUCT_RETURN_KEY,
            null
        )?.collectAsState()

    LaunchedEffect(Unit) {

        viewModel.onInit()

        updateData?.value?.let {
            viewModel.fetchCodeProducts("")
            appState.appNavigation.navController.currentBackStackEntry?.savedStateHandle?.remove<Boolean?>(
                AppConst.PRODUCT_RETURN_KEY
            )
        }
    }

    val showDetailDialog by viewModel.showDetailDialog.collectAsStateWithLifecycle()
    val showRemoveDialog by viewModel.showRemoveDialog.collectAsStateWithLifecycle()
    if (showDetailDialog) {
        CodeProductDetailDialog(viewModel)
    }

    if(showRemoveDialog){
        CustomShowRemoveDialog(
            title = AppLanguage.DELETE_PRODUCT,
            message = AppLanguage.ARE_YOU_SURE_YOU_WANT_TO_DELETE_THIS_PRODUCT,
            onDismiss = { viewModel.onSetValueRemoveDialog(false) },
            onConfirm = {
                viewModel.onDeleteProductById()
                viewModel.onSetValueRemoveDialog(false)
            },
        )
    }

    CodeProductView(
        viewModel = viewModel,
        onNavigateToCreateProduct = onNavigateToCreateProduct,
        onNavigateToUpdateProduct = onNavigateToUpdateProduct
    )
}