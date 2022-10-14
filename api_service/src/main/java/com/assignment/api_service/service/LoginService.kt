package com.assignment.api_service.service

import com.assignment.common.request.LoginRequest
import com.assignment.common.response.login.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("login")
    suspend fun loginForm(
        @Body loginRequest: LoginRequest
    ): Response<LoginResponse>
}