package com.project.egzaminai2.retrofit.maturity.program

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity
data class MaturityProgram(

    @PrimaryKey var id: String,

    var name: String,

    @TypeConverters(SubjectConverter::class)
    var subject: Subject,

    var programUrl: String
)
