package com.project.egzaminai2.activity.frg.pupp.calendar

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.project.egzaminai2.R

class PuppExamDatesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

   // var name: TextView = nameView.findViewById(R.id.generic_list_item)
    val card = view.findViewById<View>(R.id.card_view) as CardView
  //  card.setCardBackgroundColor(Color.parseColor(color))
    val tvMonth = view.findViewById<View>(R.id.examMonthHour) as TextView
  //  tvMonth.text = examName
    val tvDay = view.findViewById<View>(R.id.exam_day) as TextView
  //  tvDay.text = day
    val tvExamName = view.findViewById<View>(R.id.examName) as TextView
   // tvExamName.text = "$examType. 9:00"
}
