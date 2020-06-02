package com.project.egzaminai2.retrofit.maturity.program

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MaturityProgramDao {

    @Query("SELECT * FROM maturityprogram")
    fun getAll(): LiveData<List<MaturityProgram>>

    @Query("SELECT name FROM maturityprogram")
    fun findAllNames(): LiveData<List<String>>

    @Insert
    fun insertAll(exams: List<MaturityProgram>)
}
