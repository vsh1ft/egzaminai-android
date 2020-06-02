package com.project.egzaminai2.activity.frg.maturity.name

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.project.egzaminai2.activity.frg.maturity.name.ExamNameFragmentDirections.examNamesToExamYear
import com.project.egzaminai2.retrofit.maturity.name.MaturityExam


class ExamNameFragment: Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val layout = inflater.inflate(R.layout.list_view_default, container, false)
        ViewModelProvider(this).get(ExamNameViewModel::class.java)
            .getNames().observe(viewLifecycleOwner, Observer<List<MaturityExam>> {
                layout.findViewById<RecyclerView>(R.id.recycler_view).apply {
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(this@ExamNameFragment.context)
                    addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL).apply {
                        setDrawable(ContextCompat.getDrawable(context, R.drawable.dividers2)!!)
                    })
                    adapter =
                        ExamNameApdater({
                            SelectedExamSingleton.selectedExamName = it
                            findNavController().navigate(examNamesToExamYear())
                        }, it)
                }

            })

        return layout
    }

}
