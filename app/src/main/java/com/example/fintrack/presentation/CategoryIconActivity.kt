package com.example.fintrack.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fintrack.R

class CategoryIconActivity : AppCompatActivity() {

    private lateinit var btnIcon: Button
    private lateinit var rgIcon1: RadioGroup
    private lateinit var rgIcon2: RadioGroup
    private lateinit var rgIcon3: RadioGroup
    private lateinit var rgIcon4: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.category_icons)


        btnIcon = findViewById(R.id.btn_icon)
        rgIcon1 = findViewById(R.id.rg_ic1)
        rgIcon2 = findViewById(R.id.rg_ic2)
        rgIcon3 = findViewById(R.id.rg_ic3)
        rgIcon4 = findViewById(R.id.rg_ic4)


        btnIcon.setOnClickListener {
            // Obtém o ícone selecionado
            val selectedIcon = getSelectedIcon()
            if (selectedIcon != -1) {
                val resultIntent = Intent().apply {
                    putExtra("selectedIcon", selectedIcon)
                }
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            } else {
                // Mostra uma mensagem se nenhum ícone estiver selecionado
                Toast.makeText(this, "Por favor selecione um ícone para sua categoria", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Função que retorna o ID do drawable do ícone selecionado
    private fun getSelectedIcon(): Int {
        // Obtém o ID do RadioButton selecionado em cada RadioGroup
        val checkedRadioButtonId = when {
            rgIcon1.checkedRadioButtonId != -1 -> rgIcon1.checkedRadioButtonId
            rgIcon2.checkedRadioButtonId != -1 -> rgIcon2.checkedRadioButtonId
            rgIcon3.checkedRadioButtonId != -1 -> rgIcon3.checkedRadioButtonId
            rgIcon4.checkedRadioButtonId != -1 -> rgIcon4.checkedRadioButtonId
            else -> -1
        }


        return when (checkedRadioButtonId) {
            R.id.rb_fuel -> R.drawable.layer_gas_station
            R.id.rb_wifi -> R.drawable.layer_wifi
            R.id.rb_electricity -> R.drawable.layer_eletricity
            R.id.rb_car -> R.drawable.layer_car
            R.id.rb_credit_card -> R.drawable.layer_credit_card
            R.id.rb_key -> R.drawable.layer_key
            R.id.rb_shopping_cart -> R.drawable.layer_shopping_cart
            R.id.rb_water_drop -> R.drawable.layer_water_drop
            R.id.rb_home -> R.drawable.layer_home
            R.id.rb_game_control -> R.drawable.layer_game_control
            R.id.rb_clothes -> R.drawable.layer_clothes
            R.id.rb_graphic -> R.drawable.layer_graphic
            else -> -1
        }
    }

    override fun onBackPressed() {
        // Verifica se algum ícone está selecionado
        if (getSelectedIcon() == -1) {
            Toast.makeText(this, "Por favor selecione um ícone para sua categoria", Toast.LENGTH_SHORT).show()
        } else {
            super.onBackPressed()
        }
    }
}
