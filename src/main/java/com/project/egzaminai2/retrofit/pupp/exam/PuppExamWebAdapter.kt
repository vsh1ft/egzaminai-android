package com.project.egzaminai2.retrofit.pupp.exam

import com.project.egzaminai2.EgzaminaiApplication
import com.project.egzaminai2.retrofit.RetrofitFactory

class PuppExamWebAdapter(
    private val webService: PuppExamWebService =
        RetrofitFactory().create(EgzaminaiApplication.GlobalContext.context, PuppExamWebService::class.java)
) {

    fun getExams(): List<PuppExam> {
        return webService.getPuppExams().execute().body()!!
    }

}
