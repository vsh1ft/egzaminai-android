package com.project.egzaminai2.retrofit.maturity.date

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.project.egzaminai2.retrofit.maturity.ExamTypeConverter
import com.project.egzaminai2.retrofit.maturity.name.ExamName
import com.project.egzaminai2.retrofit.maturity.name.ExamNameConverter
import com.project.egzaminai2.retrofit.maturity.ExamType

@Entity
data class MaturityExamDate(

    @PrimaryKey val id: String,

    @TypeConverters(ExamNameConverter::class)
    val name: ExamName,

    @TypeConverters(ExamTypeConverter::class)
    val type: ExamType,

    var color: String,

    val dateTime: String
)
