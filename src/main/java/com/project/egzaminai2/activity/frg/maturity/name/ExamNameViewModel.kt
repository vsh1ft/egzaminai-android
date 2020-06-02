package com.project.egzaminai2.activity.frg.maturity.name

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.project.egzaminai2.EgzaminaiApplication
import com.project.egzaminai2.retrofit.AppDatabase
import com.project.egzaminai2.retrofit.maturity.name.MaturityExam

class ExamNameViewModel(
    private var database: AppDatabase = EgzaminaiApplication.EgzaminaiDb.db
) : ViewModel() {

    private lateinit var names: LiveData<List<MaturityExam>>

    fun getNames(): LiveData<List<MaturityExam>> {
        if (!::names.isInitialized)
            names = database.maturityExamDao().getAll()
        return names
    }

}
