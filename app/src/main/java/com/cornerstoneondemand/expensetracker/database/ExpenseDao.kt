package com.cornerstoneondemand.expensetracker.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.cornerstoneondemand.expensetracker.utilities.Category
import com.cornerstoneondemand.expensetracker.utilities.DATABASE_NAME
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface ExpenseDao {
    @Insert
    suspend fun insert(expense:Expense)

    @Query("SELECT * FROM $DATABASE_NAME")
    fun getAll() : LiveData<List<Expense>>

    @Update
    fun updateExpense(expense: Expense)

    @Query("Select * from $DATABASE_NAME where id = :id")
    fun getExpense(id:Int) : Flow<Expense>
}



/*
@Query("SELECT * FROM $DATABASE_NAME where strftime('%m', date) = strftime('%m',date('now'))")
    fun getThisMonthExpense():LiveData<List<Expense>>

    @Query("SELECT * FROM $DATABASE_NAME where strftime('%m',date)<strftime('%m',date('now'))")
    fun getLastMonthExpense():LiveData<List<Expense>>

    @Query("SELECT * FROM $DATABASE_NAME where strftime('%m',date)>strftime('%m',date('now'))")
    fun getFutureExpense():LiveData<List<Expense>>
 */