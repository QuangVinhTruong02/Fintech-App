package com.example.fintechapp.ui.screens.code_product.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fintechapp.common.AppColor
import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.common.AppTextStyle
import com.example.fintechapp.data.response.CodeProductResponse
import com.example.fintechapp.ui.base.UIState
import com.example.fintechapp.ui.components.CustomLoadImage
import com.example.fintechapp.ui.components.CustomShowDialog
import com.example.fintechapp.ui.screens.code_product.CodeProductViewModel

@Composable
fun CodeProductDetailDialog(viewModel: CodeProductViewModel) {

    val uiProductState: UIState<CodeProductResponse> by viewModel.uiProductState.collectAsStateWithLifecycle()

    CustomShowDialog(
        onDismiss = { viewModel.onSetValueShowDetailDialog(false) },
        content = {
            Column(modifier = Modifier.padding(20.dp)) {
                when (uiProductState) {
                    is UIState.Loading -> {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    is UIState.Success -> {
                        val product = (uiProductState as UIState.Success<CodeProductResponse>).data
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ){
                            CustomLoadImage(
                                imagesUrl = product!!.images,
                                height = 200.dp,
                                width = 300.dp
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            product?.nameProduct ?: AppLanguage.UNKNOWN,
                            style = AppTextStyle.latoBoldFontStyle.copy(
                                color = AppColor.darkBlue,
                                fontSize = 18.sp
                            )
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            product?.description ?: AppLanguage.UNKNOWN,
                            style = AppTextStyle.latoMediumFontStyle
                        )
                    }

                    is UIState.Failure -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                AppLanguage.SOMETHING_WENT_WRONG,
                                style = AppTextStyle.latoMediumFontStyle.copy(color = AppColor.red)
                            )
                        }
                    }

                    is UIState.Empty -> {

                    }
                }
            }
        }
    )
}