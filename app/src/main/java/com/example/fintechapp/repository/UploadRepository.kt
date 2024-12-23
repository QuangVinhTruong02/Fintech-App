package com.example.fintechapp.repository

import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.data.remote.ResultApi
import com.example.fintechapp.data.remote.service.upload.UploadApiService
import okhttp3.MultipartBody

class UploadRepository(
    private val uploadApiService: UploadApiService
) {
    suspend fun uploadImage(image: MultipartBody.Part): ResultApi<String> {
        val response = uploadApiService.uploadImage(image)
        return if (response.isSuccessful) {
            ResultApi.Success(response.body()?.data)
        } else {
            ResultApi.Error(Exception(response.message()))
        }
    }
}