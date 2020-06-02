package com.project.egzaminai2.activity.frg.maturity.program

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.egzaminai2.R
import com.project.egzaminai2.ViewInflater
import com.project.egzaminai2.retrofit.maturity.program.MaturityProgram

class ProgramNameAdapter(
    private val onClickCallback: (String) -> Unit,
    private var programs: List<MaturityProgram>,
    private val viewInflater: ViewInflater = ViewInflater()
): RecyclerView.Adapter<ProgramNameViewHolder>() {

    fun updateData(data: List<MaturityProgram>) {
        programs = data
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ProgramNameViewHolder, position: Int) {
        holder.name.text = programs[position].name

        holder.itemView.setOnClickListener { onClickCallback(programs[position].programUrl) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ProgramNameViewHolder(viewInflater.inflate(parent, R.layout.list_text_view))

    override fun getItemCount() = programs.size

}
