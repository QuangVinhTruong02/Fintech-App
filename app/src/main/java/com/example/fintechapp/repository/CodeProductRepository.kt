package com.example.fintechapp.repository

import android.util.Log
import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.data.remote.ResultApi
import com.example.fintechapp.data.remote.service.product.CodeProductApiService
import com.example.fintechapp.data.request.CodeProductRequest
import com.example.fintechapp.data.response.CodeProductResponse
import com.example.fintechapp.data.response.CodeProductsResponse

class CodeProductRepository(
    private val codeProductApiService: CodeProductApiService
) {
    suspend fun fetchProducts(
        page: Int,
        pageSize: Int,
        search: String
    ): ResultApi<CodeProductsResponse> {
        val response =
            codeProductApiService.fetchProducts(page = page, pageSize = pageSize, search = search)
        return if (response.isSuccessful && response.body() != null) {
            ResultApi.Success(response.body()!!.data)
        } else {
            ResultApi.Error(Exception(AppLanguage.SOMETHING_WENT_WRONG))
        }
    }

    suspend fun fetchProduct(
        productId: Int
    ): ResultApi<CodeProductResponse> {
        val response = codeProductApiService.fetchProduct(productId = productId)
        return if (response.isSuccessful) {
            ResultApi.Success(response.body()!!.data)
        } else {
            ResultApi.Error(Exception(AppLanguage.SOMETHING_WENT_WRONG))
        }
    }

    suspend fun createProduct(
        codeProductRequest: CodeProductRequest
    ): ResultApi<Boolean> {
        val response = codeProductApiService.createProduct(codeProductRequest = codeProductRequest)
        return if (response.isSuccessful) {
            ResultApi.Success(response.body()?.data)
        } else {
            ResultApi.Error(Exception(AppLanguage.SOMETHING_WENT_WRONG))
        }
    }

    suspend fun updateProduct(
        productId: Int,
        codeProductRequest: CodeProductRequest
    ): ResultApi<Boolean> {
        val response = codeProductApiService.updateProduct(
            productId = productId,
            codeProductRequest = codeProductRequest
        )
        return if (response.isSuccessful) {
            ResultApi.Success(response.body()?.data)
        } else {
            ResultApi.Error(Exception(AppLanguage.SOMETHING_WENT_WRONG))
        }
    }

    suspend fun deleteProductById(
        productId: Int,
    ): ResultApi<Boolean> {
        val response = codeProductApiService.deleteProductById(
            productId = productId
        )
        return if (response.isSuccessful) {
            ResultApi.Success(response.body()?.data)
        } else {
            ResultApi.Error(Exception(response.message()))
        }
    }

}