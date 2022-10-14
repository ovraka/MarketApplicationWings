package com.assignment.api_service.service

import com.assignment.common.request.RegisterRequest
import com.assignment.common.response.register.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterService {
    @POST("register")
    suspend fun registerForm(
        @Body registerRequest: RegisterRequest
    ): Response<RegisterResponse>
}