/*
 * Copyright (c) 2024.
 * Diem Phuong corporation
 */

package com.example.thebook.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.thebook.R

class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }
}
