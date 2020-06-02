package com.project.egzaminai2.retrofit.pupp.program

import retrofit2.Call
import retrofit2.http.GET

interface PuppProgramWebService {

    @GET("/pupp-programs")
    fun getPuppPrograms(): Call<List<PuppProgram>>
}
