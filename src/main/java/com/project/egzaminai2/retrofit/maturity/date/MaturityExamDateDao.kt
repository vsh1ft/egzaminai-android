package com.project.egzaminai2.retrofit.maturity.date

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MaturityExamDateDao {

    @Query("SELECT * FROM maturityexamdate")
    fun getAll(): LiveData<List<MaturityExamDate>>

    @Insert
    fun insertAll(exams: List<MaturityExamDate>)
}
