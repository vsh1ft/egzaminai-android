package com.project.egzaminai2.retrofit.maturity.credit

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MaturityCourseCredit(

    @PrimaryKey var id: String,

    var name: String,

    var year: Int,

    var creditUrl: String
)
