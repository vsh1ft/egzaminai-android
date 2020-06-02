package com.project.egzaminai2.retrofit.pupp.exam

import retrofit2.Call
import retrofit2.http.GET

interface PuppExamWebService {

    @GET("/pupp-exams")
    fun getPuppExams(): Call<List<PuppExam>>
}
