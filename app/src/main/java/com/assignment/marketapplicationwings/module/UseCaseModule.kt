package com.assignment.marketapplicationwings.module

import com.assignment.api_service.DataStorePreference
import com.assignment.api_service.repository.CartRepository
import com.assignment.api_service.service.CartDAO
import com.assignment.api_service.service.LoginService
import com.assignment.api_service.service.ProductService
import com.assignment.api_service.service.RegisterService
import com.assignment.api_service.use_case.LoginUseCase
import com.assignment.api_service.use_case.ProductUseCase
import com.assignment.api_service.use_case.RegisterUseCase
import com.assignment.common.ext.AppExecutors
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {
    @Provides
    fun provideRegisterUseCase(registerService: RegisterService) = RegisterUseCase(registerService)

    @Provides
    fun provideLoginUseCase(loginService: LoginService, dataStorePreference: DataStorePreference) =
        LoginUseCase(loginService, dataStorePreference)

    @Provides
    fun provideProductUseCase(productService: ProductService) = ProductUseCase(productService)

    @Provides
    fun provideCartRepository(
        cartDAO: CartDAO,
        dataStorePreference: DataStorePreference,
        appExecutors: AppExecutors
    ) =
        CartRepository(cartDAO, dataStorePreference, appExecutors)
}