package com.example.fintechapp.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fintechapp.common.AppColor
import com.example.fintechapp.common.AppIcon
import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.common.AppTextStyle

@Composable
fun CustomEndRowDropDown(
     onClickRemove: () -> Unit
) {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    Box {
        IconButton(onClick = { isExpanded = !isExpanded }) {
            Icon(
                painter = painterResource(AppIcon.icVerticalEllipsis),
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        ) {
            DropdownMenuItem(
                text = {
                    Text(AppLanguage.DETAILS, style = AppTextStyle.latoMediumFontStyle)
                },
                onClick = {
                    isExpanded = false
                }
            )
            DropdownMenuItem(
                text = {
                    Text(AppLanguage.UPDATE, style = AppTextStyle.latoMediumFontStyle)
                },
                onClick = {
                    isExpanded = false
                }
            )
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 5.dp)
            )
            DropdownMenuItem(
                text = {
                    Text(
                        AppLanguage.REMOVE,
                        style = AppTextStyle.latoMediumFontStyle.copy(color = AppColor.red)
                    )
                },
                onClick = {
                    onClickRemove()
                    isExpanded = false
                }
            )
        }
    }
}