package com.project.egzaminai2.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.project.egzaminai2.SelectedExamSingleton
import com.project.egzaminai2.activity.frg.maturity.year.ExamYearTabMBEFragment
import com.project.egzaminai2.activity.frg.maturity.year.ExamYearTabVBEFragment
import java.util.*

class SampleFragmentPagerAdapter(
    fm: FragmentManager?
): FragmentPagerAdapter(fm!!) {

    private val noVBE = arrayOf(
        "Lietuvių kalba ir literatūra",
        "Biologija",
        "Matematika",
        "Chemija",
        "Fizika",
        "Istorija",
        "Geografija",
        "Užsienio kalba (anglų)",
        "Užsienio kalba (prancūzų)",
        "Užsienio kalba (rusų)",
        "Užsienio kalba (vokiečių)"
    )
    private val noMBE = "Informacinės technologijos"
    private val tabTitles = arrayOf("Valstybiniai", "Mokykliniai")
    private var examName: String? = null
    private val pageCount: Int
    override fun getCount(): Int {
        return pageCount
    }

    override fun getItem(position: Int): Fragment {
        return if (pageCount == 2) {
            if (position == 0) // if the position is 0 we are returning the First tab
            {
                ExamYearTabVBEFragment()
            } else  // As we are having 2 tabs if the position is now 0 it must be 1 so we are returning second tab
            {
                ExamYearTabMBEFragment()
            }
        } else {
            if (noMBE == examName) {
                ExamYearTabVBEFragment()
            } else {
                ExamYearTabMBEFragment()
            }
        }
    }

    private fun getPageCount(examName: String?): Int {
        return if (!Arrays.asList(*noVBE).contains(examName) or (noMBE == examName)) {
            1
        } else {
            2
        }
    }

    override fun getPageTitle(position: Int): CharSequence? { // Generate title based on item position
        return if (pageCount == 2) {
            tabTitles[position]
        } else {
            if (noMBE == examName) {
                tabTitles[0]
            } else {
                tabTitles[1]
            }
        }
    }

    init {
        this.examName = SelectedExamSingleton.selectedExamName;
        pageCount = getPageCount(examName)
    }
}
