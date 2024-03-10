/*
 * Copyright (c) 2024.
 * Diem Phuong corporation
 */

package com.example.thebook.connection

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {
    private const val BASE_URL = "https://0903efc0-2e6e-422c-9b0b-ec3044ce388c.mock.pstmn.io/"
    val retrofit: Retrofit by lazy {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
    }
}
object ApiClient{
    val apiService: ApiService by lazy {
        RetrofitClient.retrofit.create(ApiService::class.java)
    }
}