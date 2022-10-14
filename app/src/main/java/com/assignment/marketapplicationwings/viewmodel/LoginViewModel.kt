package com.assignment.marketapplicationwings.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.assignment.api_service.use_case.LoginUseCase
import com.assignment.common.ext.AppResponse
import com.assignment.common.ext.BaseViewModel
import com.assignment.common.request.LoginRequest
import com.assignment.common.response.login.LoginResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    application: Application, val loginUseCase: LoginUseCase
) : BaseViewModel(application) {
    val loginState = MutableLiveData<AppResponse<LoginResponse>>()

    fun getLoginData(username: String, password: String) {
        val loginData = LoginRequest(username, password)

        viewModelScope.launch {
            loginUseCase(loginData).collect {
                loginState.postValue(it)
            }
        }
    }
}