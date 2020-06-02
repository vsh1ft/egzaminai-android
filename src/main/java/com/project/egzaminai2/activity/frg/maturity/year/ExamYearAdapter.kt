package com.project.egzaminai2.activity.frg.maturity.year

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.egzaminai2.R
import com.project.egzaminai2.SelectedExamSingleton
import com.project.egzaminai2.ViewInflater

class ExamYearAdapter(
    private val examCallback: (Int) -> Unit,
    private val answCallback: (Int) -> Unit,
    private val years: List<Int>,
    private val viewInflater: ViewInflater = ViewInflater()
): RecyclerView.Adapter<ExamYearViewHolder>() {


    override fun onBindViewHolder(holder: ExamYearViewHolder, position: Int) {
        holder.name.text = years[position].toString()

        holder.name.setOnClickListener {
            examCallback(years[position])
            SelectedExamSingleton.examSelectedEvent()
        }
        holder.button.setOnClickListener{
            answCallback(years[position])
            SelectedExamSingleton.examSelectedEvent()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ExamYearViewHolder(viewInflater.inflate(parent, R.layout.papers_textview))

    override fun getItemCount() = years.size

}
