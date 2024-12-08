package com.example.fintechapp.ui.base

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.fintechapp.common.AppIcon

sealed class UICheckState {
    data object Checked: UICheckState()
    data object Unchecked : UICheckState()
    data object Subtracted: UICheckState()

    fun getIcon(): Int? {
        return when (this) {
            is Checked -> AppIcon.icCheck
            is Unchecked -> null
            is Subtracted -> AppIcon.icRemove
        }
    }
}