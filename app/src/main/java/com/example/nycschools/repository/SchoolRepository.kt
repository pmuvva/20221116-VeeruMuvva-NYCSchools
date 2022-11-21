package com.example.nycschools.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.nycschools.remote.ApiClient
import com.example.nycschools.data.NYCSchoolsResponse
import com.example.nycschools.data.SchoolDetails
import com.example.nycschools.util.Utils.hideProgressBar
import com.example.nycschools.util.Utils.showProgressBar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object SchoolRepository {
    fun getNYCSchoolsList(context: Context) : MutableLiveData<List<NYCSchoolsResponse>> {

        val mutableLiveData = MutableLiveData<List<NYCSchoolsResponse>>()

        context.showProgressBar()

        ApiClient.apiService.getNYCSchools().enqueue(object : Callback<List<NYCSchoolsResponse>> {
            override fun onFailure(call: Call<List<NYCSchoolsResponse>>, t: Throwable) {
                hideProgressBar()
                Log.e("error", t.localizedMessage)
            }

            override fun onResponse(
                call: Call<List<NYCSchoolsResponse>>,
                response: Response<List<NYCSchoolsResponse>>
            ) {
                hideProgressBar()
                val usersResponse = response.body()
                usersResponse?.let { mutableLiveData.value = it }
            }

        })

        return mutableLiveData
    }

    fun getSATResults(context: Context, schoolDbn: String) : MutableLiveData<List<SchoolDetails>> {

        val mutableLiveData = MutableLiveData<List<SchoolDetails>>()

        context.showProgressBar()

        ApiClient.apiService.getSATResults(schoolDbn).enqueue(object : Callback<List<SchoolDetails>> {
            override fun onFailure(call: Call<List<SchoolDetails>>, t: Throwable) {
                hideProgressBar()
                Log.e("error", t.localizedMessage)
            }

            override fun onResponse(
                call: Call<List<SchoolDetails>>,
                response: Response<List<SchoolDetails>>
            ) {
                hideProgressBar()
                val usersResponse = response.body()
                usersResponse?.let { mutableLiveData.value = it }
            }

        })

        return mutableLiveData
    }
}