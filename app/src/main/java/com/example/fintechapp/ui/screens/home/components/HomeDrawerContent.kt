package com.example.fintechapp.ui.screens.home.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fintechapp.common.AppColor
import com.example.fintechapp.common.AppIcon
import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.common.AppTextStyle
import com.example.fintechapp.common.model.navigationDrawerItemModelList
import com.example.fintechapp.common.type.PageType
import com.example.fintechapp.ui.screens.home.HomeViewModel

@Composable
fun HomeDrawerContent(viewModel: HomeViewModel, onCloseDrawer: () -> Unit) {
    val indexPageType: PageType by viewModel.indexPageType.collectAsStateWithLifecycle()
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = AppLanguage.DTGENTA,
            style = AppTextStyle.latoBoldFontStyle.copy(fontSize = 30.sp),
            modifier = Modifier.padding(all = 16.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = onCloseDrawer
        ) {
            Icon(painter = painterResource(AppIcon.icClose), contentDescription = null)
        }
    }
    navigationDrawerItemModelList.forEachIndexed { index, item ->
        val isSelected: Boolean = indexPageType == item.pageType
        NavigationDrawerItem(
            icon = {
                Icon(
                    painter = painterResource(item.icon),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp),
                    tint = if (isSelected) AppColor.white else AppColor.black
                )
            },
            label = {
                Text(
                    text = item.titleText,
                    style = AppTextStyle.latoBoldFontStyle.copy(color = if (isSelected) AppColor.white else AppColor.black),
                    modifier = Modifier.padding(all = 16.dp)
                )
            },
            selected = isSelected,
            onClick = {
                viewModel.onSelectedPage(item.pageType)
                onCloseDrawer()
            },
            modifier = Modifier.padding(horizontal = 16.dp),
            shape = RoundedCornerShape(8.dp),
            colors = NavigationDrawerItemDefaults.colors(
                selectedContainerColor = AppColor.darkBlue.copy(alpha = 0.5f),
                unselectedContainerColor = Color.Transparent
            )
        )
    }
}