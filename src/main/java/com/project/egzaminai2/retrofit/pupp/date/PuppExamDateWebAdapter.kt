package com.project.egzaminai2.retrofit.pupp.date

import com.project.egzaminai2.EgzaminaiApplication
import com.project.egzaminai2.retrofit.RetrofitFactory

class PuppExamDateWebAdapter(
    private val webService: PuppExamDateWebService =
        RetrofitFactory().create(EgzaminaiApplication.GlobalContext.context, PuppExamDateWebService::class.java)
) {

    fun getDates(): List<PuppExamDate> {
        return webService.getDates().execute().body()!!
    }

}
