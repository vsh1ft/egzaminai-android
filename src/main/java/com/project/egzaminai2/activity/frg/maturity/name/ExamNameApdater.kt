package com.project.egzaminai2.activity.frg.maturity.name

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.egzaminai2.R
import com.project.egzaminai2.ViewInflater
import com.project.egzaminai2.retrofit.maturity.name.MaturityExam

class ExamNameApdater(
    private val onClickCallback: (String) -> Unit,
    private val exams: List<MaturityExam>,
    private val viewInflater: ViewInflater = ViewInflater()
): RecyclerView.Adapter<ExamNameViewHolder>() {


    override fun onBindViewHolder(holder: ExamNameViewHolder, position: Int) {
        holder.name.text = exams[position].name.toString()

        holder.itemView.setOnClickListener { onClickCallback(exams[position].name.toString()) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ExamNameViewHolder(viewInflater.inflate(parent, R.layout.list_text_view))

    override fun getItemCount() = exams.size

}
