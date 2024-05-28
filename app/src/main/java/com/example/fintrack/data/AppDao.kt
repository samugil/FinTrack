package com.example.fintrack.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface AppDao {

    @Query("SELECT * FROM Category")
    fun getAllCategories(): LiveData<List<Category>>

    @Query("SELECT * FROM Category")
    fun getAllCategory(): List<Category>

    @Query("SELECT * FROM Expenses")
    fun getAllExpenses(): List<Expenses>

    @Query("SELECT * FROM Category WHERE id = :id")
    fun getCategoryById(id: Int): LiveData<Category>

    @Query("SELECT * FROM Category WHERE title = :name")
    fun getCategoryByName(name: String): LiveData<Category>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategory(category: Category)

    @Update
    fun updateCategory(category: Category)

    @Delete
    fun deleteCategory(category: Category)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExpenses(expense: Expenses)

    @Update
    fun updateExpenses(expense: Expenses)

    @Delete
    fun deleteExpenses(expense: Expenses)
}
