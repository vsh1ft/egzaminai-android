package com.project.egzaminai2.activity.frg

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import com.project.egzaminai2.R

class HomeFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.home_table_layout, container, false).apply {
            findViewById<RelativeLayout>(R.id.examLayout).setOnClickListener { findNavController(it).navigate(R.id.dashboardToExamNames) }
            findViewById<RelativeLayout>(R.id.planaiLayout).setOnClickListener { findNavController(it).navigate(R.id.dashboardToPrograms) }
            findViewById<RelativeLayout>(R.id.iskaitaLayout).setOnClickListener { findNavController(it).navigate(R.id.dashboardToCredit) }
            findViewById<RelativeLayout>(R.id.puppLayout).setOnClickListener { findNavController(it).navigate(R.id.dashboardToPuppExam) }
            findViewById<RelativeLayout>(R.id.puppPlanaiLayout).setOnClickListener { findNavController(it).navigate(R.id.dashboardToPuppPrograms) }
            findViewById<RelativeLayout>(R.id.datosLayout).setOnClickListener { findNavController(it).navigate(R.id.dashboardToExamDatesFragment) }
            findViewById<RelativeLayout>(R.id.puppDatosLayout).setOnClickListener { findNavController(it).navigate(R.id.dashboardToPuppExamDatesFragment) }
        }

        return view
    }
}
