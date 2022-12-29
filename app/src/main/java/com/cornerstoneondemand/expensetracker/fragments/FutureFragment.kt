package com.cornerstoneondemand.expensetracker.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cornerstoneondemand.expensetracker.R
import com.cornerstoneondemand.expensetracker.adapters.ExpenseAdapter
import com.cornerstoneondemand.expensetracker.viewmodel.FutureExpenseViewModel

class FutureFragment : Fragment() {

    lateinit var viewModel: FutureExpenseViewModel
    lateinit var adapter: ExpenseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_future, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_future)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager =LinearLayoutManager(activity)
        adapter = ExpenseAdapter()
        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this).get(FutureExpenseViewModel::class.java)
        viewModel.futureExpense.observe(viewLifecycleOwner) { list ->
            list?.let{
                adapter.setExpense(list)
            }
        }
        return view
    }

}