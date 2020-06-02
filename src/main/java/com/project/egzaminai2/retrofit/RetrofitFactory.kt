package com.project.egzaminai2.retrofit

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.google.gson.GsonBuilder
import com.project.egzaminai2.EgzaminaiApplication
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit.SECONDS

class RetrofitFactory(
    private val builder: Builder = Builder(),
    private val okHttpClient: OkHttpClient = OkHttpClient()
) {

    internal fun <S> create(context: Context, serviceClass: Class<S>): S {
        val okHttpClient =
            okHttpClient.newBuilder().apply {
                readTimeout(10, SECONDS)
                writeTimeout(10, SECONDS)
                connectTimeout(10, SECONDS)
            }
        return build(okHttpClient).create(serviceClass)
    }

    private fun build(okHttpClientBuilder: OkHttpClient.Builder) =
        builder.apply {
            baseUrl("http://10.0.2.2:8081/")
            client(okHttpClientBuilder.build())
            addConverterFactory(GsonConverterFactory.create((GsonBuilder().setLenient().create())))
        }.build()




}
