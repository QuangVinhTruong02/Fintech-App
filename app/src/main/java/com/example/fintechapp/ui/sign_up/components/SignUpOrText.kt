package com.example.fintechapp.ui.sign_up.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fintechapp.common.AppColor
import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.common.AppTextStyle

@Composable
fun SignUpOrText() {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        HorizontalDivider(
            color = AppColor.grey.copy(alpha = 30f),
            thickness = 0.5.dp,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            AppLanguage.OR,
            style = AppTextStyle.latoRegularFontStyle.copy(
                color = AppColor.darkBlue
            )
        )
        Spacer(modifier = Modifier.width(4.dp))
        HorizontalDivider(
            color = AppColor.grey.copy(alpha = 30f),
            thickness = 0.5.dp,
            modifier = Modifier.weight(1f)
        )
    }
}