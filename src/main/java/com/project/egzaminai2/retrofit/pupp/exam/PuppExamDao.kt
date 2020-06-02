package com.project.egzaminai2.retrofit.pupp.exam

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PuppExamDao {

    @Query("SELECT * FROM puppexam")
    fun getAll(): LiveData<List<PuppExam>>

    @Query("SELECT name FROM puppexam")
    fun findAllNames(): LiveData<List<String>>

    @Insert
    fun insertAll(exams: List<PuppExam>)
}
