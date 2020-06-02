package com.project.egzaminai2.activity.frg.pupp.calendar

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.project.egzaminai2.EgzaminaiApplication
import com.project.egzaminai2.retrofit.AppDatabase
import com.project.egzaminai2.retrofit.maturity.date.MaturityExamDate
import com.project.egzaminai2.retrofit.maturity.program.MaturityProgram

class PuppDatesViewModel(
    private var database: AppDatabase = EgzaminaiApplication.EgzaminaiDb.db
) : ViewModel() {

    private lateinit var programs: LiveData<List<MaturityExamDate>>

    fun getDates(): LiveData<List<MaturityExamDate>> {
        if (!::programs.isInitialized)
            programs = database.examDateDao().getAll()
        return programs
    }

}
