package com.example.fintechapp.ui.screens.agent.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fintechapp.common.AppColor
import com.example.fintechapp.common.AppIcon
import com.example.fintechapp.ui.screens.agent.AgentViewModel

@Composable
fun AgentTableFooter(viewModel: AgentViewModel) {
    val nextHasDataToFetch: Boolean by viewModel.nextHasDataToFetch.collectAsStateWithLifecycle()
    val preHasDataToFetch: Boolean by viewModel.preHasDataToFetch.collectAsStateWithLifecycle()
    val totalCountItem : Int by viewModel.totalCount.collectAsStateWithLifecycle()
    Row(
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Gray.copy(alpha = 0.2f))
    ) {
        Text("$totalCountItem Item")
        Spacer(modifier = Modifier.width(15.dp))
        IconButton(
            onClick = viewModel::onPageDecrement,
            enabled = preHasDataToFetch
        ) {
            Icon(
                painter = painterResource(AppIcon.icLeft),
                contentDescription = null,
                modifier = Modifier.size(30.dp),
                tint = if (preHasDataToFetch) AppColor.black else AppColor.grey
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        IconButton(
            onClick = viewModel::onPageIncrement,
            enabled = nextHasDataToFetch
        ) {
            Icon(
                painter = painterResource(AppIcon.icRight),
                contentDescription = null,
                modifier = Modifier.size(30.dp),
                tint = if (nextHasDataToFetch) AppColor.black else AppColor.grey
            )
        }
    }
}