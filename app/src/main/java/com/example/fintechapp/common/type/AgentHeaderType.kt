package com.example.fintechapp.common.type

import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.data.response.AgencyResponse

enum class AgentHeaderType {
    AgentName,
    AgentCode,
    PhoneNumber,
    City,
    FarmersCount;

    fun sortAscendingByAgentHeaderType(agentResponseList: List<AgencyResponse>) : List<AgencyResponse>{
        return when (this) {
            AgentName -> agentResponseList.sortedBy { it.agentName }

            AgentCode -> agentResponseList.sortedBy { it.agentCode }

            PhoneNumber -> agentResponseList.sortedBy { it.phoneNumber }

            City -> agentResponseList.sortedBy { it.agentName }

            FarmersCount -> agentResponseList.sortedBy { it.farmersCount }
        }
    }

    fun sortDescendingByAgentHeaderType(agentResponseList: List<AgencyResponse>) : List<AgencyResponse> {
        return when (this) {
            AgentName -> agentResponseList.sortedByDescending { it.agentName }

            AgentCode -> agentResponseList.sortedByDescending { it.agentCode }

            PhoneNumber -> agentResponseList.sortedByDescending { it.phoneNumber }

            City -> agentResponseList.sortedByDescending { it.agentCode }

            FarmersCount -> agentResponseList.sortedByDescending { it.farmersCount }
        }
    }

    val title: String
        get() = when (this) {
            AgentName -> AppLanguage.AGENT_NAME
            AgentCode -> AppLanguage.AGENT_CODE
            PhoneNumber -> AppLanguage.PHONE_NUMBER
            City -> AppLanguage.CITY
            FarmersCount -> AppLanguage.FARMERS_COUNT
        }
}
