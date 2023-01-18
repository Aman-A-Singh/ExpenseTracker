package com.cornerstoneondemand.expensetracker.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cornerstoneondemand.expensetracker.EditExpenseActivity
import com.cornerstoneondemand.expensetracker.R
import com.cornerstoneondemand.expensetracker.adapters.ExpenseAdapter
import com.cornerstoneondemand.expensetracker.database.Expense
import com.cornerstoneondemand.expensetracker.utilities.EXPENSE_ID
import com.cornerstoneondemand.expensetracker.viewmodel.ThisMonthViewModel
import java.text.SimpleDateFormat
import java.util.*

class ThisMonthFragment : Fragment() {
    lateinit var viewModel: ThisMonthViewModel
    lateinit var adapter: ExpenseAdapter
    var totalAmount: Double = 0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //Initializing the UI
        val view = inflater.inflate(R.layout.fragment_this_month, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_this_month)

        val date = Date()
        val text_view_date = view.findViewById<TextView>(R.id.text_view_date)
        text_view_date.text = date.date.toString()

        val text_view_month_year = view.findViewById<TextView>(R.id.text_view_month_year)
        val dateFormat = SimpleDateFormat("MMMM yyyy", Locale.getDefault())
        text_view_month_year.text = dateFormat.format(date)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = ExpenseAdapter({id->
            val intent = Intent(activity, EditExpenseActivity()::class.java)
            intent.putExtra(EXPENSE_ID, id)
            this.startActivity(intent)
        })
        recyclerView.adapter = adapter


        viewModel = ViewModelProvider(this).get(ThisMonthViewModel::class.java)
        viewModel.thisMonthExpense.observe(viewLifecycleOwner) { list ->
            list?.let {
                adapter.setExpense(list)
            }
        }
        viewModel.thisMonthExpense.observe(viewLifecycleOwner) { list ->
            totalAmount = 0.0
            list.forEach { item ->
                totalAmount = totalAmount + item.amount
            }
            val text_view_total = view.findViewById<TextView>(R.id.text_view_total)
            text_view_total.text = String.format("%,.2f",totalAmount)
        }
        return view
    }
}