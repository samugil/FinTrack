package com.example.fintrack.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface CategoryDao {

    // Operações relacionadas à tabela de categorias
    @Query("SELECT * FROM Category ORDER BY id DESC")
    fun getAllCategories(): LiveData<List<Category>>

    @Query("SELECT * FROM Category WHERE id = :id")
    fun getCategoryById(id: Int): Category

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategory(category: Category)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateCategory(category: Category)

    @Delete
    fun deleteCategory(category: Category)

    // Operações relacionadas à tabela de despesas
    @Query("SELECT * FROM Expenses ORDER BY id DESC")
    fun getAllExpenses(): List<Expenses>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExpenses(expense: Expenses)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateExpenses(expense: Expenses)

    @Delete
    fun deleteExpenses(expense: Expenses)
}
