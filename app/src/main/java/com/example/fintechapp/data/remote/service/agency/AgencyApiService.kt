package com.example.fintechapp.data.remote.service.agency

import com.example.fintechapp.data.response.AgenciesResponse
import com.example.fintechapp.data.response.BaseResponse
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Query

interface AgencyApiService {
    @GET("agencies")
    suspend fun fetchAgencies(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("search") search: String
    ) : Response<BaseResponse<AgenciesResponse>>

    @DELETE("agencies")
    suspend fun deleteAgencyById(
        @Query("agencyIds") agencyId: Int,
    ): Response<Void>
}