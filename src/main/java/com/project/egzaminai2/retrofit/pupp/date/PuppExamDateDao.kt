package com.project.egzaminai2.retrofit.pupp.date

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PuppExamDateDao {

    @Query("SELECT * FROM puppexamdate")
    fun getAll(): LiveData<List<PuppExamDate>>

    @Insert
    fun insertAll(exams: List<PuppExamDate>)
}
