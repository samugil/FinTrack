package com.example.fintrack.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fintrack.repository.FinTrackRepository

class FinTrackViewModelFactory(
    private val repository: FinTrackRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FinTrackViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FinTrackViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
