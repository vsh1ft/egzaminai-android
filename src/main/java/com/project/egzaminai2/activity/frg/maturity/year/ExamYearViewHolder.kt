package com.project.egzaminai2.activity.frg.maturity.year

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.egzaminai2.R

class ExamYearViewHolder(nameView: View) : RecyclerView.ViewHolder(nameView) {

    var name: TextView = nameView.findViewById(R.id.default_text_view2)
    var button: Button = nameView.findViewById(R.id.answers)

}
