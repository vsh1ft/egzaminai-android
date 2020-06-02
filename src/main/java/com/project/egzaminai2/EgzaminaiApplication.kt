package com.project.egzaminai2

import android.app.Application
import androidx.room.Room
import com.project.egzaminai2.retrofit.AppDatabase

class EgzaminaiApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initializeGlobalContext(this)
    }

    private fun initializeGlobalContext(application: EgzaminaiApplication) {
        GlobalContext.context = application
        EgzaminaiDb.db = Room.databaseBuilder(application, AppDatabase::class.java, "database-name").build()
    }

    object EgzaminaiDb {
        lateinit var db: AppDatabase
    }

    object GlobalContext {
        lateinit var context: EgzaminaiApplication
    }
}
