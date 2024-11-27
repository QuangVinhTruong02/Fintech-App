package com.example.fintechapp.common

import androidx.compose.material3.Typography
import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import com.example.fintechapp.ui.theme.latoFontFamily

@Immutable
class AppTypography {

    fun getLatoRegularFontStyle(fontSize: TextUnit): TextStyle {
        return TextStyle(
            fontFamily = latoFontFamily,
            fontWeight = FontWeight.W400,
            fontSize = fontSize
        )
    }

    fun getLatoMediumFontStyle(fontSize: TextUnit): TextStyle {
        return TextStyle(
            fontFamily = latoFontFamily,
            fontWeight = FontWeight.W500,
            fontSize = fontSize
        )
    }

    fun getLatoBoldFontStyle(fontSize: TextUnit): TextStyle {
        return TextStyle(
            fontFamily = latoFontFamily,
            fontWeight = FontWeight.W700,
            fontSize = fontSize
        )
    }
}