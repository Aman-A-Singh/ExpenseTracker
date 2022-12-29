package com.cornerstoneondemand.expensetracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.cornerstoneondemand.expensetracker.database.Expense
import com.cornerstoneondemand.expensetracker.database.ExpenseDatabase
import com.cornerstoneondemand.expensetracker.database.ExpenseRepository
import java.util.*

class FutureExpenseViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ExpenseRepository
    val futureExpense : LiveData<List<Expense>>

    init{
        val dao = ExpenseDatabase.getInstance(application).expenseDao()
        repository = ExpenseRepository(dao)
        futureExpense = getFutureMonthObjects(repository.allExpense)
    }

    fun getFutureMonthObjects(objects: LiveData<List<Expense>>):LiveData<List<Expense>>{
        return Transformations.map(objects) {
            val calendar = Calendar.getInstance()
            val currentMonth = calendar.get(Calendar.MONTH)  + 1 // Calendar.MONTH is zero-based
            val currentYear = calendar.get(Calendar.YEAR)
            it.filter {
                val calendar = Calendar.getInstance()
                calendar.time = it.date
                (calendar.get(Calendar.MONTH) + 1 > currentMonth) or (calendar.get(Calendar.YEAR) > currentYear)
            }
        }
    }
}