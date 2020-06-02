package com.project.egzaminai2.retrofit.maturity

enum class ExamType(val type: String) {
    NATIONAL_LEVEL("Valstybinis"),
    SCHOOL_LEVEL("Mokyklinis");

    override fun toString(): String {
        return type
    }
}
