package com.project.egzaminai2.retrofit

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.project.egzaminai2.retrofit.maturity.name.ExamNameConverter
import com.project.egzaminai2.retrofit.maturity.ExamTypeConverter
import com.project.egzaminai2.retrofit.maturity.credit.MaturityCourseCredit
import com.project.egzaminai2.retrofit.maturity.credit.MaturityCourseCreditDao
import com.project.egzaminai2.retrofit.maturity.date.LocalDateTimeConverter
import com.project.egzaminai2.retrofit.maturity.date.MaturityExamDate
import com.project.egzaminai2.retrofit.maturity.date.MaturityExamDateDao
import com.project.egzaminai2.retrofit.maturity.program.SubjectConverter
import com.project.egzaminai2.retrofit.maturity.name.MaturityExam
import com.project.egzaminai2.retrofit.maturity.name.MaturityExamDao
import com.project.egzaminai2.retrofit.maturity.program.MaturityProgram
import com.project.egzaminai2.retrofit.maturity.program.MaturityProgramDao
import com.project.egzaminai2.retrofit.pupp.date.PuppExamDate
import com.project.egzaminai2.retrofit.pupp.date.PuppExamDateDao
import com.project.egzaminai2.retrofit.pupp.exam.PuppExam
import com.project.egzaminai2.retrofit.pupp.exam.PuppExamDao
import com.project.egzaminai2.retrofit.pupp.exam.PuppExamNameConverter
import com.project.egzaminai2.retrofit.pupp.program.PuppProgram
import com.project.egzaminai2.retrofit.pupp.program.PuppProgramDao

@Database(
    entities = arrayOf(
        MaturityExam::class, MaturityProgram::class, MaturityCourseCredit::class,
        PuppExam::class, PuppProgram::class, MaturityExamDate::class, PuppExamDate::class
    ), version = 1
)
@TypeConverters(
    ExamNameConverter::class,
    ExamTypeConverter::class,
    SubjectConverter::class,
    PuppExamNameConverter::class,
    LocalDateTimeConverter::class
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun maturityExamDao(): MaturityExamDao
    abstract fun maturityProgramDao(): MaturityProgramDao
    abstract fun courseCreditsDao(): MaturityCourseCreditDao
    abstract fun examDateDao(): MaturityExamDateDao
    abstract fun puppExamDao(): PuppExamDao
    abstract fun puppExamDateDao(): PuppExamDateDao
    abstract fun puppProgramDao(): PuppProgramDao
}
