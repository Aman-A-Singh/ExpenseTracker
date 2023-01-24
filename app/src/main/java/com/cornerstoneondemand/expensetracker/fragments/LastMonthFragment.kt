package com.cornerstoneondemand.expensetracker.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cornerstoneondemand.expensetracker.EditExpenseActivity
import com.cornerstoneondemand.expensetracker.R
import com.cornerstoneondemand.expensetracker.adapters.ExpenseAdapter
import com.cornerstoneondemand.expensetracker.utilities.EXPENSE_ID
import com.cornerstoneondemand.expensetracker.viewmodel.LastMonthViewModel


class LastMonthFragment : Fragment() {

    lateinit var viewModel: LastMonthViewModel
    lateinit var adapter: ExpenseAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_last_month, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_last_month)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager =LinearLayoutManager(activity)
        adapter = ExpenseAdapter({id->
            val intent = Intent(activity, EditExpenseActivity()::class.java)
            intent.putExtra(EXPENSE_ID, id)
            this.startActivity(intent)
        })
        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this).get(LastMonthViewModel::class.java)
        viewModel.lastMonthExpense.observe(viewLifecycleOwner) { list ->
            list?.let{
                adapter.setExpense(list)
            }
        }
        return view
    }
}