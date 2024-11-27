package com.example.fintechapp.screens.ulti

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fintechapp.common.AppColor
import com.example.fintechapp.common.AppTypography

@Composable
fun CustomButton(
    buttonColor: Color,
    contentText: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor
        ),
        modifier = modifier
    ) {
        Text(contentText,
            style = AppTypography().getLatoBoldFontStyle(fontSize = 16.sp)
        )
    }
}