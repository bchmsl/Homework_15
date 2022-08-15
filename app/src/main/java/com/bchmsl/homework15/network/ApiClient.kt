package com.bchmsl.homework15.network

import com.bchmsl.homework15.model.PostUser
import com.bchmsl.homework15.model.ResponseToken
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiClient {
    @POST("api/login")
    suspend fun loginUser(@Body user: PostUser): Response<ResponseToken>

    @POST("api/register")
    suspend fun registerUser(@Body user: PostUser): Response<ResponseToken>
}
