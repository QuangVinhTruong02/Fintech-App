package com.example.fintechapp.ui.screens.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fintechapp.common.AppIcon
import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.common.AppTextStyle
import com.example.fintechapp.ui.components.AvatarUser
import com.example.fintechapp.ui.components.CustomListTileWithIcon
import com.example.fintechapp.ui.screens.home.HomeViewModel

@Composable
fun DropDownAvatar(viewModel: HomeViewModel, onNavigateToSignIn: () -> Unit) {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    val userPhoneLocal: String by viewModel.userPhoneLocal.collectAsStateWithLifecycle()
    Box {
        AvatarUser(
            onClick = { isExpanded = !isExpanded }
        )
        Spacer(modifier = Modifier.height(10.dp))
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        ) {
            DropdownMenuItem(
                text = {
                    Row {
                        AvatarUser()
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(userPhoneLocal, style = AppTextStyle.latoBoldFontStyle)
                            Spacer(modifier = Modifier.height(5.dp))
                            Text("Admin", style = AppTextStyle.latoMediumFontStyle)
                        }
                    }
                },
                onClick = {
                    isExpanded = false
                }
            )
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 5.dp)
            )
            DropdownMenuItem(
                text = {
                    CustomListTileWithIcon(
                        leadingIcon = AppIcon.icProfile,
                        titleText = AppLanguage.PROFILE
                    )
                },
                onClick = {
                    isExpanded = false
                }
            )
            DropdownMenuItem(
                text = {
                    CustomListTileWithIcon(
                        leadingIcon = AppIcon.icSetting,
                        titleText = AppLanguage.SETTING
                    )
                },
                onClick = {
                    isExpanded = false
                }
            )
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 5.dp)
            )
            DropdownMenuItem(
                text = {
                    CustomListTileWithIcon(
                        leadingIcon = AppIcon.icLogout,
                        titleText = AppLanguage.LOGOUT
                    )
                },
                onClick = {
                    viewModel.onLogout()
                    onNavigateToSignIn()
                    isExpanded = false
                }
            )
        }
    }
}