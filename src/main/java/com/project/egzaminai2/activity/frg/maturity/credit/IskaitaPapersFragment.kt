package com.project.egzaminai2.activity.frg.maturity.credit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.egzaminai2.R
import com.project.egzaminai2.SelectedExamSingleton
import com.project.egzaminai2.activity.frg.maturity.credit.IskaitaPapersFragmentDirections.creditToPaper
import com.project.egzaminai2.retrofit.maturity.credit.MaturityCourseCredit

class IskaitaPapersFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.programs_dropdown_layout, container, false)
        val spinner = layout!!.findViewById<View>(R.id.spinner) as Spinner
        val years = resources.getStringArray(R.array.iskaita_array)
        ArrayAdapter.createFromResource(layout.context, R.array.iskaita_array, android.R.layout.simple_spinner_item)
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner.adapter = adapter
            }
        ViewModelProvider(this).get(CourseCreditViewModel::class.java)
            .getCredits().observe(viewLifecycleOwner, Observer<List<MaturityCourseCredit>> { credits ->
                layout.findViewById<RecyclerView>(R.id.simple_recycler).apply {
                    val creditsAdapter =
                        CourseCreditAdapter({
                            SelectedExamSingleton.paperUrl = it
                            findNavController().navigate(creditToPaper())
                        }, credits.filter { it.year.toString() == years[0] })
                    layoutManager = LinearLayoutManager(this@IskaitaPapersFragment.context)
                    setHasFixedSize(true)
                    addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL).apply {
                        setDrawable(ContextCompat.getDrawable(context, R.drawable.dividers2)!!)
                    })
                    adapter = creditsAdapter
                    spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
                        override fun onNothingSelected(parent: AdapterView<*>?) {}

                        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                            creditsAdapter.updateData(credits.filter { it.year.toString() == years[position] })
                        }
                    }
                }

            })
        return layout
    }

}
