package com.project.egzaminai2.retrofit.maturity.name

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MaturityExamDao {

    @Query("SELECT * FROM maturityexam")
    fun getAll(): LiveData<List<MaturityExam>>

    @Query("SELECT name FROM maturityexam")
    fun findAllNames(): LiveData<List<String>>

    @Query("SELECT name FROM maturityexam WHERE id LIKE :id")
    fun findNameById(id: String): LiveData<ExamName>

    @Query("SELECT year FROM maturityexam WHERE name LIKE :name")
    fun findYearByName(name: String): LiveData<List<Int>>

    @Insert
    fun insertAll(exams: List<MaturityExam>)
}
