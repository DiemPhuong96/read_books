/*
 * Copyright (c) 2024.
 * Diem Phuong corporation
 */

package com.example.thebook.model

import com.google.gson.annotations.SerializedName

data class Book (

  @SerializedName("id") var id: String? = null,
  @SerializedName("title") var title: String? = null,
  @SerializedName("author") var author: String? = null,
  @SerializedName("photo") var photo: String? = null,
  @SerializedName("category") var category: Category? = null,
  @SerializedName("section") var section: Section? = Section(),
  @SerializedName("public_day") var publicDay: String? = null,

)
