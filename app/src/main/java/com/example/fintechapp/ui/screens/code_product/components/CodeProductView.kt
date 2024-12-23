package com.example.fintechapp.ui.screens.code_product.components

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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fintechapp.common.AppColor
import com.example.fintechapp.common.AppIcon
import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.common.AppTextStyle
import com.example.fintechapp.data.response.CodeProductResponse
import com.example.fintechapp.ui.base.UIButtonState
import com.example.fintechapp.ui.base.UIState
import com.example.fintechapp.ui.components.CustomButton
import com.example.fintechapp.ui.components.CustomTextInput
import com.example.fintechapp.ui.screens.code_product.CodeProductViewModel

@Composable
fun CodeProductView(
    viewModel: CodeProductViewModel,
    onNavigateToCreateProduct: () -> Unit,
    onNavigateToUpdateProduct: (Int) -> Unit,
) {

    val uiCodeProductListState: UIState<List<CodeProductResponse>> by viewModel.uiProductListState.collectAsStateWithLifecycle()
    val searchInput: String by viewModel.searchTextInput.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        Text(
            AppLanguage.PRODUCT_LIST,
            style = AppTextStyle.latoBoldFontStyle.copy(fontSize = 20.sp),
            modifier = Modifier.padding(horizontal = 10.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        CustomTextInput(
            valueText = searchInput,
            leadingIcon = AppIcon.icSearch,
            hintText = AppLanguage.SEARCH,
            onValueChanged = {
                viewModel.onQueryChanged(it)
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        viewModel.onQueryChanged("")
                    }
                ) {
                    Icon(
                        painter = painterResource(id = AppIcon.icClose),
                        contentDescription = null,
                        modifier = Modifier.size(25.dp)
                    )
                }
            },
            modifier = Modifier.padding(horizontal = 10.dp)
        )
        Spacer(modifier = Modifier.height(5.dp))
        CustomButton(
            buttonColor = AppColor.darkBlue,
            contentText = AppLanguage.NEW_PRODUCT,
            buttonState = UIButtonState.Enable,
            onClick = {
                onNavigateToCreateProduct()
            },
            modifier = Modifier.padding(horizontal = 10.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        CodeProductDataTable(viewModel = viewModel, onNavigateToUpdateProduct = onNavigateToUpdateProduct)
        if (uiCodeProductListState is UIState.Loading) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(32.dp),
                    color = AppColor.darkBlue,
                    strokeWidth = 3.dp
                )
            }
        }
        if (uiCodeProductListState is UIState.Empty || (uiCodeProductListState is UIState.Success && (uiCodeProductListState as UIState.Success).data!!.isEmpty())) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    AppLanguage.NO_DATA_AVAILABLE,
                    style = AppTextStyle.latoBoldFontStyle.copy(color = AppColor.lightGrey)
                )
            }
        }
        if (uiCodeProductListState is UIState.Failure) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "${(uiCodeProductListState as UIState.Failure<List<CodeProductResponse>>).throwable?.message}",
                    style = AppTextStyle.latoBoldFontStyle.copy(color = AppColor.lightGrey)
                )
            }
        }
        CodeProductFooter(viewModel)
    }
}