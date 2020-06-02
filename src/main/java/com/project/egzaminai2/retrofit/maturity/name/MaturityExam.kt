package com.project.egzaminai2.retrofit.maturity.name

import androidx.room.*
import com.project.egzaminai2.retrofit.maturity.ExamTypeConverter
import com.project.egzaminai2.retrofit.maturity.ExamType

@Entity
data class MaturityExam(

    @PrimaryKey var id: String,

    @TypeConverters(ExamNameConverter::class)
    var name: ExamName,

    var year: Int,

    @TypeConverters(ExamTypeConverter::class)
    var type: ExamType,

    var examUrl: String,

    var answerUrl: String
)
