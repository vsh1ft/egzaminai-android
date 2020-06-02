package com.project.egzaminai2.retrofit.maturity.program

import retrofit2.Call
import retrofit2.http.GET

interface ExamProgramWebService {

    @GET("/programs")
    fun getPrograms(): Call<List<MaturityProgram>>
}
