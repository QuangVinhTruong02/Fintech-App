package com.example.fintechapp.ui.screens.agent.components

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
import com.example.fintechapp.common.extensions.generateInitials
import com.example.fintechapp.data.response.AgenciesResponse
import com.example.fintechapp.data.response.AgencyResponse
import com.example.fintechapp.ui.base.UIState
import com.example.fintechapp.ui.components.CustomDataTable
import com.example.fintechapp.ui.components.CustomEndRowDropDown
import com.example.fintechapp.ui.screens.agent.AgentViewModel

@Composable
fun AgentDataTable(
    viewModel: AgentViewModel,
    onNavigateDetailAgent: (String) -> Unit,
    onNavigateToCreateAgent: (String) -> Unit
) {
    val uiAgencyListState: UIState<List<AgencyResponse>> by viewModel.uiAgencyListState.collectAsStateWithLifecycle()

    CustomDataTable(
        headerList = agentDataHeader(),
        content = {
            if (uiAgencyListState is UIState.Success) {
                (uiAgencyListState as UIState.Success<List<AgencyResponse>>).data!!.forEach { item ->
                    row {
                        cell {
                            Row(verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.clickable {
                                    viewModel.setSelectedAgency(item)
                                    onNavigateDetailAgent(viewModel.selectedAgency.value!!.agentCode)
                                }
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
                                        text = item.agentName.generateInitials(),
                                        style = AppTextStyle.latoBoldFontStyle.copy(color = AppColor.darkBlue)
                                    )
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = item.agentName,
                                    style = AppTextStyle.latoMediumFontStyle,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                        cell {
                            Text(
                                item.agentCode,
                                style = AppTextStyle.latoMediumFontStyle,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        cell {
                            Text(
                                item.phoneNumber,
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
                                item.farmersCount.toString(),
                                style = AppTextStyle.latoMediumFontStyle,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        cell {
                            CustomEndRowDropDown(
                                onClickDetail = {
                                    viewModel.setSelectedAgency(item)
                                    onNavigateDetailAgent(viewModel.selectedAgency.value!!.agentCode)
                                },
                                onClickRemove = {
                                    viewModel.setValueShowDialog(true)
                                    viewModel.setSelectedAgency(item)
                                },
                                onClickUpdate = {
                                    viewModel.setSelectedAgency(item)
                                    viewModel.onSetValueOpenSheet(true)
                                }
                            )
                        }

                    }
                }
            }
        },
    )
}

