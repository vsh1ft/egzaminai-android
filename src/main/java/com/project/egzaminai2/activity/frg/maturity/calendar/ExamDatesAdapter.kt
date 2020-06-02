package com.project.egzaminai2.activity.frg.maturity.calendar

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.egzaminai2.R
import com.project.egzaminai2.retrofit.maturity.date.MaturityExamDate
import java.time.LocalDateTime

class ExamDatesAdapter(private val dates: List<MaturityExamDate>):
    RecyclerView.Adapter<ExamDatesViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ExamDatesViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.card_view, parent, false)
    )

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ExamDatesViewHolder, position: Int) {

        val day = LocalDateTime.parse(dates[position].dateTime).dayOfMonth.toString()
        val examName = dates[position].name.toString()
        val color = dates[position].color
        val examType = dates[position].type.toString()
        holder.card.setCardBackgroundColor(Color.parseColor(color))
        holder.tvMonth.text = examName
        holder.tvDay.text = day
        holder.tvExamName.text = "$examType. 9:00"

    }

    override fun getItemCount() = dates.size

}
