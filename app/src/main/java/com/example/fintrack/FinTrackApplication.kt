package com.example.fintrack

import android.app.Application
import com.example.fintrack.data.AppDataBase
import com.example.fintrack.repository.FinTrackRepository

class FinTrackApplication : Application() {
    val database by lazy { AppDataBase.getInstance(this) }
    val repository by lazy { FinTrackRepository(database.appDao()) }
}