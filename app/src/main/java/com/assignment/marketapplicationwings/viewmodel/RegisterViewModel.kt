package com.assignment.marketapplicationwings.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.assignment.api_service.use_case.RegisterUseCase
import com.assignment.common.ext.AppResponse
import com.assignment.common.ext.BaseViewModel
import com.assignment.common.request.RegisterRequest
import com.assignment.common.response.register.RegisterResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    application: Application,
    val registerUseCase: RegisterUseCase
) : BaseViewModel(application) {
    val registerState = MutableLiveData<AppResponse<RegisterResponse>>()

    fun getRegisterData(username: String, password: String) {
        val registerData = RegisterRequest(username, password)

        viewModelScope.launch {
            registerUseCase(registerData).collect {
                registerState.postValue(it)
            }
        }
    }
}