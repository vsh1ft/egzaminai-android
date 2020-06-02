package com.project.egzaminai2.activity.frg.pupp.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.egzaminai2.R
import com.project.egzaminai2.retrofit.maturity.date.MaturityExamDate

/**
 * Created by vsh1ft on 14/11/2016.
 */
class PuppCalendarFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.exam_dates_recycler, container, false)

        ViewModelProvider(this).get(PuppDatesViewModel::class.java)
            .getDates().observe(viewLifecycleOwner, Observer<List<MaturityExamDate>> { dates ->
                layout.findViewById<RecyclerView>(R.id.cardList).apply {
                    val programsAdapter = PuppExamDatesAdapter(dates)

                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(this@PuppCalendarFragment.context)
                    addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL).apply {
                        setDrawable(ContextCompat.getDrawable(context, R.drawable.dividers2)!!)
                    })
                    adapter = programsAdapter
                }

            })

        return layout
    }

}
