package com.project.egzaminai2.retrofit.maturity

import androidx.room.TypeConverter

class ExamTypeConverter {
    @TypeConverter
    fun toType(name: String): ExamType {
        return from(name)
    }

    @TypeConverter
    fun toString(type: ExamType): String? {
        return type.toString()
    }

    companion object {
        fun from(s: String): ExamType = ExamType.values().find { it.type == s }!!
    }
}
