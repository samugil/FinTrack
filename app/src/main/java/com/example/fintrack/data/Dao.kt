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
    @Query("SELECT * FROM Category ORDER BY id DESC LIMIT 1")
    fun getAllCategories(): List<Category>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategory(title: Category)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateCategory(title: Category)

    @Delete
    fun deleteCategory(title: Category)


    // Operações relacionadas à tabela de tarefas
    @Query("SELECT * FROM Expenses ORDER BY id DESC LIMIT 1")
    fun getAllExpenses(): List<Expenses>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExpenses(title: Expenses)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateExpenses(title: Expenses)

    @Delete
    fun deleteExpenses(title: Expenses)

}