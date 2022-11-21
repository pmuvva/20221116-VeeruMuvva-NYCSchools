package com.example.nycschools.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nycschools.adapter.SchoolsListAdapter
import com.example.nycschools.databinding.ActivityMainBinding
import com.example.nycschools.remote.SearchViewModelFactory
import com.example.nycschools.viewmodel.SearchViewModel


class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding
    private val adapter = SchoolsListAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSchoolsList()
    }

    private fun setSchoolsList(){

        val searchViewModel = ViewModelProviders.of(this,
            SearchViewModelFactory(this@MainActivity)
        )[SearchViewModel::class.java]
        binding.rvSchools.layoutManager = LinearLayoutManager(this@MainActivity)
        binding.rvSchools.adapter = adapter

        searchViewModel.getSchoolsList().observe(this) { response ->
            response?.let {
                binding.tvNoSchools.visibility = View.GONE
                adapter.setDataList(response)
                adapter.notifyDataSetChanged()
                Log.i("response", response.toString())
            }
        }

    }
}