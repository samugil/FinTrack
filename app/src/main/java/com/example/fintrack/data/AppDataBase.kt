package com.example.fintrack.data

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Category::class, Expenses::class], version = 1)
abstract class AppDataBase : RoomDatabase(){

    abstract fun categoryDao(): CategoryDao

    abstract fun expensesDao(): ExpensesDao

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "fintrack_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}

class ExpensesDao {

}
