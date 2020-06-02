package com.project.egzaminai2.activity.frg.maturity.year

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.view.*
import android.view.ContextMenu.ContextMenuInfo
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.egzaminai2.EgzaminaiApplication
import com.project.egzaminai2.R
import com.project.egzaminai2.SelectedExamSingleton
import com.project.egzaminai2.retrofit.maturity.name.MaturityExam
import com.project.egzaminai2.retrofit.maturity.ExamType

/**
 * Created by vsh1ft on 8/14/13.
 */
class ExamYearTabMBEFragment: Fragment() {

    private var examName: String? = null
    private var adapter: ExamYearAdapter? = null
    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = activity!!.menuInflater
        inflater.inflate(R.menu.redownload, menu)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // getActivity().getSupportLoaderManager().initLoader(3,null,this);
    }



    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.list_view_default, container, false)
        EgzaminaiApplication.EgzaminaiDb.db.maturityExamDao().getAll()
            .observe(viewLifecycleOwner, Observer<List<MaturityExam>> { exams ->
                view.findViewById<RecyclerView>(R.id.recycler_view).apply {
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(this@ExamYearTabMBEFragment.context)
                    adapter = ExamYearAdapter(
                        { year ->
                            SelectedExamSingleton.paperUrl =
                                exams.first { it.name.toString() == SelectedExamSingleton.selectedExamName && it.year == year }.examUrl
                        },
                        {  year -> SelectedExamSingleton.paperUrl =
                            exams.first { it.name.toString() == SelectedExamSingleton.selectedExamName && it.year == year }.answerUrl
                        },
                        exams.filter { it.name.toString() == SelectedExamSingleton.selectedExamName && it.type == ExamType.SCHOOL_LEVEL}
                            .map { it.year }
                            .toList())
                }
            })
        return view
    }



}
