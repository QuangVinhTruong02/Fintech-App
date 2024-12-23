package com.example.fintechapp.ui.screens.code_product.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fintechapp.common.AppColor
import com.example.fintechapp.common.AppTextStyle
import com.example.fintechapp.common.extensions.generateInitials
import com.example.fintechapp.data.response.AgencyResponse
import com.example.fintechapp.data.response.CodeProductResponse
import com.example.fintechapp.data.response.CodeProductsResponse
import com.example.fintechapp.ui.base.UIState
import com.example.fintechapp.ui.components.CustomDataTable
import com.example.fintechapp.ui.components.CustomEndRowDropDown
import com.example.fintechapp.ui.components.CustomLoadImage
import com.example.fintechapp.ui.screens.agent.AgentViewModel
import com.example.fintechapp.ui.screens.agent.components.agentDataHeader
import com.example.fintechapp.ui.screens.code_product.CodeProductViewModel

@Composable
fun CodeProductDataTable(
    viewModel: CodeProductViewModel,
    onNavigateToUpdateProduct: (Int) -> Unit
) {
    val uiCodeProductListState: UIState<List<CodeProductResponse>> by viewModel.uiProductListState.collectAsStateWithLifecycle()

    CustomDataTable(
        headerList = codeProductDataHeader(),
        content = {
            if (uiCodeProductListState is UIState.Success) {
                (uiCodeProductListState as UIState.Success).data!!.forEach { item ->
                    row {
                        cell {
                            Row(verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.clickable {
                                    viewModel.fetchCodeProduct(item.id)
                                    viewModel.onSetValueShowDetailDialog(true)
                                }
                            ) {
                                CustomLoadImage(imagesUrl = item.images)
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = item.nameProduct,
                                    style = AppTextStyle.latoMediumFontStyle,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                        cell {
                            if (item.nextProduct != null) {
                                Row(verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.clickable {
//                                    viewModel.setSelectedAgency(item)
//                                    onNavigateDetailAgent(viewModel.selectedAgency.value!!.agentCode)
                                    }
                                ) {
                                    CustomLoadImage(imagesUrl = item.nextProduct.images)
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Text(
                                        text = item.nextProduct.nameProduct,
                                        style = AppTextStyle.latoMediumFontStyle,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            } else {
                                Text(
                                    text = "-",
                                    style = AppTextStyle.latoMediumFontStyle,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                        cell {
                            Text(
                                item.description ?: "",
                                style = AppTextStyle.latoMediumFontStyle,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        cell {
                            CustomEndRowDropDown(
                                onClickDetail = {
                                    viewModel.fetchCodeProduct(item.id)
                                    viewModel.onSetValueShowDetailDialog(true)
                                },
                                onClickRemove = {
                                    viewModel.onSetValueRemoveDialog(true)
                                    viewModel.setSelectedProductId(item.id)
                                },
                                onClickUpdate = {
                                    viewModel.setSelectedProductId(item.id)
                                    if (viewModel.selectedProductId != null) {
                                        onNavigateToUpdateProduct(viewModel.selectedProductId!!)
                                    }
                                    viewModel.setSelectedProductId(null)
                                }
                            )
                        }

                    }
                }
            }
        },
    )
}