package com.example.fintrack.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "expenses")
data class Expenses(
    @PrimaryKey (autoGenerate = true) val id: Int = 0,
    val categoryTitle: String,
    val title: String,
    val price: Double
)