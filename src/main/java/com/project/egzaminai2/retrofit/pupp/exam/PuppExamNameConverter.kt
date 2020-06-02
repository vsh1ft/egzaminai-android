package com.project.egzaminai2.retrofit.pupp.exam

import androidx.room.TypeConverter

class PuppExamNameConverter {
    @TypeConverter
    fun toName(string: String): PuppExamName {
        return from(string)
    }

    @TypeConverter
    fun toTimestamp(name: PuppExamName): String? {
        return name.toString()
    }

    companion object {
        fun from(s: String): PuppExamName = PuppExamName.values().find { it.examName == s }!!
    }
}
