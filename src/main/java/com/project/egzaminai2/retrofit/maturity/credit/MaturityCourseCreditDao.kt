package com.project.egzaminai2.retrofit.maturity.credit

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MaturityCourseCreditDao {

    @Query("SELECT * FROM maturitycoursecredit")
    fun getAll(): LiveData<List<MaturityCourseCredit>>

    @Query("SELECT name FROM maturitycoursecredit")
    fun findAllNames(): LiveData<List<String>>

    @Insert
    fun insertAll(exams: List<MaturityCourseCredit>)
}
