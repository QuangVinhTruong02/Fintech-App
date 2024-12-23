package com.example.fintechapp.data.remote.service.upload

import com.example.fintechapp.data.response.BaseResponse
import com.example.fintechapp.data.response.ImageResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface UploadApiService {
    @Multipart
    @POST("uploads")
    suspend fun uploadImage(
        @Part file: MultipartBody.Part,
    ): Response<BaseResponse<String>>
}