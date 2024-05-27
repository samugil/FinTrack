package com.example.fintrack.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fintrack.data.Expenses
import com.example.fintrack.repository.FinTrackRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExpenseWithCategoryViewModel(private val repository: FinTrackRepository) : ViewModel() {

    private val _expensesWithCategories = MutableLiveData<List<ExpenseWithCategory>>()
    val expensesWithCategories: LiveData<List<ExpenseWithCategory>> get() = _expensesWithCategories
    private val _insertComplete = MutableLiveData<Boolean>()
    val insertComplete: LiveData<Boolean> get() = _insertComplete

    init {
        fetchExpensesWithCategories()
    }

    fun insertExpense(expense: Expenses) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertExpenses(expense)
            _insertComplete.postValue(true) // Indicar que a inserção foi concluída
            fetchExpensesWithCategories() // Refresh the data after insertion
        }
    }

    fun updateExpense(expense: Expenses) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateExpenses(expense)
            fetchExpensesWithCategories() // Refresh the data after update
        }
    }

    fun deleteExpense(expense: Expenses) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteExpenses(expense)
            fetchExpensesWithCategories() // Refresh the data after deletion
        }
    }

    private fun fetchExpensesWithCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            val expenses = repository.getAllExpenses().value ?: emptyList()
            val categories = repository.getAllCategories().value ?: emptyList()
            val expenseWithCategoryList = expenses.mapNotNull { expense ->
                val category = categories.find { it.id == expense.categoryId }
                if (category != null) {
                    ExpenseWithCategory(expense, category)
                } else {
                    null
                }
            }
            _expensesWithCategories.postValue(expenseWithCategoryList)
        }
    }
}
