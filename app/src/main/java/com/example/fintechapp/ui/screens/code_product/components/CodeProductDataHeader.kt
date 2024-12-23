package com.example.fintechapp.ui.screens.code_product.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import com.example.fintechapp.common.AppIcon
import com.example.fintechapp.common.AppTextStyle
import com.example.fintechapp.common.type.AgentHeaderType
import com.example.fintechapp.common.type.CodeProductType
import com.seanproctor.datatable.DataColumn
import com.seanproctor.datatable.TableColumnWidth

@Composable
fun codeProductDataHeader(): List<DataColumn> {
    return buildList {
        CodeProductType.entries.forEach { headerTitle ->
            add(
                DataColumn(
                    header = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                headerTitle.title.uppercase(),
                                style = AppTextStyle.latoBoldFontStyle,
                                modifier = Modifier.fillMaxWidth(0.7f),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    },
                    width = TableColumnWidth.Fraction(0.7f)
                )
            )
        }
        add(
            DataColumn(
                header = {
                    Icon(
                        painter = painterResource(AppIcon.icVerticalEllipsis),
                        contentDescription = null
                    )
                },
                width = TableColumnWidth.Fraction(0.1f),
            )
        )
    }
}