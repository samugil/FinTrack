package com.example.fintrack.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fintrack.repository.FinTrackRepository

class ExpenseWithCategoryViewModelFactory(
    private val repository: FinTrackRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExpenseWithCategoryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ExpenseWithCategoryViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}