package com.project.egzaminai2.activity.frg.pupp.exams

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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.egzaminai2.R
import com.project.egzaminai2.SelectedExamSingleton
import com.project.egzaminai2.activity.frg.pupp.exams.PuppPapersFragmentDirections.puppExamToPapers
import com.project.egzaminai2.retrofit.pupp.exam.PuppExam

class PuppPapersFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.programs_dropdown_layout, container, false)
        val spinner = layout!!.findViewById<View>(R.id.spinner) as Spinner
        val years = resources.getStringArray(R.array.pupp_exam_years)
        ArrayAdapter.createFromResource(layout.context, R.array.pupp_exam_years, android.R.layout.simple_spinner_item)
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner.adapter = adapter
            }
        ViewModelProvider(this).get(PuppExamViewModel::class.java)
            .getExams().observe(viewLifecycleOwner, Observer<List<PuppExam>> { exams ->
                val examAdapter = PuppExamAdapter({
                    SelectedExamSingleton.paperUrl = it
                    findNavController().navigate(puppExamToPapers())
                }, exams.filter { it.year.toString() == years[0] })

                layout.findViewById<RecyclerView>(R.id.simple_recycler).apply {
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(this@PuppPapersFragment.context)
                    addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL).apply {
                        setDrawable(ContextCompat.getDrawable(context, R.drawable.dividers2)!!)
                    })

                    adapter = examAdapter
                }
                spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(parent: AdapterView<*>?) {}

                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        examAdapter.updateData(exams.filter { it.year.toString() == years[position] })
                    }
                }
            })

        return layout
    }

}
