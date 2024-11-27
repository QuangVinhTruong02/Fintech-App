package com.example.fintechapp.common

import androidx.compose.ui.graphics.Color

object AppColor {
    val darkBlue : Color = "#1D3A70".color
    val orange : Color = "#F56C2A".color
    val lightGrey : Color = "#C4C4C4".color

    private val String.color get() = Color(android.graphics.Color.parseColor(this))
}