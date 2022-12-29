package com.cornerstoneondemand.expensetracker.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cornerstoneondemand.expensetracker.utilities.DATABASE_NAME
import com.cornerstoneondemand.expensetracker.utilities.DateConverter

/**
 * This class is resposible for creating the database in the application
 * Only one instance of this class should be created throughout the application
 * If the object of this class is already created then only that object should be accessible throughout the application
 */
@Database(entities = [Expense::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class ExpenseDatabase:RoomDatabase() {

    abstract fun expenseDao():ExpenseDao

    /**
     * This creates only one instance of the class throughout the class
     */
    companion object{

        @Volatile
        private var INSTANCE : ExpenseDatabase?=null

        /**
         * @return ExpenseDatabase Object.
         * Returns only the single instance of ExpenseDatabase
         * if the instance is already created it returns the reference of same object
         */
        fun getInstance(context: Context): ExpenseDatabase {

            //synchronized is used so that no two threads can create the instance of class
            // (in short same operation should not be carried out twice at any given time)

            return INSTANCE?: synchronized(this){//Checks if the INSTANCE is null
                //Executes only if the INSTANCE is null else return the INSTANCE value
                val instance = Room.databaseBuilder(
                    context,
                    ExpenseDatabase::class.java,
                    DATABASE_NAME)
                    .build()

                INSTANCE = instance

                instance
            }
        }
    }
}