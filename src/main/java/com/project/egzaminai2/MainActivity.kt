package com.project.egzaminai2

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.*
import com.project.egzaminai2.Utils.AppRater
import kotlinx.android.synthetic.main.home_activity.*

class MainActivity: AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val settings = getSharedPreferences("MyPrefsFile", 0)
        if (settings.getBoolean("my_first_time", true)) {
            Log.d("Comments", "First time")
            settings.edit().putBoolean("my_first_time", false).apply()
        }

        setContentView(R.layout.home_activity)
        val topLevelDestinations =
            setOf(R.id.dashboardFragment, R.id.examNamesFragment, R.id.examYearFragment, R.id.examPapersFragment)
        appBarConfiguration = AppBarConfiguration.Builder(topLevelDestinations)
            .setDrawerLayout(drawer_layout)
            .build()
        navController = findNavController(this, R.id.nav_host)

        AppRater.app_launched(this@MainActivity)
    }

}
