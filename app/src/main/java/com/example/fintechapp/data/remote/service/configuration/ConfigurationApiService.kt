package com.example.fintechapp.data.remote.service.configuration

import com.example.fintechapp.data.request.AdvertisementRequest
import com.example.fintechapp.data.request.CodeScanSpamRequest
import com.example.fintechapp.data.response.AdvertisementDurationResponse
import com.example.fintechapp.data.response.BaseResponse
import com.example.fintechapp.data.response.CodeScanSpamResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

interface ConfigurationApiService {
    @GET("advertisementDuration")
    suspend fun fetchAdvertisementDuration() : Response<BaseResponse<AdvertisementDurationResponse>>

    @GET("codeScanSpam")
    suspend fun fetchCodeScanSpam() : Response<BaseResponse<CodeScanSpamResponse>>

    @PUT("advertisementDuration")
    suspend fun updateAdvertisementDuration(
        @Body advertisementRequest: AdvertisementRequest
    ) : Response<Void>

    @PUT("codeScanSpam")
    suspend fun updateCodeScanSpam(
        @Body codeScanSpamRequest: CodeScanSpamRequest
    ) : Response<Void>
}