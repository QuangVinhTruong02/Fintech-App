package com.example.fintechapp.ui.fun_compose

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.fintechapp.common.AppColor
import com.example.fintechapp.common.AppTextStyle

@Composable
fun CustomButton(
    buttonColor: Color,
    contentText: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enable: Boolean = true
) {
    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor
        ),
        modifier = modifier.height(48.dp),
        enabled = true
    ) {
        Text(
            contentText,
            style = AppTextStyle.latoBoldFontStyle.copy(color = AppColor.white)
        )
    }
}