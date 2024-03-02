/*
 * Copyright (c) 2024.
 * Diem Phuong corporation
 */

package com.example.thebook.model

import com.google.gson.annotations.SerializedName

data class MyBooks (

  @SerializedName("myBooks" ) var myBooks : MyBook? = MyBook()

)