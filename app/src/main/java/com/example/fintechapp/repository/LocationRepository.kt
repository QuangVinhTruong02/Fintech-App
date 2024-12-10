package com.example.fintechapp.repository

import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.data.remote.ResultApi
import com.example.fintechapp.data.remote.service.location.LocationApiService
import com.example.fintechapp.data.response.BaseResponse
import com.example.fintechapp.data.response.DistrictResponse
import com.example.fintechapp.data.response.ProvinceResponse
import com.example.fintechapp.data.response.WardResponse

class LocationRepository(private val locationApiService: LocationApiService) {
    suspend fun fetchProvinces(): ResultApi<BaseResponse<List<ProvinceResponse>>>{
        val response = locationApiService.fetchProvinces()
        return if(response.isSuccessful){
            ResultApi.Success(response.body())
        }else{
            ResultApi.Error(Exception(AppLanguage.SOMETHING_WENT_WRONG))
        }
    }

    suspend fun fetchDistricts(provinceCode: String) : ResultApi<BaseResponse<List<DistrictResponse>>>{
        val response = locationApiService.fetchDistricts(provinceCode)
        return if(response.isSuccessful){
            ResultApi.Success(response.body())
        }else{
            ResultApi.Error(Exception(AppLanguage.SOMETHING_WENT_WRONG))
        }
    }

    suspend fun fetchWard(districtCode: String) : ResultApi<BaseResponse<List<WardResponse>>>{
        val response = locationApiService.fetchWards(districtCode)
        return if(response.isSuccessful){
            ResultApi.Success(response.body())
        }else{
            ResultApi.Error(Exception(AppLanguage.SOMETHING_WENT_WRONG))
        }
    }
}