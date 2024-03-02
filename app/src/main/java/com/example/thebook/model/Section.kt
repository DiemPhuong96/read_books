/*
 * Copyright (c) 2024.
 * Diem Phuong corporation
 */

package com.example.thebook.model

import com.google.gson.annotations.SerializedName

data class Section (

  @SerializedName("sectionId"   ) var sectionId   : String? = null,
  @SerializedName("sectionName" ) var sectionName : String? = null

)