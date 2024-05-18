package com.example.fintrack.repository

import androidx.lifecycle.LiveData
import com.example.fintrack.data.AppDao
import com.example.fintrack.data.Category
import com.example.fintrack.data.Expenses
import com.example.fintrack.presentation.viewmodel.ExpenseWithCategory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FinTrackRepository(private val local: AppDao) {

    // LiveData para observação reativa
    fun getAllCategories(): LiveData<List<Category>> {
        return local.getAllCategories()
    }

    // coroutines para operações de banco de dados
    suspend fun getAllExpensesWithCategories(): List<ExpenseWithCategory> {
        return withContext(Dispatchers.IO) {
            val expenses = local.getAllExpenses()
            expenses.map { expense ->
                val category = local.getCategoryById(expense.categoryId)
                ExpenseWithCategory(expense, category)
            }
        }
    }

    suspend fun insertCategory(category: Category) {
        withContext(Dispatchers.IO) {
            local.insertCategory(category)
        }
    }

    suspend fun updateCategory(category: Category) {
        withContext(Dispatchers.IO) {
            local.updateCategory(category)
        }
    }

    suspend fun deleteCategory(category: Category) {
        withContext(Dispatchers.IO) {
            local.deleteCategory(category)
        }
    }

    suspend fun insertExpenses(expense: Expenses) {
        withContext(Dispatchers.IO) {
            local.insertExpenses(expense)
        }
    }

    suspend fun updateExpenses(expense: Expenses) {
        withContext(Dispatchers.IO) {
            local.updateExpenses(expense)
        }
    }

    suspend fun deleteExpenses(expense: Expenses) {
        withContext(Dispatchers.IO) {
            local.deleteExpenses(expense)
        }
    }
}
