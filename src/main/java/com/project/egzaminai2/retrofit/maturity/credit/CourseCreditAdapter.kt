package com.project.egzaminai2.retrofit.maturity.credit

import com.project.egzaminai2.EgzaminaiApplication
import com.project.egzaminai2.retrofit.RetrofitFactory

class CourseCreditAdapter(
    private val webService: MaturityCourseCreditWebService =
        RetrofitFactory().create(EgzaminaiApplication.GlobalContext.context, MaturityCourseCreditWebService::class.java)
) {

    fun getCredits(): List<MaturityCourseCredit> {
        return webService.getCredits().execute().body()!!
    }

}
