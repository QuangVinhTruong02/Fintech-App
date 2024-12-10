package com.example.fintechapp.repository

import android.util.Log
import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.data.remote.ResultApi
import com.example.fintechapp.data.remote.service.agency.AgencyApiService
import com.example.fintechapp.data.request.AgencyRequest
import com.example.fintechapp.data.response.AgenciesResponse
import com.example.fintechapp.data.response.AgencyResponse
import com.example.fintechapp.data.response.BaseResponse

class AgencyRepository(
    private val agencyApiService: AgencyApiService,
) {
    suspend fun fetchAgencies(
        page: Int,
        pageSize: Int,
        search: String
    ): ResultApi<BaseResponse<AgenciesResponse>> {
        val response = agencyApiService.fetchAgencies(page, pageSize, search)
        return if (response.isSuccessful && response.body() != null) {
            ResultApi.Success(response.body()!!)
        } else {
            ResultApi.Error(Exception(AppLanguage.SOMETHING_WENT_WRONG))
        }
    }

    suspend fun fetchAgency(
        agencyId: String
    ): ResultApi<BaseResponse<AgencyResponse>> {
        val response = agencyApiService.fetchAgency(agencyId)
        return if (response.isSuccessful && response.body() != null) {
            ResultApi.Success(response.body()!!)
        } else {
            ResultApi.Error(Exception(AppLanguage.SOMETHING_WENT_WRONG))
        }
    }

    suspend fun updateAgency(
        agencyId: String,
        agencyRequest: AgencyRequest
    ): ResultApi<BaseResponse<Boolean>> {
        val response = agencyApiService.updateAgency(agencyId, agencyRequest)
        return if (response.isSuccessful && response.body() != null) {
            ResultApi.Success(response.body()!!)
        } else {
            ResultApi.Error(Exception(AppLanguage.SOMETHING_WENT_WRONG))
        }
    }

    suspend fun deleteAgencyById(agencyId: Int): ResultApi<Boolean> {
        val response = agencyApiService.deleteAgencyById(agencyId)
        return if (response.isSuccessful) {
            ResultApi.Success(true)
        } else {
            ResultApi.Error(Exception(AppLanguage.SOMETHING_WENT_WRONG))
        }
    }

    suspend fun createAgency(agencyRequest: AgencyRequest): ResultApi<Boolean> {
        val response = agencyApiService.createAgency(agencyRequest)
        return if (response.isSuccessful) {
            ResultApi.Success(true)
        } else {
            ResultApi.Error(Exception(AppLanguage.SOMETHING_WENT_WRONG))
        }
    }
}