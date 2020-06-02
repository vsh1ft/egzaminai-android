package com.project.egzaminai2.activity.frg.maturity.calendar

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.project.egzaminai2.R

class ExamDatesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val card = view.findViewById<View>(R.id.card_view) as CardView
    val tvMonth = view.findViewById<View>(R.id.examMonthHour) as TextView
    val tvDay = view.findViewById<View>(R.id.exam_day) as TextView
    val tvExamName = view.findViewById<View>(R.id.examName) as TextView
}
