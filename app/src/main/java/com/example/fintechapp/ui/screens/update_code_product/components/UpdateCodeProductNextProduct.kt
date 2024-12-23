package com.example.fintechapp.ui.screens.create_code_product.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fintechapp.common.AppColor
import com.example.fintechapp.common.AppIcon
import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.common.AppTextStyle
import com.example.fintechapp.data.response.CodeProductResponse
import com.example.fintechapp.ui.components.CustomLoadImage
import com.example.fintechapp.ui.screens.create_code_product.CreateCodeProductViewModel
import com.example.fintechapp.ui.screens.update_code_product.UpdateCodeProductViewModel

@Composable
fun UpdateCodeProductNextProduct(viewModel: UpdateCodeProductViewModel) {
    val selectedProduct: CodeProductResponse? by viewModel.selectedNextProduct.collectAsStateWithLifecycle()

    Text(AppLanguage.NEXT_PRODUCT, style = AppTextStyle.latoMediumFontStyle)
    Spacer(modifier = Modifier.height(10.dp))
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .border(shape = RoundedCornerShape(8.dp), width = 0.5.dp, color = AppColor.lightGrey)
            .clickable {
                viewModel.onSetValueShowNextProductDialog(true)
            },
        contentAlignment = if (selectedProduct == null) Alignment.Center else Alignment.TopStart
    ) {
        if (selectedProduct == null) {
            Text(
                AppLanguage.THERE_ARE_NO_PRODUCTS,
                style = AppTextStyle.latoRegularFontStyle.copy(color = AppColor.lightGrey)
            )
        } else {
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable {}
                ) {
                    CustomLoadImage(imagesUrl = selectedProduct!!.images)
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = selectedProduct!!.nameProduct,
                        style = AppTextStyle.latoMediumFontStyle,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                IconButton(onClick = { viewModel.onSetValueSelectedProduct(null) }) {
                    Icon(painter = painterResource(AppIcon.icClose), contentDescription = null)
                }
            }
        }
    }
}