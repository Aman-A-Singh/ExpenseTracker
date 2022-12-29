package com.cornerstoneondemand.expensetracker

import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import com.cornerstoneondemand.expensetracker.adapters.SectionsPagerAdapter
import com.cornerstoneondemand.expensetracker.database.ExpenseDatabase
import com.cornerstoneondemand.expensetracker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)
        val fab: FloatingActionButton = binding.fab

        tabs.getTabAt(1)?.select()

        fab.setOnClickListener { view ->
            val intent:Intent = Intent(this,AddExpense::class.java)
            startActivity((intent))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        ExpenseDatabase.getInstance(this).close()
    }
}