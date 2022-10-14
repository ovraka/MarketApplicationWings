package com.assignment.api_service.use_case

import com.assignment.api_service.service.ProductService
import com.assignment.common.ext.AppResponse
import com.assignment.common.response.product.ProductResponse
import kotlinx.coroutines.flow.flow

class ProductUseCase(val service: ProductService) {
    operator fun invoke(token: String) = flow<AppResponse<ProductResponse>> {
        try {
            emit(AppResponse.loading())
            val result = service.getAllProduct(token)
            result.body()?.let {
                emit(AppResponse.success(it))
            } ?: run {
                emit(AppResponse.failure(Exception("Failed load data")))
            }
        } catch (e: Exception) {
            emit(AppResponse.failure(e))
        }
    }
}