package com.example.problembookmobile.services

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ServiceBuilder {

    private const val URL = "http://192.168.1.39:9000/api/mobile/"

    private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val okHttp = OkHttpClient.Builder().addInterceptor(logger).build()

    private val builder = Retrofit.Builder().baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp)

    val retrofit = builder.build()

    fun <T> buildService(serviceType : Class<T>) : T{
        return retrofit.create(serviceType)
    }

}