package com.cornerstoneondemand.expensetracker.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cornerstoneondemand.expensetracker.utilities.Category
import com.cornerstoneondemand.expensetracker.utilities.DATABASE_NAME
import java.util.Date

@Entity(tableName = DATABASE_NAME)
data class Expense(
    @PrimaryKey(autoGenerate = true) var id:Int,
    @ColumnInfo(name ="amount") val amount:Double,
    @ColumnInfo(name="category_id") val category_id:Category,
    @ColumnInfo(name="note")val note:String?,
    @ColumnInfo(name="date")val date:Date,
    @ColumnInfo(name="payment_mode")val payment_mode:String?
    )