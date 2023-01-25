package com.cornerstoneondemand.expensetracker.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.cornerstoneondemand.expensetracker.database.Expense
import com.cornerstoneondemand.expensetracker.database.ExpenseDatabase
import com.cornerstoneondemand.expensetracker.database.ExpenseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.*

class EditExpenseViewModel(application:Application): AndroidViewModel(application)  {
    private val repository: ExpenseRepository
    var selectedDate: MutableLiveData<Date> = MutableLiveData()
    init {
        val dao = ExpenseDatabase.getInstance(application).expenseDao()
        repository = ExpenseRepository(dao)
    }

    fun getExpense(id:Int): Flow<Expense> {
        return repository.getExpense(id)
    }

    fun update(expense: Expense) {
        viewModelScope.launch(Dispatchers.IO) { repository.update(expense) }
    }
}