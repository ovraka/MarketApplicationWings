package com.assignment.api_service.service

import com.assignment.common.response.product.ProductResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ProductService {
    @GET("product")
    suspend fun getAllProduct(
        @Header("Authorization") token: String
    ): Response<ProductResponse>

    @GET("product")
    fun getAllProductCall(
        @Header("Authorization") token: String
    ): Call<ProductResponse>
}