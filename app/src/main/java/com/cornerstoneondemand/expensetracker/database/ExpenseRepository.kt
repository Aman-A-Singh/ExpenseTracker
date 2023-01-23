package com.cornerstoneondemand.expensetracker.database

import androidx.lifecycle.LiveData
import java.util.*

class ExpenseRepository(private val expenseDao: ExpenseDao) {

    val allExpense:LiveData<List<Expense>> = expenseDao.getAll()
    val thisMonthExpense:LiveData<List<Expense>> = expenseDao.getThisMonthExpense()
    val lastMonthExpense:LiveData<List<Expense>> = expenseDao.getLastMonthExpense()
    val futureExpense:LiveData<List<Expense>> = expenseDao.getFutureExpense()
    suspend fun insert(expense: Expense){
        expenseDao.insert(expense)
    }
}