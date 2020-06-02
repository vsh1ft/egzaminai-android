package com.project.egzaminai2.retrofit.pupp.program

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PuppProgram(

    @PrimaryKey var id: String,

    var name: String,

    var programUrl: String
)
