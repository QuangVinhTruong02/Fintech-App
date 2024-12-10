package com.example.fintechapp.ui.screens.detail_agent.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fintechapp.common.AppColor
import com.example.fintechapp.common.AppTextStyle

@Composable
fun DetailAgentName() {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier
                .padding(15.dp)
                .background(
                    color = AppColor.darkBlue.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            Text("QV", style = AppTextStyle.latoBoldFontStyle)
        }
    }
}