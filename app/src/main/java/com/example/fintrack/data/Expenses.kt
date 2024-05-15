package com.example.fintrack.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Expenses(
    @PrimaryKey val id: Int = 1,
    val categoryTitle: String,
    val title: String,
    val price: Double
)