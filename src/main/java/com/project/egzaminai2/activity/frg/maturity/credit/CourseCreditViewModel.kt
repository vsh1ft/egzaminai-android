package com.project.egzaminai2.activity.frg.maturity.credit

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.project.egzaminai2.EgzaminaiApplication
import com.project.egzaminai2.retrofit.AppDatabase
import com.project.egzaminai2.retrofit.maturity.credit.MaturityCourseCredit

class CourseCreditViewModel(
    private var database: AppDatabase = EgzaminaiApplication.EgzaminaiDb.db
) : ViewModel() {

    private lateinit var programs: LiveData<List<MaturityCourseCredit>>

    fun getCredits(): LiveData<List<MaturityCourseCredit>> {
        if (!::programs.isInitialized)
            programs = database.courseCreditsDao().getAll()
        return programs
    }

}
