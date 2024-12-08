package com.example.fintechapp.ui.screens.agent.components

import androidx.compose.foundation.background
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
import com.example.fintechapp.data.response.AgencyResponse
import com.example.fintechapp.ui.components.CustomCheckBox
import com.example.fintechapp.ui.components.CustomDataTable
import com.example.fintechapp.ui.components.CustomEndRowDropDown
import com.example.fintechapp.ui.screens.agent.AgentViewModel

@Composable
fun AgentDataTable(viewModel: AgentViewModel) {
    val agencyList: List<AgencyResponse> by viewModel.agencyList.collectAsStateWithLifecycle()
    val selectedAgents: Map<Int, Boolean> by viewModel.selectedAgents.collectAsStateWithLifecycle()
    val isLoading: Boolean by viewModel.isLoading.collectAsStateWithLifecycle()

    CustomDataTable(
        headerList = agentDataHeader(viewModel),
        content = {
            if (!isLoading) {
                agencyList.forEach { item ->
                    row {
                        cell {
                            CustomCheckBox(
                                checked = selectedAgents[item.id] ?: false,
                                onCheckedChange = { isChecked ->
                                    viewModel.onToggleAgentSelection(item.id, isChecked)
                                }
                            )
                        }
                        cell {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier
                                        .size(30.dp)
                                        .background(
                                            color = AppColor.darkBlue.copy(alpha = 0.2f),
                                            shape = CircleShape
                                        )
                                ) {
                                    Text(
                                        text = item.agentName.first()
                                            .toString(),
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
                                item.phoneNumber,
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
                                onClickRemove = {
                                    viewModel.setValueShowDialog(true)
                                    viewModel.setSelectedAgency(item)
                                }
                            )
                        }

                    }
                }
            }
        },
    )

}

