package com.example.fintechapp.ui.screens.detail_agent.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
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
import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.common.AppTextStyle
import com.example.fintechapp.data.response.AgencyResponse
import com.example.fintechapp.data.response.ClientResponse
import com.example.fintechapp.data.response.ClientsResponse
import com.example.fintechapp.ui.base.UIState
import com.example.fintechapp.ui.screens.detail_agent.DetailAgentViewModel

@Composable
fun DetailAgentView(
    paddingValues: PaddingValues, viewModel: DetailAgentViewModel,
    agency: AgencyResponse
) {
    val uiClientsState: UIState<ClientsResponse> by viewModel.uiClientListState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .imePadding()
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(AppColor.lightGrey)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Box(modifier = Modifier.background(AppColor.white)) {
                    Column(modifier = Modifier.padding(15.dp)) {
                        DetailAgentName(agency)
                        Spacer(modifier = Modifier.height(10.dp))
                        DetailAgentUpdateButton(viewModel)
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Box(
                    modifier = Modifier
                        .background(AppColor.white)
                        .fillMaxSize()

                ) {
                    Column(
                        modifier = Modifier
                            .padding(15.dp)
                            .fillMaxSize()
                    ) {
                        Text(
                            AppLanguage.CLIENT_LIST_OF_AGENCY,
                            style = AppTextStyle.latoMediumFontStyle.copy(fontSize = 18.sp)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        DetailAgentSearchInput(viewModel)
                        Spacer(modifier = Modifier.height(10.dp))
                        DetailAgentDataTable(viewModel = viewModel)
                        if (uiClientsState is UIState.Loading) {
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
                        if (uiClientsState is UIState.Success) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 10.dp, bottom = 10.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                if ((uiClientsState as UIState.Success).data == null)
                                    Text(
                                        AppLanguage.SOMETHING_WENT_WRONG,
                                        style = AppTextStyle.latoMediumFontStyle.copy(color = AppColor.lightGrey)
                                    )
                                if ((uiClientsState as UIState.Success).data!!.clientList.isEmpty()) {
                                    Text(
                                        AppLanguage.NO_DATA_AVAILABLE,
                                        style = AppTextStyle.latoMediumFontStyle.copy(color = AppColor.lightGrey)
                                    )
                                }
                            }
                        }
                        if (uiClientsState is UIState.Failure) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 10.dp, bottom = 10.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    (uiClientsState as UIState.Failure).throwable?.message
                                        ?: AppLanguage.SOMETHING_WENT_WRONG,
                                    style = AppTextStyle.latoMediumFontStyle.copy(color = AppColor.lightGrey)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}