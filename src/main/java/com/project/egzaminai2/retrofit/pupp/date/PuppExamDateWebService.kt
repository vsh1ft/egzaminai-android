package com.project.egzaminai2.retrofit.pupp.date

import retrofit2.Call
import retrofit2.http.GET

interface PuppExamDateWebService {

    @GET("/pupp-dates")
    fun getDates(): Call<List<PuppExamDate>>
}
