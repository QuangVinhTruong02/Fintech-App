package com.example.fintechapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.fintechapp.common.AppColor
import com.example.fintechapp.common.AppIcon
import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.common.AppTextStyle

@Composable
fun CustomErrorDialog(
    modifier: Modifier = Modifier,
    title: String,
    message: String,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            tonalElevation = 4.dp,
            shape = RoundedCornerShape(8.dp),
            color = AppColor.white,
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = AppIcon.icClose),
                    contentDescription = "Icon",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(50.dp)
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                )
                Text(
                    text = title,
                    style = AppTextStyle.latoBoldFontStyle.copy(fontSize = 20.sp),
                    modifier = Modifier.align(Alignment.CenterHorizontally)

                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = message,
                    style = AppTextStyle.latoRegularFontStyle,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(15.dp))
                CustomButton(
                    contentText = AppLanguage.CONFIRM,
                    buttonColor = AppColor.darkBlue,
                    onClick = onConfirm,
                    modifier = Modifier.align(Alignment.End)
                )
            }
        }
    }
}

