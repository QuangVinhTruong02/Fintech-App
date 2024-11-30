package com.example.fintechapp.common

import androidx.compose.ui.graphics.Color

object AppColor {
    val darkBlue: Color = "#1D3A70".color
    val orange: Color = "#F56C2A".color
    val lightGrey: Color = "#C4C4C4".color
    val white: Color = "#FFFFFF".color
    val blueBell: Color = "#9491BB".color
    val black: Color = "#1A1A1A".color
    var grey : Color = "#6B698E".color
    var red : Color = "#E71616".color

    private val String.color get() = Color(android.graphics.Color.parseColor(this))
}