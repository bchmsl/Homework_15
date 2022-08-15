package com.bchmsl.homework15.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

object RetrofitProvider {
    private const val BASE_URL = "https://reqres.in/"
    private val retrofit =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(MoshiConverterFactory.create())
            .build()

    fun getRetrofit(): ApiClient = retrofit.create(ApiClient::class.java)
}