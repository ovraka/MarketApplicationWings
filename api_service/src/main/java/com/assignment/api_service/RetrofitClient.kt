package com.assignment.api_service

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.ashokvarma.gander.Gander
import com.ashokvarma.gander.GanderInterceptor
import com.ashokvarma.gander.imdb.GanderIMDB
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.KeyStore
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.*
import javax.security.cert.CertificateException

object RetrofitClient {
    fun getClient(context: Context): Retrofit {
        Gander.setGanderStorage(GanderIMDB.getInstance())
        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor {
                Log.e("Http Log", it)
            }).addInterceptor(
                GanderInterceptor(context)
                    .showNotification(true)).build()
        return Retrofit.Builder()
            .client(client)
            .baseUrl("http://10.237.74.249:3000/marketApplication/api/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
    }
}