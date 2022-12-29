package com.cornerstoneondemand.expensetracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.cornerstoneondemand.expensetracker.database.Expense
import com.cornerstoneondemand.expensetracker.database.ExpenseDatabase
import com.cornerstoneondemand.expensetracker.database.ExpenseRepository
import java.util.*

class LastMonthViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ExpenseRepository
    val lastMonthExpense : LiveData<List<Expense>>

    init{
        val dao = ExpenseDatabase.getInstance(application).expenseDao()
        repository = ExpenseRepository(dao)
        lastMonthExpense = getLastMonthObjects(repository.allExpense)
    }

    fun getLastMonthObjects(objects: LiveData<List<Expense>>):LiveData<List<Expense>>{
        return Transformations.map(objects) {
            val calendar = Calendar.getInstance()
            val lastMonth = calendar.get(Calendar.MONTH)  // Calendar.MONTH is zero-based
            val currentYear = calendar.get(Calendar.YEAR)
            it.filter {
                calendar.time = it.date
                (calendar.get(Calendar.MONTH) + 1 == lastMonth) or (calendar.get(Calendar.YEAR)<currentYear)
            }
        }
    }
}