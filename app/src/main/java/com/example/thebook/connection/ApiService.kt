/*
 * Copyright (c) 2024.
 * Diem Phuong corporation
 */

package com.example.thebook.connection

import com.example.thebook.model.Categories
import com.example.thebook.model.MyBooks
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("books")
    fun getMyBooks(): Call<MyBooks>
    @GET("categories")
    fun getCategories(): Call<Categories>

}
