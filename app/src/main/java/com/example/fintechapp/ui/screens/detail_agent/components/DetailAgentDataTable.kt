package com.example.fintechapp.ui.screens.detail_agent.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fintechapp.common.AppColor
import com.example.fintechapp.common.AppTextStyle
import com.example.fintechapp.common.Utils.DateTimeUtils
import com.example.fintechapp.common.extensions.generateInitials
import com.example.fintechapp.data.response.ClientResponse
import com.example.fintechapp.data.response.ClientsResponse
import com.example.fintechapp.ui.base.UIState
import com.example.fintechapp.ui.components.CustomDataTable
import com.example.fintechapp.ui.components.CustomEndRowDropDown
import com.example.fintechapp.ui.screens.detail_agent.DetailAgentViewModel

//
@Composable
fun DetailAgentDataTable(viewModel: DetailAgentViewModel) {
//    val clientList: List<ClientResponse> by viewModel.clientList.collectAsStateWithLifecycle()
//    val isLoading: Boolean by viewModel.isLoading.collectAsStateWithLifecycle()

    val uiClientsState: UIState<ClientsResponse> by viewModel.uiClientListState.collectAsStateWithLifecycle()

    CustomDataTable(
        headerList = detailAgentDataClientHeader(),
        content = {
            if (uiClientsState is UIState.Success && ((uiClientsState as UIState.Success<ClientsResponse>).data!!.clientList.isNotEmpty())) {
                val clientList = (uiClientsState as UIState.Success).data!!.clientList
                clientList.forEach { item ->
                    row {
                        cell {
                            Row(verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.clickable { }
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier
                                        .size(50.dp)
                                        .background(
                                            color = AppColor.darkBlue.copy(alpha = 0.2f),
                                            shape = CircleShape
                                        )
                                ) {
                                    Text(
                                        text = item.fullName.generateInitials()
                                            .toString(),
                                        style = AppTextStyle.latoBoldFontStyle.copy(color = AppColor.darkBlue)
                                    )
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = item.fullName,
                                    style = AppTextStyle.latoMediumFontStyle,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                        cell {
                            Text(
                                item.phone,
                                style = AppTextStyle.latoMediumFontStyle,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        cell {
                            Text(
                                item.location.province.name,
                                style = AppTextStyle.latoMediumFontStyle,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        cell {
                            Text(
                                DateTimeUtils.getFormattedDate(dateTime = item.createdAt),
                                style = AppTextStyle.latoMediumFontStyle,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        cell {
                            CustomEndRowDropDown(
                                onClickRemove = {},
                                onClickUpdate = {},
                                onClickDetail = {}
                            )
                        }

                    }
                }
            }
        },
    )
}