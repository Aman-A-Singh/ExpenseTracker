package com.cornerstoneondemand.expensetracker.database

import androidx.lifecycle.LiveData

class ExpenseRepository(private val expenseDao: ExpenseDao) {

    val allExpense:LiveData<List<Expense>> = expenseDao.getAll()
    val thisMonthExpense:LiveData<List<Expense>> = expenseDao.getThisMonthExpense("12")
    val lastMonthExpense:LiveData<List<Expense>> = expenseDao.getLastMonthExpense()
    val futureExpense:LiveData<List<Expense>> = expenseDao.getFutureExpense()

    suspend fun insert(expense: Expense){
        expenseDao.insert(expense)
    }
}