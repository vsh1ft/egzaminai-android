package com.project.egzaminai2.activity.frg.maturity.program

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.project.egzaminai2.EgzaminaiApplication
import com.project.egzaminai2.retrofit.AppDatabase
import com.project.egzaminai2.retrofit.maturity.program.MaturityProgram

class ExamProgramViewModel(
    private var database: AppDatabase = EgzaminaiApplication.EgzaminaiDb.db
) : ViewModel() {

    private lateinit var programs: LiveData<List<MaturityProgram>>

    fun getPrograms(): LiveData<List<MaturityProgram>> {
        if (!::programs.isInitialized)
            programs = database.maturityProgramDao().getAll()
        return programs
    }

}
