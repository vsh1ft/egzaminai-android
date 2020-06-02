package com.project.egzaminai2.retrofit.pupp.program

import com.project.egzaminai2.EgzaminaiApplication
import com.project.egzaminai2.retrofit.RetrofitFactory

class PuppProgramWebAdapter(
    private val webService: PuppProgramWebService =
        RetrofitFactory().create(EgzaminaiApplication.GlobalContext.context, PuppProgramWebService::class.java)
) {

    fun getPrograms(): List<PuppProgram> {
        return webService.getPuppPrograms().execute().body()!!
    }

}
