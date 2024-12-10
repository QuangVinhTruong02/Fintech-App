package com.example.fintechapp.data.remote.service.location

import com.example.fintechapp.data.response.BaseResponse
import com.example.fintechapp.data.response.DistrictResponse
import com.example.fintechapp.data.response.ProvinceResponse
import com.example.fintechapp.data.response.WardResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface LocationApiService {
    @GET("provinces")
    suspend fun fetchProvinces(
    ): Response<BaseResponse<List<ProvinceResponse>>>

    @GET("{provinceCode}/districts")
    suspend fun fetchDistricts(
        @Path("provinceCode") provinceCode: String
    ): Response<BaseResponse<List<DistrictResponse>>>

    @GET("{districtCode}/wards")
    suspend fun fetchWards(
        @Path("districtCode") districtCode: String
    ): Response<BaseResponse<List<WardResponse>>>
}