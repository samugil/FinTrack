package com.example.fintrack.data

import android.media.Image
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category(
    @PrimaryKey val id: Int = 1,
    val title: String,
    val color: String,
    val icon: Image
)