package com.project.egzaminai2.retrofit.maturity.name

import androidx.room.TypeConverter

class ExamNameConverter {
    @TypeConverter
    fun toName(string: String): ExamName {
        return from(string)
    }

    @TypeConverter
    fun toTimestamp(name: ExamName): String? {
        return name.toString()
    }

    companion object {
        fun from(s: String): ExamName = ExamName.values().find { it.value == s }!!
    }
}
