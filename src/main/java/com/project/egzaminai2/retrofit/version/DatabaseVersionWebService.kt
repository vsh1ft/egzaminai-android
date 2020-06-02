package com.project.egzaminai2.retrofit.version

import retrofit2.Call
import retrofit2.http.GET

interface DatabaseVersionWebService {

    @GET("/database-version")
    fun getVersion(): Call<String>
}
