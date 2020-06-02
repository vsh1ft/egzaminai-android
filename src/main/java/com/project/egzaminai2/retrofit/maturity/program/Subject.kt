package com.project.egzaminai2.retrofit.maturity.program

enum class Subject(val subject: String) {
    LITHUANIAN_LANGUAGE("Lietuvių kalba"),
    INFORMATICS("Informatika"),
    BIOLOGY("Biologija"),
    MATH("Matematika"),
    CHEMISTRY("Chemija"),
    PHYSICS("Fizika"),
    HISTORY("Istorija"),
    GEOGRAPHY("Geografija"),
    ENGLISH_LANGUAGE("Užsienio kalbos"),
    ART("Menai"),
    MUSIC("Muzikologija"),
    TECHNOLOGY("TECHNOLOGIJOS");

    override fun toString(): String {
        return subject
    }
}
