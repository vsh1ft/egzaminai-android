package com.project.egzaminai2.retrofit.maturity.program

import androidx.room.TypeConverter

class SubjectConverter {
    @TypeConverter
    fun toName(string: String): Subject {
        return from(string)
    }

    @TypeConverter
    fun toTimestamp(name: Subject): String? {
        return name.toString()
    }

    companion object {
        fun from(s: String): Subject = Subject.values().find { it.subject == s }!!
    }
}
