package com.example.fintechapp.ui.screens.agent.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fintechapp.common.AppColor
import com.example.fintechapp.common.AppIcon
import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.common.AppTextStyle
import com.example.fintechapp.ui.base.UIButtonState
import com.example.fintechapp.ui.components.CustomButton
import com.example.fintechapp.ui.components.CustomTextInput
import com.example.fintechapp.ui.screens.agent.AgentViewModel

@Composable
fun AgentView(viewModel: AgentViewModel){
    val searchTextInput: String by viewModel.searchTextInput.collectAsStateWithLifecycle()
    val isLoading: Boolean by viewModel.isLoading.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        Text(
            AppLanguage.AGENT_LIST,
            style = AppTextStyle.latoBoldFontStyle.copy(fontSize = 20.sp),
            modifier = Modifier.padding(horizontal = 10.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        CustomTextInput(
            valueText = searchTextInput,
            leadingIcon = AppIcon.icSearch,
            hintText = AppLanguage.SEARCH,
            onValueChanged = {
                viewModel.onQueryChanged(it)
            },
            trailingIcon = AppIcon.icClose,
            onPressedTrailingIcon = viewModel::onRemoveInput,
            modifier = Modifier.padding(horizontal = 10.dp)
        )
        Spacer(modifier = Modifier.height(5.dp))
        CustomButton(
            buttonColor = AppColor.darkBlue,
            contentText = AppLanguage.NEW_AGENT,
            buttonState = UIButtonState.Enable,
            onClick = {},
            modifier = Modifier.padding(horizontal = 10.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        AgentDataTable(viewModel)
        if(isLoading){
            Box(
                modifier = Modifier.fillMaxWidth().padding(top = 10.dp, bottom = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(32.dp),
                    color = AppColor.darkBlue,
                    strokeWidth = 3.dp
                )
            }
        }
        AgentTableFooter(viewModel)
    }
}