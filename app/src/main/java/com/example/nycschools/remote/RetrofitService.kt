package com.example.nycschools.remote

import com.example.nycschools.data.SchoolDetails
import com.example.nycschools.data.NYCSchoolsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("s3k6-pzi2.json")
    fun getNYCSchools(): Call<List<NYCSchoolsResponse>>

    @GET("f9bf-2cp4.json")
    fun getSATResults(@Query("dbn") tags: String): Call<List<SchoolDetails>>
}