package com.project.egzaminai2.activity.frg.pupp.programs

import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
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
import com.project.egzaminai2.activity.frg.maturity.program.ExamProgramViewModel
import com.project.egzaminai2.activity.frg.maturity.program.ProgramNameAdapter
import com.project.egzaminai2.activity.frg.pupp.programs.PuppProgramsListFragmentDirections.puppProgramsToPapers
import com.project.egzaminai2.retrofit.maturity.program.MaturityProgram

class PuppProgramsListFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.list_view_default, container, false)
        ViewModelProvider(this).get(ExamProgramViewModel::class.java)
            .getPrograms().observe(viewLifecycleOwner, Observer<List<MaturityProgram>> { programs ->
                layout.findViewById<RecyclerView>(R.id.recycler_view).apply {
                    val programsAdapter = ProgramNameAdapter({
                        SelectedExamSingleton.paperUrl = it
                        findNavController().navigate(puppProgramsToPapers())
                    }, programs)

                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(this@PuppProgramsListFragment.context)
                    addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL).apply {
                        setDrawable(ContextCompat.getDrawable(context, R.drawable.dividers2)!!)
                    })
                    adapter = programsAdapter

                }

            })

        return layout
    }
}
