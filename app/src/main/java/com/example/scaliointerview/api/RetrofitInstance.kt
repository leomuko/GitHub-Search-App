package com.example.scaliointerview.api

import com.example.scaliointerview.utilities.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {

    var okHttpClient = OkHttpClient().newBuilder()
        .connectTimeout(240, TimeUnit.SECONDS)
        .readTimeout(120, TimeUnit.SECONDS)
        .writeTimeout(120, TimeUnit.SECONDS)
        .addInterceptor( HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC).setLevel
            (HttpLoggingInterceptor.Level.BODY).setLevel(HttpLoggingInterceptor.Level.HEADERS))
        .build()

    private val retrofit by lazy {
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiInterface : ApiInterface by lazy {
        retrofit.create(ApiInterface::class.java)
    }
}