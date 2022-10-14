package com.assignment.api_service.use_case

import com.assignment.api_service.DataStorePreference
import com.assignment.api_service.service.LoginService
import com.assignment.common.ext.AppResponse
import com.assignment.common.request.LoginRequest
import com.assignment.common.response.login.LoginResponse
import kotlinx.coroutines.flow.flow

class LoginUseCase(val loginService: LoginService, val dataStorePreference: DataStorePreference) {
    operator fun invoke(loginRequest: LoginRequest) = flow<AppResponse<LoginResponse>> {
        try {
            emit(AppResponse.loading())
            val result = loginService.loginForm(loginRequest)
            result.body()?.let {
                if (it.error) {
                    emit(AppResponse.failure(Exception("Invalid Username or Password")))
                } else {
                    emit(AppResponse.success(it))
                    dataStorePreference.saveToken(it.loginResult.token)
                    dataStorePreference.saveUsername(it.loginResult.username)
                }
                emit(AppResponse.success(it))
            }
        } catch (e: Exception) {
            emit(AppResponse.failure(e))
        }
    }
}