package com.project.egzaminai2.retrofit.maturity.date

import com.project.egzaminai2.EgzaminaiApplication
import com.project.egzaminai2.retrofit.RetrofitFactory

class DatesNameAdapter(
    private val webService: ExamDateWebService =
        RetrofitFactory().create(EgzaminaiApplication.GlobalContext.context, ExamDateWebService::class.java)
) {

    fun getDates(): List<MaturityExamDate> {
        return webService.getDates().execute().body()!!
    }

}
