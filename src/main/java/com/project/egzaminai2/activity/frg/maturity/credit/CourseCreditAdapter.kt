package com.project.egzaminai2.activity.frg.maturity.credit

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.egzaminai2.R
import com.project.egzaminai2.ViewInflater
import com.project.egzaminai2.retrofit.maturity.credit.MaturityCourseCredit

class CourseCreditAdapter(
    private val onClickCallback: (String) -> Unit,
    private var credits: List<MaturityCourseCredit>,
    private val viewInflater: ViewInflater = ViewInflater()
): RecyclerView.Adapter<CreditsViewHolder>() {

    fun updateData(data: List<MaturityCourseCredit>) {
        credits = data
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: CreditsViewHolder, position: Int) {
        holder.name.text = credits[position].name

        holder.itemView.setOnClickListener { onClickCallback(credits[position].creditUrl) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CreditsViewHolder(viewInflater.inflate(parent, R.layout.list_text_view))

    override fun getItemCount() = credits.size

}
