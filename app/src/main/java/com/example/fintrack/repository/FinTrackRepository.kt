package com.example.fintrack.repository

import androidx.lifecycle.LiveData
import com.example.fintrack.data.AppDao
import com.example.fintrack.data.Category
import com.example.fintrack.data.Expenses

class FinTrackRepository(private val dao: AppDao) {

    fun getAllCategories(): LiveData<List<Category>> {
        return dao.getAllCategories()
    }

    fun getAllExpenses(): LiveData<List<Expenses>> {
        return dao.getAllExpenses()
    }

    fun getCategoryById(id: Int): LiveData<Category> {
        return dao.getCategoryById(id)
    }

    fun insertCategory(category: Category) {
        dao.insertCategory(category)
    }

    fun updateCategory(category: Category) {
        dao.updateCategory(category)
    }

    fun deleteCategory(category: Category) {
        dao.deleteCategory(category)
    }

    fun insertExpenses(expense: Expenses) {
        dao.insertExpenses(expense)
    }

    fun updateExpenses(expense: Expenses) {
        dao.updateExpenses(expense)
    }

    fun deleteExpenses(expense: Expenses) {
        dao.deleteExpenses(expense)
    }

    companion object {
        @Volatile
        private var INSTANCE: FinTrackRepository? = null

        fun getInstance(dao: AppDao): FinTrackRepository {
            return INSTANCE ?: synchronized(this) {
                val instance = FinTrackRepository(dao)
                INSTANCE = instance
                instance
            }
        }
    }
}
