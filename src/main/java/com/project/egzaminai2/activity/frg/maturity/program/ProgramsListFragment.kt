package com.project.egzaminai2.activity.frg.maturity.program

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
import com.project.egzaminai2.activity.frg.maturity.program.ProgramsListFragmentDirections.programToPaper
import com.project.egzaminai2.retrofit.maturity.program.MaturityProgram

class ProgramsListFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.programs_dropdown_layout, container, false)
        val spinner = layout!!.findViewById<View>(R.id.spinner) as Spinner
        val subjects = resources.getStringArray(R.array.subject_array)
        ArrayAdapter.createFromResource(layout.context, R.array.subject_array, android.R.layout.simple_spinner_item)
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner.adapter = adapter
            }
        ViewModelProvider(this).get(ExamProgramViewModel::class.java)
            .getPrograms().observe(viewLifecycleOwner, Observer<List<MaturityProgram>> { programs ->
                layout.findViewById<RecyclerView>(R.id.simple_recycler).apply {
                    val programsAdapter = ProgramNameAdapter({
                        SelectedExamSingleton.paperUrl = it
                        findNavController().navigate(programToPaper())
                    }, programs.filter { it.subject.toString() == subjects[0] })

                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(this@ProgramsListFragment.context)
                    addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL).apply {
                        setDrawable(ContextCompat.getDrawable(context, R.drawable.dividers2)!!)
                    })
                    adapter = programsAdapter
                    spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
                        override fun onNothingSelected(parent: AdapterView<*>?) {}

                        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                            programsAdapter.updateData(programs.filter { it.subject.toString() == subjects[position] })
                        }
                    }
                }

            })

        return layout
    }
}
