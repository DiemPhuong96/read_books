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
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.thebook.R
import com.example.thebook.model.Book
import com.example.thebook.model.Section


class SectionAdapter(private val sections: ArrayList<Section>, private val books: ArrayList<Book>): RecyclerView.Adapter<SectionAdapter.SectionViewHolder>() {
    internal var onItemSectionClick: (book: Book) -> Unit = {}

    class SectionViewHolder(itemView: View): ViewHolder(itemView) {
       val sectionName: TextView = itemView.findViewById(R.id.txtSectionName)
        val recBooks: RecyclerView = itemView.findViewById(R.id.reBooks)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_book_section, parent,false)
        return SectionViewHolder(view)
    }

    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        val section = sections[position]
        holder.sectionName.text = section.sectionName
        val bookList = arrayListOf<Book>()
        books.forEach {
            if (section.sectionId == it.section?.sectionId) {
                bookList.add(it)
            }
        }
        val adapter = BookAdapter(bookList)
        Log.d("cccc", bookList.size.toString())
        holder.recBooks.layoutManager = LinearLayoutManager(holder.itemView.context, LinearLayoutManager.HORIZONTAL, false)
        holder.recBooks.adapter = adapter
        adapter.onItemClick = {
            onItemSectionClick.invoke(it)
        }
    }

    override fun getItemCount(): Int {
        Log.d("sectionSize", sections.size.toString())
        return sections.size
    }
}
