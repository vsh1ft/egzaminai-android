package com.project.egzaminai2.activity.frg.maturity.year

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.project.egzaminai2.R
import com.project.egzaminai2.SelectedExamSingleton
import com.project.egzaminai2.adapters.SampleFragmentPagerAdapter
import com.project.egzaminai2.activity.frg.maturity.year.ExamYearsFragmentDirections.examYearsToPaper

/**
 * Created by Vsh1ft on 8/10/13.
 */
class ExamYearsFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.tabs_layout, container, false)
        val viewPager = view.findViewById<ViewPager>(R.id.viewpager).apply {
            adapter = SampleFragmentPagerAdapter(
                childFragmentManager
            )
        }
        SelectedExamSingleton.examSelectedEvent = {
            findNavController().navigate(examYearsToPaper())
        }
        view
            .findViewById<TabLayout>(R.id.sliding_tabs)
            .setupWithViewPager(viewPager)

        return view
    }

}
