package com.project.egzaminai2.retrofit.pupp.exam

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PuppExam(

    @PrimaryKey var id: String,

    var name: PuppExamName,

    var year: Int,

    var examUrl: String
)
