package com.example.fintechapp.repository

import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.data.remote.ResultApi
import com.example.fintechapp.data.remote.service.configuration.ConfigurationApiService
import com.example.fintechapp.data.request.AdvertisementRequest
import com.example.fintechapp.data.request.CodeScanSpamRequest
import com.example.fintechapp.data.response.AdvertisementDurationResponse
import com.example.fintechapp.data.response.CodeScanSpamResponse

class ConfigurationRepository(
    private val configurationApiService: ConfigurationApiService
) {
    suspend fun fetchAdvertisementDuration() : ResultApi<AdvertisementDurationResponse>{
        val response = configurationApiService.fetchAdvertisementDuration()
        return if(response.isSuccessful && response.body() != null){
            ResultApi.Success(response.body()?.data)
        }else{
            ResultApi.Error(Exception(AppLanguage.SOMETHING_WENT_WRONG))
        }
    }

    suspend fun fetchCodeScanSpam() : ResultApi<CodeScanSpamResponse>{
        val response = configurationApiService.fetchCodeScanSpam()
        return if(response.isSuccessful && response.body() != null){
            ResultApi.Success(response.body()?.data)
        }else{
            ResultApi.Error(Exception(AppLanguage.SOMETHING_WENT_WRONG))
        }
    }

    suspend fun updateAdvertisementDuration(
        advertisementRequest: AdvertisementRequest
    ) : ResultApi<Void>{
        val response = configurationApiService.updateAdvertisementDuration(advertisementRequest)
        return if(response.isSuccessful){
            ResultApi.Success(null)
        }else{
            ResultApi.Error(Exception(AppLanguage.SOMETHING_WENT_WRONG))
        }
    }

    suspend fun updateCodeScanSpam(
        codeScanSpamRequest: CodeScanSpamRequest
    ) : ResultApi<Void>{
        val response = configurationApiService.updateCodeScanSpam(codeScanSpamRequest)
        return if(response.isSuccessful){
            ResultApi.Success(null)
        }else{
            ResultApi.Error(Exception(AppLanguage.SOMETHING_WENT_WRONG))
        }
    }
}