package com.project.egzaminai2.activity.frg.maturity.year

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.egzaminai2.EgzaminaiApplication.EgzaminaiDb.db
import com.project.egzaminai2.R
import com.project.egzaminai2.SelectedExamSingleton
import com.project.egzaminai2.retrofit.maturity.name.MaturityExam
import com.project.egzaminai2.retrofit.maturity.ExamType

/**
 * Created by Vsh1ft on 8/14/13.
 */
class ExamYearTabVBEFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.list_view_default, container, false)
        db.maturityExamDao().getAll()
            .observe(viewLifecycleOwner, Observer<List<MaturityExam>> { exams ->
                view.findViewById<RecyclerView>(R.id.recycler_view).apply {
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(this@ExamYearTabVBEFragment.context)
                    adapter = ExamYearAdapter(
                        { year ->
                            SelectedExamSingleton.paperUrl =
                                exams.first { it.name.toString() == SelectedExamSingleton.selectedExamName && it.year == year }
                                    .examUrl
                        },
                        { year ->
                            SelectedExamSingleton.paperUrl =
                                exams.first { it.name.toString() == SelectedExamSingleton.selectedExamName && it.year == year }
                                    .answerUrl
                        },
                        exams.filter { it.name.toString() == SelectedExamSingleton.selectedExamName && it.type == ExamType.NATIONAL_LEVEL }
                            .map { it.year }
                            .toList())
                }
            })
        return view
    }

}
