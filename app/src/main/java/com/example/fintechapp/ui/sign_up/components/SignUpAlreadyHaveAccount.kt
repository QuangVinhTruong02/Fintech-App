package com.example.fintechapp.ui.sign_up.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import com.example.fintechapp.common.AppColor
import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.common.AppTextStyle

@Composable
fun SignUpAlreadyHaveAccount(
    onClickLogin: () -> Unit
) {
    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = AppColor.darkBlue)) {
            append("${AppLanguage.ALREADY_HAVE_AN_ACCOUNT}? ")
        }
        pushStringAnnotation(tag = "LOGIN", annotation = "LOGIN_CLICKED")
        withStyle(
            style = SpanStyle(
                color = AppColor.orange,
                fontWeight = FontWeight.W700
            )
        ) {
            append(AppLanguage.LOGIN)
        }
        pop()
    }

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.clickable {
                val annotation = annotatedString.getStringAnnotations(
                    tag = "LOGIN",
                    start = 0,
                    end = annotatedString.length
                ).firstOrNull()

                if (annotation != null) {
                    onClickLogin()
                }
            },
            text = annotatedString,
            style = AppTextStyle.latoRegularFontStyle,
        )
    }
}