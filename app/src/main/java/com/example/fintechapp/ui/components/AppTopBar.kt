package com.example.fintechapp.ui.components

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    title: String = "",
    titleSize: TextUnit = 20.sp,
    backgroundColor: Color = AppColor.white,
    onBackNavigation: (() -> Unit)? = null,
) {
    TopAppBar(
        title = { Text(title) },
        colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = backgroundColor),
//        actions = {
//            IconButton(onClick = onMenuClick) {
//                AppIcon(
//                    painter = rememberVectorPainter(image = Icons.Filled.Menu),
//                    tint = contentColor
//                )
//            }
//        },
        navigationIcon = {
            onBackNavigation?.let {
                IconButton(onClick = it) {
                    Icon(
                        painter = painterResource(
                            id = AppIcon.icNavigateBack
                        ),
                        contentDescription = null
                    )
                }
            }
        }
    )
}