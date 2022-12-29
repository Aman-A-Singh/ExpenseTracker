package com.cornerstoneondemand.expensetracker.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.cornerstoneondemand.expensetracker.R
import com.cornerstoneondemand.expensetracker.fragments.FutureFragment
import com.cornerstoneondemand.expensetracker.fragments.LastMonthFragment
import com.cornerstoneondemand.expensetracker.fragments.ThisMonthFragment

private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2,
    R.string.tab_text_3
)
private val fragmentList = arrayListOf<Fragment>(LastMonthFragment(),
    ThisMonthFragment(),FutureFragment())
/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return fragmentList[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show all total pages.
        return TAB_TITLES.size
    }

    fun addFragments(fragment:Fragment){
        fragmentList.add(fragment);
    }
}