package com.project.egzaminai2.retrofit.maturity.credit

import retrofit2.Call
import retrofit2.http.GET

interface MaturityCourseCreditWebService {

    @GET("/credits")
    fun getCredits(): Call<List<MaturityCourseCredit>>
}
