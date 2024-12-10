package com.example.fintechapp.data.remote.service.agency

import com.example.fintechapp.data.request.AgencyRequest
import com.example.fintechapp.data.response.AgenciesResponse
import com.example.fintechapp.data.response.AgencyResponse
import com.example.fintechapp.data.response.BaseResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface AgencyApiService {
    @GET("agencies")
    suspend fun fetchAgencies(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("search") search: String
    ) : Response<BaseResponse<AgenciesResponse>>

    @GET("agencies/{agencyCode}")
    suspend fun fetchAgency(
        @Path("agencyCode") agencyCode: String
    ) : Response<BaseResponse<AgencyResponse>>

    @PUT("agencies/{agencyCode}")
    suspend fun updateAgency(
        @Path("agencyCode") agencyCode: String,
        @Body agencyRequest: AgencyRequest
    ) : Response<BaseResponse<Boolean>>

    @DELETE("agencies")
    suspend fun deleteAgencyById(
        @Query("agencyIds") agencyId: Int,
    ): Response<Void>

    @POST("agencies")
    suspend fun createAgency(
        @Body agencyRequest : AgencyRequest
    ): Response<Void>
}