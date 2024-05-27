package com.example.fintrack.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fintrack.data.Category
import com.example.fintrack.data.Expenses
import com.example.fintrack.repository.FinTrackRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FinTrackViewModel(private val repository: FinTrackRepository) : ViewModel() {

    val categories: LiveData<List<Category>> = repository.getAllCategories()

    fun fetchExpensesWithCategories(categoryId: Int, onResult: (List<ExpenseWithCategory>) -> Unit) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.getExpensesWithCategory(categoryId)
            }
            onResult(result)
        }
    }


    fun insertCategory(category: Category) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertCategory(category)
        }
    }

    fun updateCategory(category: Category) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateCategory(category)
        }
    }

    fun deleteCategory(category: Category) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteCategory(category)
        }
    }

    fun insertExpenses(expense: Expenses) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertExpenses(expense)
        }
    }

    fun updateExpenses(expense: Expenses) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateExpenses(expense)
        }
    }

    fun deleteExpenses(expense: Expenses) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteExpenses(expense)
        }
    }
}
