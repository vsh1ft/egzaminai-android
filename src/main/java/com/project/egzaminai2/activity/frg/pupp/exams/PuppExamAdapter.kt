package com.project.egzaminai2.activity.frg.pupp.exams

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.egzaminai2.R
import com.project.egzaminai2.ViewInflater
import com.project.egzaminai2.retrofit.pupp.exam.PuppExam

class PuppExamAdapter(
    private val onClickCallback: (String) -> Unit,
    private var exams: List<PuppExam>,
    private val viewInflater: ViewInflater = ViewInflater()
): RecyclerView.Adapter<PuppExamViewHolder>() {

    fun updateData(data: List<PuppExam>) {
        exams = data
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: PuppExamViewHolder, position: Int) {
        holder.name.text = exams[position].name.toString()

        holder.itemView.setOnClickListener { onClickCallback(exams[position].examUrl) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PuppExamViewHolder(viewInflater.inflate(parent, R.layout.list_text_view))

    override fun getItemCount() = exams.size

}
