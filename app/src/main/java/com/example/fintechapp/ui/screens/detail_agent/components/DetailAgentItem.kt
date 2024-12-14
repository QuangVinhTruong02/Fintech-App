package com.example.fintechapp.ui.screens.detail_agent.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.common.AppTextStyle

@Composable
fun DetailAgentItem(
    title: String,
    content: String,
) {
    Row {
        Text("$title: ", style = AppTextStyle.latoMediumFontStyle)
        Spacer(modifier = Modifier.width(5.dp))
        Text(content, style = AppTextStyle.latoMediumFontStyle)
    }
}