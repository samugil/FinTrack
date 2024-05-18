package com.example.fintrack.data


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Category")
data class Category(
    @PrimaryKey (autoGenerate = true) val id: Int = 0,
    val title: String,
    val color: Int,
    val icon: Int
)