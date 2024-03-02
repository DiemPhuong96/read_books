/*
 * Copyright (c) 2024.
 * Diem Phuong corporation
 */

package com.example.thebook.model

import com.google.gson.annotations.SerializedName

data class Categories (

  @SerializedName("category" ) var catelory : ArrayList<Category> = arrayListOf()

)
