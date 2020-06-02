package com.project.egzaminai2.activity.frg.pupp.calendar

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.project.egzaminai2.EgzaminaiApplication
import com.project.egzaminai2.retrofit.AppDatabase
import com.project.egzaminai2.retrofit.maturity.date.MaturityExamDate
import com.project.egzaminai2.retrofit.maturity.program.MaturityProgram
import com.project.egzaminai2.retrofit.pupp.date.PuppExamDate

class PuppDatesParentViewModel(
    private var database: AppDatabase = EgzaminaiApplication.EgzaminaiDb.db
) : ViewModel() {

    private lateinit var programs: LiveData<List<PuppExamDate>>

    fun getDates(): LiveData<List<PuppExamDate>> {
        if (!::programs.isInitialized)
            programs = database.puppExamDateDao().getAll()
        return programs
    }

}
