package com.example.fintechapp.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.fintechapp.common.AppIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownMenu() {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    Box(modifier = Modifier.fillMaxSize()) {
        IconButton(
            onClick = {isExpanded = !isExpanded}
        ) {
            Icon(painter = painterResource(AppIcon.icMenu), contentDescription = null)
        }
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        ) {
            DropdownMenuItem(
                text = { Text("MALE") },
                onClick = {
                    isExpanded = false
                }
            )
            DropdownMenuItem(
                text = { Text("FEMALE") },
                onClick = {
                    isExpanded = false
                }
            )
        }

    }
}