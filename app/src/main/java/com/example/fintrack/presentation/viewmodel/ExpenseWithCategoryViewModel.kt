package com.example.fintrack.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fintrack.data.Category
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
            fetchExpensesWithCategories() // Refresh depois da inclusão
            _insertComplete.postValue(true) // Indicar que a inserção foi concluída
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
            val expensesAll = repository.getAllExpenses()
            val categories = repository.getAllCategory()


            val expenseWithCategoryList: MutableList<ExpenseWithCategory> = mutableListOf()
            if (expensesAll.isNotEmpty() && categories.isNotEmpty()) {
                for (categoria in categories) {
                    var categoryValue: Double = 0.0
                    expensesAll.forEach {
                        if (it.categoryId == categoria.id) {
                            categoryValue += it.price
                        }
                    }
                    expenseWithCategoryList.add(
                        ExpenseWithCategory(
                            categoria.color,
                            categoria.icon,
                            categoryValue,
                            categoria.title
                        )
                    )
                }
            }
            _expensesWithCategories.postValue(expenseWithCategoryList)
        }
    }
}
