/*
 * Copyright (c) 2024.
 * Diem Phuong corporation
 */

package com.example.thebook.view.home

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.thebook.databinding.ItemBookBinding
import com.example.thebook.model.Book


class BookAdapter(private val books: ArrayList<Book>): RecyclerView.Adapter<BookAdapter.SectionViewHolder>() {
    private lateinit var binding: ItemBookBinding
    internal var onItemClick: (book: Book) -> Unit = {}
    inner class SectionViewHolder(binding: ItemBookBinding): ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder {
        binding = ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val width = (parent.measuredWidth *0.3)
        binding.root.layoutParams.width = width.toInt()
        binding.root.layoutParams.height = (width*1.7).toInt()
        return SectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        val book = books[position]
        binding.txtBookName.text = book.title
        binding.txtAuthor.text = book.author
        Glide.with(binding.root.context).load(book.photo).into(binding.imgBookCover)
        binding.root.setOnClickListener {
            onItemClick.invoke(book)
        }
    }

    override fun getItemCount(): Int {
        return books.size
    }
}
