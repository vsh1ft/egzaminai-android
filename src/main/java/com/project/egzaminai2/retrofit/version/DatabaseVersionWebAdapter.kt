package com.project.egzaminai2.retrofit.version

import com.project.egzaminai2.EgzaminaiApplication
import com.project.egzaminai2.retrofit.RetrofitFactory

class DatabaseVersionWebAdapter(
    private val webService: DatabaseVersionWebService =
        RetrofitFactory().create(EgzaminaiApplication.GlobalContext.context, DatabaseVersionWebService::class.java)
) {

    fun getVersion(): String {
        return webService.getVersion().execute().body()!!
    }

}
