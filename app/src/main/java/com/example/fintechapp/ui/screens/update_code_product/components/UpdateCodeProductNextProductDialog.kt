package com.example.fintechapp.ui.screens.create_code_product.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fintechapp.common.AppColor
import com.example.fintechapp.common.AppIcon
import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.common.AppTextStyle
import com.example.fintechapp.data.response.CodeProductResponse
import com.example.fintechapp.ui.base.UIState
import com.example.fintechapp.ui.components.CustomButton
import com.example.fintechapp.ui.components.CustomLoadImage
import com.example.fintechapp.ui.components.CustomShowDialog
import com.example.fintechapp.ui.components.CustomTextInput
import com.example.fintechapp.ui.screens.create_code_product.CreateCodeProductViewModel
import com.example.fintechapp.ui.screens.update_code_product.UpdateCodeProductViewModel

@Composable
fun UpdateCodeProductNextProductDialog(
    viewModel: UpdateCodeProductViewModel
) {
    val uiProductListState: UIState<List<CodeProductResponse>> by viewModel.uiProductListState.collectAsStateWithLifecycle()
    val searchTextInput: String by viewModel.searchTextInput.collectAsStateWithLifecycle()

    CustomShowDialog(
        onDismiss = {
            viewModel.onSetValueSelectedProduct(null)
            viewModel.onSetValueShowNextProductDialog(false)
        },
        content = {
            val selectedProduct: CodeProductResponse? by viewModel.selectedNextProduct.collectAsStateWithLifecycle()
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f)
                    .padding(15.dp)
            ) {
                CustomTextInput(
                    valueText = searchTextInput,
                    hintText = AppLanguage.SEARCH,
                    onValueChanged = {
                        viewModel.onQueryChanged(it)
                    },
                    leadingIcon = AppIcon.icSearch,
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                viewModel.onQueryChanged("")
                            }
                        ) {
                            Icon(
                                painter = painterResource(AppIcon.icClose),
                                contentDescription = null,
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    }
                )
                uiProductListState.let { state ->
                    when (state) {
                        is UIState.Success -> {
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight(0.9f)
                            ) {
                                val productList = state.data

                                if (productList?.isEmpty() == true) {
                                    item {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(15.dp),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                AppLanguage.NO_DATA_AVAILABLE,
                                                style = AppTextStyle.latoMediumFontStyle.copy(
                                                    color = AppColor.lightGrey
                                                )
                                            )
                                        }
                                    }
                                } else {
                                    productList?.let {
                                        items(productList) { codeProduct ->
                                            Box(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(vertical = 5.dp)
                                                    .border(
                                                        width = 0.5.dp,
                                                        color = AppColor.lightGrey,
                                                        shape = RoundedCornerShape(8.dp)
                                                    )
                                                    .background(
                                                        color = if (selectedProduct == codeProduct) AppColor.darkBlue.copy(
                                                            alpha = 0.4f
                                                        ) else Color.Transparent,
                                                        shape = RoundedCornerShape(8.dp)
                                                    )
                                                    .clickable {
                                                        viewModel.onSetValueSelectedProduct(
                                                            codeProduct
                                                        )
                                                    }
                                            ) {
                                                Row(verticalAlignment = Alignment.CenterVertically,
                                                    modifier = Modifier.clickable {}
                                                ) {
                                                    CustomLoadImage(imagesUrl = codeProduct.images)
                                                    Spacer(modifier = Modifier.width(10.dp))
                                                    Text(
                                                        text = codeProduct.nameProduct,
                                                        style = AppTextStyle.latoMediumFontStyle,
                                                        overflow = TextOverflow.Ellipsis
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        is UIState.Loading -> {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight(0.3f),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }

                        is UIState.Failure -> {}
                        is UIState.Empty -> {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight(0.5f)
                                    .padding(15.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    AppLanguage.NO_DATA_AVAILABLE,
                                    style = AppTextStyle.latoMediumFontStyle.copy(color = AppColor.lightGrey)
                                )
                            }
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.Bottom
                ) {
                    CustomButton(
                        contentText = AppLanguage.CONFIRM,
                        onClick = {
                            if (selectedProduct != null) {
                                viewModel.onSetValueShowNextProductDialog(false)
                            }
                        }
                    )
                    CustomButton(
                        contentText = AppLanguage.CANCEL,
                        buttonColor = AppColor.darkBlue.copy(alpha = 0.2f),
                        onClick = {
                            viewModel.onSetValueSelectedProduct(null)
                            viewModel.onSetValueShowNextProductDialog(false)
                        }
                    )
                }
            }
        }
    )
}