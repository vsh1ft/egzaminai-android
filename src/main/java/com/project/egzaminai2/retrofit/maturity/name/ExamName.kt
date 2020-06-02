package com.project.egzaminai2.retrofit.maturity.name

enum class ExamName( val value: String) {
    LITHUANIAN_LANGUAGE("Lietuvių kalba ir literatūra"),
    INFORMATICS("Informacinės technologijos"),
    BIOLOGY("Biologija"),
    MATH("Matematika"),
    CHEMISTRY("Chemija"),
    PHYSICS("Fizika"),
    HISTORY("Istorija"),
    ENGLISH_LANGUAGE("Užsienio kalba (anglų)"),
    FRENCH_LANGUAGE("Užsienio kalba (prancūzų)"),
    RUSSIAN_LANGUAGE("Užsienio kalba (rusų)"),
    GERMAN_LANGUAGE("Užsienio kalba (vokiečių)"),
    GEOGRAPHY("Geografija"),
    NATIVE_BELARUSIAN_LANGUAGE("Gimtoji kalba (baltarusų)"),
    NATIVE_POLISH_LANGUAGE("Gimtoji kalba (lenkų)"),
    NATIVE_RUSSIAN_LANGUAGE("Gimtoji kalba (rusų)"),
    MUSIC_CREATIVITY_TASK("Muzikologija (kūrybinė užduotis)"),
    MUSIC_NOTES("Muzikologija (muzikos diktanto natos)"),
    MUSIC_HISTORY("Muzikos istorijos ir teorijos testas"),
    MUSICAL_THINKING("Muzikinio mąstymo testas");

    override fun toString(): String {
        return value
    }
}
