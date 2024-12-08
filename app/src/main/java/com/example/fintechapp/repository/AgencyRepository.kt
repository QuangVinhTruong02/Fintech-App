package com.example.fintechapp.repository

import android.util.Log
import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.data.remote.ResultApi
import com.example.fintechapp.data.remote.service.agency.AgencyApiService
import com.example.fintechapp.data.response.AgenciesResponse
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

    suspend fun deleteAgencyById(agencyId : Int): ResultApi<Boolean>{
        val response = agencyApiService.deleteAgencyById(agencyId)
        return if(response.isSuccessful){
            ResultApi.Success(true)
        }else{
            ResultApi.Error(Exception(AppLanguage.SOMETHING_WENT_WRONG))
        }
    }
}