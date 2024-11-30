package com.example.fintechapp.common

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.fintechapp.ui.theme.interFontFamily
import com.example.fintechapp.ui.theme.latoFontFamily

@Immutable
object AppTextStyle {
    //lato font famlily
    val latoRegularFontStyle: TextStyle = TextStyle(
        fontFamily = latoFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 16.sp,
        color = AppColor.black
    )
    val latoMediumFontStyle: TextStyle = TextStyle(
        fontFamily = latoFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = 16.sp,
        color = AppColor.black
    )
    val latoBoldFontStyle: TextStyle = TextStyle(
        fontFamily = latoFontFamily,
        fontWeight = FontWeight.W700,
        fontSize = 16.sp,
        color = AppColor.black
    )

    //inter font family
    val interRegularFontStyle: TextStyle = TextStyle(
        fontFamily = interFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 16.sp,
        color = AppColor.black
    )
}