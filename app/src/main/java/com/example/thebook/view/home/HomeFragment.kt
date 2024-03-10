/*
 * Copyright (c) 2023-2024.
 * Diem Phuong corporation
 */

package com.example.thebook.view.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thebook.R
import com.example.thebook.connection.ApiClient
import com.example.thebook.databinding.FragmentHomeBinding
import com.example.thebook.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.txtWelcome.text = getString(R.string.welcome, "Phuong")
        val call = ApiClient.apiService.getMyBooks()
        call.enqueue(object : Callback<MyBooks> {
            override fun onResponse(call: Call<MyBooks>, response: Response<MyBooks>) {
                if(response.isSuccessful) {
                    val data = response.body()
                    Log.d("phuong success", "${data?.myBooks?.book}")
                    data?.myBooks?.book?.let { initSectionAdapter(it) }
                } else {
                    Log.d("phuong fail", "${response.body()}")
                }
            }

            override fun onFailure(call: Call<MyBooks>, t: Throwable) {
                Log.d("phuong on fail", "${t.message}")
            }

        })
        val spinner: Spinner = binding.spinType
        val adapter = ArrayAdapter.createFromResource(requireContext(), R.array.types_of_book, R.layout.book_type_spinner_item)
        adapter.setDropDownViewResource(R.layout.book_type_spinner_dropdown_item)
        spinner.adapter = adapter

        val callCategories = ApiClient.apiService.getCategories()
        callCategories.enqueue(object : Callback<Categories> {
            override fun onResponse(call: Call<Categories>, response: Response<Categories>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    data?.let { addCategory(it) }
                } else {
                    Log.d("phuong fail category", "${response.body()}")
                    val data = Categories()
                    data.catelory = arrayListOf(Category(id = "1", name = "Novel"), Category(id = "2", name = "horror"), Category("3", "Funny"))
                    addCategory(data)
                }
            }

            override fun onFailure(call: Call<Categories>, t: Throwable) {
                Log.d("phuong on fail category", "${t.message}")
                val data = Categories()
                data.catelory = arrayListOf(Category(id = "1", name = "Novel"), Category(id = "2", name = "horror"), Category("3", "Funny"))
                addCategory(data)
            }

        })

    }

    private fun initSectionAdapter(books : ArrayList<Book>) {
        val listSection = arrayListOf<Section>()
        books.forEach {
            val section = it.section
            if (section !in listSection) {
                section?.let { it1 -> listSection.add(it1) }
            }
        }
        Log.d("sectionlist", listSection.size.toString())
        val adapter = SectionAdapter(listSection, books)
        binding.recSections.layoutManager = LinearLayoutManager(requireContext())
        binding.recSections.adapter = adapter
        adapter.onItemSectionClick = {
            findNavController().navigate(HomeFragmentDirections.actionFromHomeFragmentToDetailFragment(it))
        }
    }
    private fun addCategory(categories: Categories) {
        //remove view
        binding.flexTag.removeAllViews()
        // add tag
        for (category in categories.catelory) {
            val tag = View.inflate(requireContext(), R.layout.item_category, null)
            tag.findViewById<TextView>(R.id.txt_tag).text = category.name
            binding.flexTag.addView(tag)
        }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}