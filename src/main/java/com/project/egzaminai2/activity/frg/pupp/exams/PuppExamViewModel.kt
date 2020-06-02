package com.project.egzaminai2.activity.frg.pupp.exams

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.project.egzaminai2.EgzaminaiApplication
import com.project.egzaminai2.retrofit.AppDatabase
import com.project.egzaminai2.retrofit.pupp.exam.PuppExam

class PuppExamViewModel(
    private var database: AppDatabase = EgzaminaiApplication.EgzaminaiDb.db
) : ViewModel() {

    private lateinit var programs: LiveData<List<PuppExam>>

    fun getExams(): LiveData<List<PuppExam>> {
        if (!::programs.isInitialized)
            programs = database.puppExamDao().getAll()
        return programs
    }

}
