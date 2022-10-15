package com.assignment.api_service.use_case

import com.assignment.api_service.DataStorePreference
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InitUserDataUseCase @Inject constructor(
    val dataStorePreference: DataStorePreference
) {
    operator fun invoke() = flow {
        val userData = dataStorePreference.getUsername() to
                dataStorePreference.getToken()
        emit(userData)
    }
}