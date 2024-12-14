package com.example.fintechapp.ui.screens.detail_agent.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fintechapp.common.AppIcon
import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.ui.components.CustomTextInput
import com.example.fintechapp.ui.screens.detail_agent.DetailAgentViewModel

@Composable
fun DetailAgentSearchInput(viewModel: DetailAgentViewModel) {
    val searchInput : String by viewModel.searchTextInput.collectAsStateWithLifecycle()
    CustomTextInput(
        valueText = searchInput,
        onValueChanged = {
            viewModel.onQueryChanged(it)
        },
        hintText = "${AppLanguage.SEARCH}...",
        leadingIcon = AppIcon.icSearch,
        trailingIcon = {
            IconButton(onClick = {viewModel.onRemoveInput()}) {
                Icon(
                    painter = painterResource(AppIcon.icClose), contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    )
}