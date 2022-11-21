package com.example.nycschools.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nycschools.data.NYCSchoolsResponse
import com.example.nycschools.data.SchoolDetails
import com.example.nycschools.repository.SchoolRepository
import com.example.nycschools.util.Utils.isInternetAvailable

class SearchViewModel(private val context: Context) : ViewModel() {

    private var listData = MutableLiveData<List<NYCSchoolsResponse>>()
    private var schoolDetails = MutableLiveData<List<SchoolDetails>>()
    private val schoolRepository : SchoolRepository by lazy { SchoolRepository }

    fun getSchoolsList() : MutableLiveData<List<NYCSchoolsResponse>>{
        if(context.isInternetAvailable()) {
            listData = schoolRepository.getNYCSchoolsList(context)
        }
        return listData
    }

    fun getSchoolSATResults(dbn: String) : MutableLiveData<List<SchoolDetails>>{
        if(context.isInternetAvailable()) {
            schoolDetails = schoolRepository.getSATResults(context, dbn)
        }
        return schoolDetails
    }

}