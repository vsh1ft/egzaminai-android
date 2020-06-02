package com.project.egzaminai2.activity.frg.maturity.year

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.project.egzaminai2.EgzaminaiApplication
import com.project.egzaminai2.retrofit.AppDatabase
import com.project.egzaminai2.retrofit.maturity.name.ExamName

class ExamYearViewModel(
    private var database: AppDatabase = EgzaminaiApplication.EgzaminaiDb.db
) : ViewModel() {

    private lateinit var exam: LiveData<ExamName>

    fun getName(id: String): LiveData<ExamName> {
        if (!::exam.isInitialized)
            exam = database.maturityExamDao().findNameById(id)
        return exam
    }

}
