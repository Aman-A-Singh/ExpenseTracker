package com.cornerstoneondemand.expensetracker.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.cornerstoneondemand.expensetracker.utilities.Category
import com.cornerstoneondemand.expensetracker.utilities.DATABASE_NAME
import java.util.*

@Dao
interface ExpenseDao {
    @Insert
    suspend fun insert(expense:Expense)

    @Query("SELECT * FROM $DATABASE_NAME")
    fun getAll() : LiveData<List<Expense>>

    @Query("SELECT * FROM $DATABASE_NAME where strftime('%m', date) = strftime('%m',date('now'))")
    fun getThisMonthExpense():LiveData<List<Expense>>

    @Query("SELECT * FROM $DATABASE_NAME where strftime('%m',date)<strftime('%m',date('now'))")
    fun getLastMonthExpense():LiveData<List<Expense>>

    @Query("SELECT * FROM $DATABASE_NAME where strftime('%m',date)>strftime('%m',date('now'))")
    fun getFutureExpense():LiveData<List<Expense>>

    @Query("Update $DATABASE_NAME set amount=:amount,category_id=:category_id,note=:note,date=:date,payment_mode=:payment_mode where id = :id")
    fun updateExpense(id:Int,amount:Double,category_id: Category,note:String?,date:Date,payment_mode:String?)
}