package com.project.egzaminai2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.project.egzaminai2.EgzaminaiApplication.EgzaminaiDb.db
import com.project.egzaminai2.Utils.MyUtils
import com.project.egzaminai2.Utils.MyUtils.isDatabaseVersionEqual
import com.project.egzaminai2.Utils.MyUtils.storeDatabaseVersion
import com.project.egzaminai2.retrofit.maturity.credit.CourseCreditAdapter
import com.project.egzaminai2.retrofit.maturity.date.DatesNameAdapter
import com.project.egzaminai2.retrofit.maturity.name.ExamNameAdapter
import com.project.egzaminai2.retrofit.maturity.program.ExamProgramAdapter
import com.project.egzaminai2.retrofit.pupp.date.PuppExamDateWebAdapter
import com.project.egzaminai2.retrofit.pupp.exam.PuppExamWebAdapter
import com.project.egzaminai2.retrofit.pupp.program.PuppProgramWebAdapter
import com.project.egzaminai2.retrofit.version.DatabaseVersionWebAdapter
import kotlinx.coroutines.*
import java.util.*


class Splash: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)
        if (MyUtils.isConnection(this@Splash)) {
            Timer().apply {
                schedule(object: TimerTask() {
                    override fun run() {
                        val intent = Intent(this@Splash, MainActivity::class.java)
                        startActivity(intent)
                        GlobalScope.launch(Dispatchers.Default) {
                            withContext(coroutineContext) {
                                if (!isDatabaseVersionEqual(DatabaseVersionWebAdapter().getVersion(), this@Splash)) {
                                    storeDatabaseVersion(DatabaseVersionWebAdapter().getVersion(), this@Splash)
                                    db.clearAllTables()
                                    db.maturityExamDao().insertAll(ExamNameAdapter().getExams())
                                    db.maturityProgramDao().insertAll(ExamProgramAdapter().getPrograms())
                                    db.courseCreditsDao().insertAll(CourseCreditAdapter().getCredits())
                                    db.puppExamDao().insertAll(PuppExamWebAdapter().getExams())
                                    db.puppProgramDao().insertAll(PuppProgramWebAdapter().getPrograms())
                                    db.examDateDao().insertAll(DatesNameAdapter().getDates())
                                    db.puppExamDateDao().insertAll(PuppExamDateWebAdapter().getDates())
                                }
                            }
                        }
                        finish()
                    }
                }, 1000)
            }
        } else
            MyUtils.showInternetWarning(this)
    }

}
