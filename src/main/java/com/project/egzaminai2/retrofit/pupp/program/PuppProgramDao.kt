package com.project.egzaminai2.retrofit.pupp.program

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PuppProgramDao {

    @Query("SELECT * FROM puppprogram")
    fun getAll(): LiveData<List<PuppProgram>>

    @Query("SELECT name FROM puppprogram")
    fun findAllNames(): LiveData<List<String>>

    @Insert
    fun insertAll(programs: List<PuppProgram>)
}
