package com.example.fintrack.presentation

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.widget.Button
import android.widget.RadioGroup
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fintrack.R

class CategoryColorActivity : AppCompatActivity() {
    private lateinit var btnColor: Button
    private lateinit var rgColor1: RadioGroup
    private lateinit var rgColor2: RadioGroup
    private lateinit var rgColor3: RadioGroup
    private lateinit var rgColor4: RadioGroup

    private lateinit var rbWhite: RadioButton
    private lateinit var rbBlack: RadioButton
    private lateinit var rbRed: RadioButton
    private lateinit var rbViolet: RadioButton
    private lateinit var rbOceanBlue: RadioButton
    private lateinit var rbBlue: RadioButton
    private lateinit var rbWaterBlue: RadioButton
    private lateinit var rbWaterGreen: RadioButton
    private lateinit var rbOceanGreen: RadioButton
    private lateinit var rbLightYellow: RadioButton
    private lateinit var rbWaterMediumYellow: RadioButton
    private lateinit var rbLightOrange: RadioButton
    private lateinit var rbMediumOrange: RadioButton
    private lateinit var rbBrown: RadioButton
    private lateinit var rbGrey: RadioButton
    private lateinit var rbMagenta: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.category_color)

        // Inicialize os componentes de interface usando findViewById
        btnColor = findViewById(R.id.btn_color)
        rgColor1 = findViewById(R.id.rg_color1)
        rgColor2 = findViewById(R.id.rg_color2)
        rgColor3 = findViewById(R.id.rg_color3)
        rgColor4 = findViewById(R.id.rg_color4)

        rbWhite = findViewById(R.id.rb_white)
        rbBlack = findViewById(R.id.rb_black)
        rbRed = findViewById(R.id.rb_red)
        rbViolet = findViewById(R.id.rb_violet)
        rbOceanBlue = findViewById(R.id.rb_ocean_blue)
        rbBlue = findViewById(R.id.rb_blue)
        rbWaterBlue = findViewById(R.id.rb_water_blue)
        rbWaterGreen = findViewById(R.id.rb_water_green)
        rbOceanGreen = findViewById(R.id.rb_ocean_green)
        rbLightYellow = findViewById(R.id.rb_light_yellow)
        rbWaterMediumYellow = findViewById(R.id.rbWaterMediumYellow)
        rbLightOrange = findViewById(R.id.rb_light_orange)
        rbMediumOrange = findViewById(R.id.rbMediumOrange)
        rbBrown = findViewById(R.id.rb_brown_)
        rbGrey = findViewById(R.id.rb_grey)
        rbMagenta = findViewById(R.id.rbMagenta)

        btnColor.setOnClickListener {
            val selectedColor = getSelectedColor()
            if (selectedColor != "") {
                val resultIntent = Intent().apply {
                    putExtra("selectedColor", selectedColor)
                }
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            } else {
                // Mostra uma mensagem se nenhum ícone estiver selecionado
                Toast.makeText(this, "Por favor selecione uma cor para sua categoria", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getSelectedColor(): String {
        val checkedRadioButtonId = when {
            rgColor1.checkedRadioButtonId != -1 -> rgColor1.checkedRadioButtonId
            rgColor2.checkedRadioButtonId != -1 -> rgColor2.checkedRadioButtonId
            rgColor3.checkedRadioButtonId != -1 -> rgColor3.checkedRadioButtonId
            rgColor4.checkedRadioButtonId != -1 -> rgColor4.checkedRadioButtonId
            else -> -1
        }

        val selectedColor = when (checkedRadioButtonId) {
            rbWhite.id -> "#FFFFFF"
            rbBlack.id -> "#FF000000"
            rbRed.id -> "#F54336"
            rbViolet.id -> "#EE82EE" // Violet
            rbOceanBlue.id -> "#0077BE" // Ocean Blue
            rbBlue.id -> "#1A96F0"
            rbWaterBlue.id -> "#76D7EA" // Water Blue
            rbWaterGreen.id -> "#00FF7F" // Water Green
            rbOceanGreen.id -> "#2E8B57" // Ocean Green
            rbLightYellow.id -> "#FFFFE0" // Light Yellow
            rbWaterMediumYellow.id -> "#FFD700" // Medium Yellow
            rbLightOrange.id -> "#FFA07A" // Light Orange
            rbMediumOrange.id -> "#FFA500" // Medium Orange
            rbBrown.id -> "#A52A2A" // Brown
            rbGrey.id -> "#607D8A"
            rbMagenta.id -> "#FF00FF" // Magenta
            else -> "#00607D8A"
        }
        return selectedColor
    }

    override fun onBackPressed() {
        // Verifica se algum ícone está selecionado
        if (getSelectedColor() == "") {
            Toast.makeText(this, "Por favor selecione uma cor para sua categoria", Toast.LENGTH_SHORT).show()
        } else {
            super.onBackPressed()
        }
    }

}
