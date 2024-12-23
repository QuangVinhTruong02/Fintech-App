package com.example.fintechapp.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.fintechapp.common.AppColor
import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.common.AppTextStyle

@Composable
fun CustomShowRemoveDialog(
    modifier: Modifier = Modifier,
    title: String,
    message: String,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = {
            onDismiss()
        },
        title = {
            Text(
                text = title,
                style = AppTextStyle.latoBoldFontStyle.copy(fontSize = 20.sp)
            )
        },
        text = { Text(text = message, style = AppTextStyle.latoMediumFontStyle) },
        confirmButton = {
            Button(
                onClick = {
                    onConfirm()
                },
                colors = ButtonDefaults.buttonColors(containerColor = AppColor.darkBlue)
            ) {
                Text(
                    text = AppLanguage.CONFIRM,
                    color = Color.White
                )
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    onDismiss()
                },
                colors = ButtonDefaults.buttonColors(containerColor = AppColor.darkBlue)
            ) {
                Text(
                    text = AppLanguage.CANCEL,
                    color = Color.White
                )
            }
        }
    )
}