package com.example.fintechapp.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.fintechapp.common.AppColor
import com.example.fintechapp.common.AppIcon
import com.example.fintechapp.common.AppTextStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    titleText: String = "",
    titleSize: TextUnit = 20.sp,
    titleIcon: Int? = null,
    leadingIcon: Int? = null,
    backgroundColor: Color = AppColor.white,
    onPressedTitleIcon: (() -> Unit)? = null,
    onPressedLeading: (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {}
) {
    TopAppBar(
        title = {
            if (titleIcon != null) {
                IconButton(onClick = { onPressedTitleIcon?.invoke() }) {
                    Icon(
                        painter = painterResource(
                            id = titleIcon
                        ),
                        contentDescription = null
                    )
                }
            } else {
                Text(titleText, style = AppTextStyle.latoBoldFontStyle.copy(fontSize = 18.sp))
            }
        },
        colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = backgroundColor),
        actions = actions,
        navigationIcon = {
            onPressedLeading?.let {
                IconButton(onClick = it) {
                    Icon(
                        painter = painterResource(
                            id = leadingIcon ?: AppIcon.icNavigateBack
                        ),
                        contentDescription = null
                    )
                }
            }
        }
    )
}