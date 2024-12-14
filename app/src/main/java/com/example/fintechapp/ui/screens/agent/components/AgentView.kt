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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fintechapp.common.AppColor
import com.example.fintechapp.common.AppIcon
import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.common.AppTextStyle
import com.example.fintechapp.data.response.AgencyResponse
import com.example.fintechapp.ui.base.UIButtonState
import com.example.fintechapp.ui.base.UIState
import com.example.fintechapp.ui.components.CustomButton
import com.example.fintechapp.ui.components.CustomTextInput
import com.example.fintechapp.ui.screens.agent.AgentViewModel

@Composable
fun AgentView(
    viewModel: AgentViewModel,
    onNavigateToCreateAgent: (String) -> Unit,
    onNavigateDetailAgent: (String) -> Unit
) {
    val searchTextInput: String by viewModel.searchTextInput.collectAsStateWithLifecycle()
    val uiAgencyListState: UIState<List<AgencyResponse>> by viewModel.uiAgencyListState.collectAsStateWithLifecycle()
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
            trailingIcon = {
                IconButton(
                    onClick = viewModel::onRemoveInput
                ) {
                    Icon(
                        painter = painterResource(id = AppIcon.icClose),
                        contentDescription = null,
                        modifier = Modifier.size(25.dp)
                    )
                }
            },
            modifier = Modifier.padding(horizontal = 10.dp)
        )
        Spacer(modifier = Modifier.height(5.dp))
        CustomButton(
            buttonColor = AppColor.darkBlue,
            contentText = AppLanguage.NEW_AGENT,
            buttonState = UIButtonState.Enable,
            onClick = {
                viewModel.setSelectedAgency(null)
                viewModel.onSetValueOpenSheet(true)
            },
            modifier = Modifier.padding(horizontal = 10.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        AgentDataTable(
            viewModel = viewModel,
            onNavigateToCreateAgent = onNavigateToCreateAgent,
            onNavigateDetailAgent = onNavigateDetailAgent
        )
        if (uiAgencyListState is UIState.Loading) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(32.dp),
                    color = AppColor.darkBlue,
                    strokeWidth = 3.dp
                )
            }
        }
        if (uiAgencyListState is UIState.Empty || (uiAgencyListState is UIState.Success && (uiAgencyListState as UIState.Success).data!!.isEmpty())) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    AppLanguage.NO_DATA_AVAILABLE,
                    style = AppTextStyle.latoBoldFontStyle.copy(color = AppColor.lightGrey)
                )
            }
        }
        AgentTableFooter(viewModel)
    }
}