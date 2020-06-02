package com.project.egzaminai2.retrofit.maturity.program

import com.project.egzaminai2.EgzaminaiApplication
import com.project.egzaminai2.retrofit.RetrofitFactory

class ExamProgramAdapter(
    private val webService: ExamProgramWebService =
        RetrofitFactory().create(EgzaminaiApplication.GlobalContext.context, ExamProgramWebService::class.java)
) {

    fun getPrograms(): List<MaturityProgram> {
        return webService.getPrograms().execute().body()!!
    }

}
