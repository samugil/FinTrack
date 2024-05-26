package com.example.fintrack.presentation

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.fintrack.R

class FinTrackSplashScreen : AppCompatActivity() {

    // Criada uma splash screen personalizada, com tempo de apresentação em tela
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Ativar modo noturno de acordo com as configurações do sistema
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        setContentView(R.layout.fintrack_splash_screen)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, FinTrackActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }

}