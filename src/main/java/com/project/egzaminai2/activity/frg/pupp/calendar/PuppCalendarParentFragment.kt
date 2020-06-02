package com.project.egzaminai2.activity.frg.pupp.calendar

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.project.egzaminai2.R
import com.project.egzaminai2.activity.frg.CaldroidSampleCustomFragment
import com.project.egzaminai2.activity.frg.EmptyFragment
import com.project.egzaminai2.retrofit.pupp.date.PuppExamDate
import com.roomorama.caldroid.CaldroidFragment
import com.roomorama.caldroid.CaldroidListener
import java.time.LocalDateTime
import java.util.*

/**
 * Created by vsh1ft on 14/11/2016.
 */
class PuppCalendarParentFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.test, container, false)

        childFragmentManager
            .beginTransaction()
            .add(R.id.My_Container_2_ID, initCalendar(), "Frag_Middle_tag")
            .commit()
        childFragmentManager
            .beginTransaction()
            .add(R.id.My_Container_1_ID, PuppCalendarFragment(), "Frag_Middle_tag")
            .commit()
        return layout
    }

    private fun initCalendar(): CaldroidSampleCustomFragment {
        val caldroidFragment = CaldroidSampleCustomFragment()
        val args = Bundle()
        args.putInt(CaldroidFragment.MONTH, 5) // October
        args.putInt(CaldroidFragment.YEAR, 2020)
        args.putInt(CaldroidFragment.START_DAY_OF_WEEK, CaldroidFragment.MONDAY)
        args.putBoolean(CaldroidFragment.SIX_WEEKS_IN_CALENDAR, false)
        caldroidFragment.arguments = args



        ViewModelProvider(this).get(PuppDatesParentViewModel::class.java)
            .getDates().observe(viewLifecycleOwner, Observer<List<PuppExamDate>> { dates ->
                val listener: CaldroidListener = object: CaldroidListener() {
                    override fun onSelectDate(date: Date, view: View) {
                        val c = Calendar.getInstance()
                        c.time = date
                        val dayOfMonth = c[Calendar.DAY_OF_MONTH]
                        val customMonth = c[Calendar.MONTH]
                        val alertDialogBuilder =
                            AlertDialog.Builder(this@PuppCalendarParentFragment.context!!)
                        val alertDialog = alertDialogBuilder.create()
                        alertDialog.setCancelable(true)
                        val inflater = layoutInflater
                        val dialoglayout = inflater.inflate(R.layout.card_view, null)
                        val card = dialoglayout.findViewById<View>(R.id.card_view) as CardView
                        val tvMonth = dialoglayout.findViewById<View>(R.id.examMonthHour) as TextView
                        val tvDay = dialoglayout.findViewById<View>(R.id.exam_day) as TextView
                        val tvExamName = dialoglayout.findViewById<View>(R.id.examName) as TextView
                        var found = false
                        for (i in 0 until dates.size) {
                            val month = LocalDateTime.parse(dates[i].dateTime).monthValue
                            val day = LocalDateTime.parse(dates[i].dateTime).dayOfMonth
                            val colorCode = dates[i].color
                            val examName = dates[i].name.toString()
                            if (month == customMonth + 1 && day == dayOfMonth) {
                                card.setCardBackgroundColor(Color.parseColor(colorCode))
                                found = true
                                tvMonth.text = examName
                                tvDay.text = Integer.toString(day)
                           }
                        }
                        alertDialog.setView(dialoglayout)
                        if (found) {
                            alertDialog.show()
                        }
                    }

                    override fun onChangeMonth(month: Int, year: Int) {
                        if (month == 5 && year == 2020) {
                            caldroidFragment.leftArrowButton.visibility = View.GONE
                            caldroidFragment.rightArrowButton.visibility = View.VISIBLE
                            val ft: FragmentTransaction = parentFragmentManager.beginTransaction()
                            val clFrg: Fragment = PuppCalendarFragment()
                            val bundle = Bundle()
                            bundle.putInt("value", 1)
                            clFrg.arguments = bundle
                            ft.replace(R.id.My_Container_1_ID, clFrg, "NewFragmentTag")
                            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            ft.commit()
                        } else if (month == 6 && year == 2020) {
                            caldroidFragment.rightArrowButton.visibility = View.GONE
                            caldroidFragment.leftArrowButton.visibility = View.VISIBLE
                            val ft: FragmentTransaction = parentFragmentManager.beginTransaction()
                            val clFrg: Fragment = PuppCalendarFragment()
                            val bundle = Bundle()
                            bundle.putInt("value", 2)
                            clFrg.arguments = bundle
                            ft.replace(R.id.My_Container_1_ID, clFrg, "NewFragmentTag")
                            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            ft.commit()
                        } else {
                            val ft: FragmentTransaction = parentFragmentManager.beginTransaction()
                            ft.replace(R.id.My_Container_2_ID, EmptyFragment(), "NewFragmentTag")
                            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                            ft.commit()
                        }
                    }
                }
                caldroidFragment.caldroidListener = listener
                for (i in 0 until dates.size) {
                    val month = LocalDateTime.parse(dates[i].dateTime).monthValue
                    val day = LocalDateTime.parse(dates[i].dateTime).dayOfMonth
                    val colorCode = dates[i].color
                    val examName = dates[i].name.toString()
                    val cal = Calendar.getInstance()
                    cal[2020, month - 1] = day
                    val blueDate = cal.time
                        val blue =  ColorDrawable(Color.parseColor(colorCode));
                        caldroidFragment.setBackgroundDrawableForDate(blue, blueDate)
                        caldroidFragment.setTextColorForDate(R.color.caldroid_light_red, blueDate)
                }
                caldroidFragment.refreshView()

            })
        return caldroidFragment
    }

}
