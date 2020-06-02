package com.project.egzaminai2.retrofit.maturity.date

import retrofit2.Call
import retrofit2.http.GET

interface ExamDateWebService {

    @GET("/dates")
    fun getDates(): Call<List<MaturityExamDate>>
}
