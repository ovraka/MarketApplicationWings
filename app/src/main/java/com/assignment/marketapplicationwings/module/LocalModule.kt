package com.assignment.marketapplicationwings.module

import android.content.Context
import com.assignment.api_service.CartDatabase
import com.assignment.common.ext.AppExecutors
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = CartDatabase.getInstance(context)

    @Provides
    @Singleton
    fun provideCartDao(cartDatabase: CartDatabase) = cartDatabase.cartDao()

    @Provides
    @Singleton
    fun providesExecutor() = AppExecutors()
}