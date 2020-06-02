package com.project.egzaminai2.retrofit.pupp.date

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.project.egzaminai2.retrofit.pupp.exam.PuppExamName
import com.project.egzaminai2.retrofit.pupp.exam.PuppExamNameConverter

@Entity
data class PuppExamDate(

    @PrimaryKey var id: String,

    @TypeConverters(PuppExamNameConverter::class)
    var name: PuppExamName,

    var color: String,

    var dateTime: String
)
