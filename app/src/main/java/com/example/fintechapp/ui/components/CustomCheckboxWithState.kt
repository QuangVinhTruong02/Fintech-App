package com.example.fintechapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fintechapp.common.AppColor
import com.example.fintechapp.ui.base.UICheckState

@Composable
fun CustomCheckboxWithState(
    modifier: Modifier = Modifier,
    uiCheckedState: UICheckState,
    onCheckedState:() -> Unit,
) {
    Box(
        modifier = modifier
            .size(24.dp)
            .background(
                color = if (uiCheckedState == UICheckState.Unchecked) Color.Transparent else AppColor.darkBlue,
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                border = BorderStroke(width = 1.dp, color = Color.Gray),
                shape = RoundedCornerShape(8.dp)

            )
            .clickable {
                onCheckedState()
            },
        contentAlignment = Alignment.Center
    ) {
        if (uiCheckedState != UICheckState.Unchecked) {
            Icon(
                painter = painterResource(uiCheckedState.getIcon()!!),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}