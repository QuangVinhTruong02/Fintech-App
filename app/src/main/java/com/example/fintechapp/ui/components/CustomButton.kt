package com.example.fintechapp.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.fintechapp.common.AppColor
import com.example.fintechapp.common.AppTextStyle
import com.example.fintechapp.ui.base.UIButtonState

@Composable
fun CustomButton(
    buttonColor: Color,
    disabledButtonColor: Color = AppColor.darkBlue.copy(alpha = 0.2f),
    contentText: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    buttonState: UIButtonState = UIButtonState.Enable
) {
    Button(
        onClick = { if (buttonState == UIButtonState.Enable) onClick() },
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor,
            disabledContainerColor = disabledButtonColor
        ),
        modifier = modifier.height(48.dp),
        enabled = buttonState == UIButtonState.Enable
    ) {
        when (buttonState) {
            is UIButtonState.Enable -> {
                Text(
                    contentText,
                    style = AppTextStyle.latoBoldFontStyle.copy(color = AppColor.white)
                )
            }

            is UIButtonState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = Color.White,
                    strokeWidth = 2.dp
                )
            }

            is UIButtonState.Disable -> {
                Text(
                    contentText,
                    style = AppTextStyle.latoBoldFontStyle.copy(color = AppColor.white)
                )
            }
        }

    }
}