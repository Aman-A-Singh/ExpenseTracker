package com.cornerstoneondemand.expensetracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cornerstoneondemand.expensetracker.adapters.ExpenseAdapter
import com.cornerstoneondemand.expensetracker.database.Expense
import com.cornerstoneondemand.expensetracker.database.ExpenseDatabase
import com.cornerstoneondemand.expensetracker.database.ExpenseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class AddExpenseViewModel(application: Application) : AndroidViewModel(application) {
    var selectedDate: MutableLiveData<Date> = MutableLiveData()
    private val expenseRepository: ExpenseRepository

    init {
        val db = ExpenseDatabase.getInstance(application)
        val dao = db.expenseDao()
        expenseRepository = ExpenseRepository(dao)
    }

    fun insert(expense: Expense) =
        viewModelScope.launch(Dispatchers.IO) { expenseRepository.insert(expense) }

}