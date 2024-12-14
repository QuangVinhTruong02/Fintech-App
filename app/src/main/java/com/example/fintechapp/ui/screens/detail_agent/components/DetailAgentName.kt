package com.example.fintechapp.ui.screens.detail_agent.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fintechapp.common.AppColor
import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.common.AppTextStyle
import com.example.fintechapp.common.extensions.generateInitials
import com.example.fintechapp.data.response.AgencyResponse

@Composable
fun DetailAgentName(
    agency: AgencyResponse
) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Column {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .background(
                            color = AppColor.darkBlue.copy(alpha = 0.2f),
                            shape = RoundedCornerShape(8.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        agency.agentName.generateInitials(),
                        style = AppTextStyle.latoBoldFontStyle.copy(
                            fontSize = 20.sp,
                            color = AppColor.darkBlue
                        )
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(agency.agentName, style = AppTextStyle.latoBoldFontStyle)
                Spacer(modifier = Modifier.height(10.dp))
                Box(
                    modifier = Modifier
                        .heightIn(min = 30.dp)
                        .widthIn(min = 50.dp)
                        .background(
                            color = AppColor.darkBlue.copy(alpha = 0.2f),
                            shape = RoundedCornerShape(4.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "Agency",
                        style = AppTextStyle.latoRegularFontStyle.copy(
                            fontSize = 12.sp,
                            color = AppColor.darkBlue
                        )
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
                HorizontalDivider()
                Spacer(modifier = Modifier.height(15.dp))
            }
            Column {
                Text(
                    AppLanguage.DETAILS.uppercase(),
                    style = AppTextStyle.latoMediumFontStyle.copy(color = AppColor.grey)
                )
                Spacer(modifier = Modifier.height(15.dp))
                DetailAgentItem(title = AppLanguage.AGENT_CODE, content = agency.agentCode)
                Spacer(modifier = Modifier.height(15.dp))
                DetailAgentItem(title = AppLanguage.PHONE_NUMBER, content = agency.phoneNumber)
                Spacer(modifier = Modifier.height(15.dp))
                DetailAgentItem(title = AppLanguage.ADDRESS, content = "${agency.location.address}, ${agency.location.province.name}")
                Spacer(modifier = Modifier.height(15.dp))
                DetailAgentItem(title = AppLanguage.CLIENT_COUNT, content = agency.farmersCount.toString())
            }
        }
    }
}