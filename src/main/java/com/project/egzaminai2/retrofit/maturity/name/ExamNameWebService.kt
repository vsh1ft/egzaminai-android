package com.project.egzaminai2.retrofit.maturity.name

import retrofit2.Call
import retrofit2.http.GET

interface ExamNameWebService {

    @GET("/exams")
    fun getExamNames(): Call<List<MaturityExam>>
}
