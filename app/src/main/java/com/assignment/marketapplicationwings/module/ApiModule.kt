package com.assignment.marketapplicationwings.module

import android.app.Application
import android.content.Context
import com.assignment.api_service.CartDatabase
import com.assignment.api_service.RetrofitClient
import com.assignment.api_service.service.CartDAO
import com.assignment.api_service.service.LoginService
import com.assignment.api_service.service.ProductService
import com.assignment.api_service.service.RegisterService
import com.assignment.common.ext.AppExecutors
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {
    @Provides
    @Singleton
    fun provideRetrofit(application: Application) = RetrofitClient.getClient(application)

    @Provides
    @Singleton
    fun provideRegisterService(retrofit: Retrofit) = retrofit.create(RegisterService::class.java)


    @Provides
    @Singleton
    fun provideLoginService(retrofit: Retrofit) = retrofit.create(LoginService::class.java)

    @Provides
    @Singleton
    fun provideProductService(retrofit: Retrofit) = retrofit.create(ProductService::class.java)

}