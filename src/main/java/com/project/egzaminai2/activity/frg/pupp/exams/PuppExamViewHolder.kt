package com.project.egzaminai2.activity.frg.pupp.exams

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.egzaminai2.R

class PuppExamViewHolder(nameView: View) : RecyclerView.ViewHolder(nameView) {

    var name: TextView = nameView.findViewById(R.id.generic_list_item)

}
