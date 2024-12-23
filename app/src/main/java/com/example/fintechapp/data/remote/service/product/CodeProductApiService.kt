package com.example.fintechapp.data.remote.service.product

import com.example.fintechapp.data.request.CodeProductRequest
import com.example.fintechapp.data.response.BaseResponse
import com.example.fintechapp.data.response.CodeProductResponse
import com.example.fintechapp.data.response.CodeProductsResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface CodeProductApiService {
    @GET("ecom-products")
    suspend fun fetchProducts(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("search") search: String
    ): Response<BaseResponse<CodeProductsResponse>>

    @GET("ecom-products/{productId}")
    suspend fun fetchProduct(
        @Path("productId") productId : Int
    ) : Response<BaseResponse<CodeProductResponse>>

    @POST("ecom-products")
    suspend fun createProduct(
        @Body codeProductRequest: CodeProductRequest
    ) : Response<BaseResponse<Boolean>>

    @PUT("ecom-products/{productId}")
    suspend fun updateProduct(
        @Path("productId") productId : Int,
        @Body codeProductRequest: CodeProductRequest
    ) : Response<BaseResponse<Boolean>>

    @DELETE("ecom-products")
    suspend fun deleteProductById(
        @Query("productIds[]") productId : Int
    ) : Response<BaseResponse<Boolean>>
}