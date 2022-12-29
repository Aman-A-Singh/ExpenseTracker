package com.cornerstoneondemand.expensetracker

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cornerstoneondemand.expensetracker.adapters.CategoryAdapter
import com.cornerstoneondemand.expensetracker.adapters.ExpenseAdapter
import com.cornerstoneondemand.expensetracker.utilities.CATEGORY_RESULT
import com.cornerstoneondemand.expensetracker.utilities.Category

class CategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_category)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        var adapter = CategoryAdapter({ position ->
            val result = position
            val data = Intent().putExtra(CATEGORY_RESULT,result)
            setResult(Activity.RESULT_OK, data)
            finish()
        }
        )
        recyclerView.adapter = adapter
    }
}