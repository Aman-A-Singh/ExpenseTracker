package com.cornerstoneondemand.expensetracker.database

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import java.util.*
import kotlin.math.exp

class ExpenseRepository(private val expenseDao: ExpenseDao) {

    val allExpense:LiveData<List<Expense>> = expenseDao.getAll()
    suspend fun insert(expense: Expense){
        expenseDao.insert(expense)
    }
    suspend fun update(expense:Expense){
        expenseDao.updateExpense(expense)
    }
    @MainThread
    fun getExpense(id:Int): Flow<Expense> = expenseDao.getExpense(id).distinctUntilChanged()
}