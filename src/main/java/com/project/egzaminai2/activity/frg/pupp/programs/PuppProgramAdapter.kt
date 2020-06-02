package com.project.egzaminai2.activity.frg.pupp.programs

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.egzaminai2.R
import com.project.egzaminai2.ViewInflater
import com.project.egzaminai2.retrofit.maturity.program.MaturityProgram
import com.project.egzaminai2.retrofit.pupp.program.PuppProgram

class PuppProgramAdapter(
    private val onClickCallback: (String) -> Unit,
    private var programs: List<PuppProgram>,
    private val viewInflater: ViewInflater = ViewInflater()
): RecyclerView.Adapter<PuppProgramViewHolder>() {

    fun updateData(data: List<PuppProgram>) {
        programs = data
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: PuppProgramViewHolder, position: Int) {
        holder.name.text = programs[position].name

        holder.itemView.setOnClickListener { onClickCallback(programs[position].programUrl) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PuppProgramViewHolder(viewInflater.inflate(parent, R.layout.list_text_view))

    override fun getItemCount() = programs.size

}
