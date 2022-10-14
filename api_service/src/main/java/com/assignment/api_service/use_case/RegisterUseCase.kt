package com.assignment.api_service.use_case

import com.assignment.api_service.service.RegisterService
import com.assignment.common.ext.AppResponse
import com.assignment.common.request.RegisterRequest
import com.assignment.common.response.register.RegisterResponse
import kotlinx.coroutines.flow.flow

class RegisterUseCase(val registerService: RegisterService) {
    operator fun invoke(registerRequest: RegisterRequest) = flow<AppResponse<RegisterResponse>> {
        try {
            emit(AppResponse.loading())
            val result = registerService.registerForm(registerRequest)
            result.body()?.let {
                emit(AppResponse.success(it))
            } ?: run {
                emit(AppResponse.failure(Exception("Username already exist")))
            }
        } catch (e: Exception) {
            emit(AppResponse.failure(e))
        }
    }
}