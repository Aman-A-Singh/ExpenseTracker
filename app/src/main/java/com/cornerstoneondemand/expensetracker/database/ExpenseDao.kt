package com.cornerstoneondemand.expensetracker.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.cornerstoneondemand.expensetracker.utilities.DATABASE_NAME

@Dao
interface ExpenseDao {
    @Insert
    suspend fun insert(expense:Expense)

    @Query("SELECT * FROM $DATABASE_NAME")
    fun getAll() : LiveData<List<Expense>>

    @Query("SELECT * FROM $DATABASE_NAME where strftime('%m', date) = :month")
    fun getThisMonthExpense(month:String):LiveData<List<Expense>>

    @Query("SELECT * FROM $DATABASE_NAME where strftime('%m',date)<strftime('%m',date('now'))")
    fun getLastMonthExpense():LiveData<List<Expense>>

    @Query("SELECT * FROM $DATABASE_NAME where strftime('%m',date)>strftime('%m',date('now'))")
    fun getFutureExpense():LiveData<List<Expense>>
}