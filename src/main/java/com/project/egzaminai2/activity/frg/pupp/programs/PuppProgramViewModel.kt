package com.project.egzaminai2.activity.frg.pupp.programs

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.project.egzaminai2.EgzaminaiApplication
import com.project.egzaminai2.retrofit.AppDatabase
import com.project.egzaminai2.retrofit.maturity.program.MaturityProgram
import com.project.egzaminai2.retrofit.pupp.program.PuppProgram

class PuppProgramViewModel(
    private var database: AppDatabase = EgzaminaiApplication.EgzaminaiDb.db
) : ViewModel() {

    private lateinit var programs: LiveData<List<PuppProgram>>

    fun getPrograms(): LiveData<List<PuppProgram>> {
        if (!::programs.isInitialized)
            programs = database.puppProgramDao().getAll()
        return programs
    }

}
