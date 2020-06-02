package com.project.egzaminai2.retrofit.maturity.name

import com.project.egzaminai2.EgzaminaiApplication
import com.project.egzaminai2.retrofit.RetrofitFactory

class ExamNameAdapter(
    private val webService: ExamNameWebService =
        RetrofitFactory().create(EgzaminaiApplication.GlobalContext.context, ExamNameWebService::class.java)
) {

    fun getExams(): List<MaturityExam> {
        return webService.getExamNames().execute().body()!!
    }

}
