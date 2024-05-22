package com.example.fintrack.presentation

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fintrack.R
import com.example.fintrack.databinding.ActivityColorCategoryBinding

class ColorCategoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityColorCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityColorCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnColor.setOnClickListener {
            val selectedColor = getSelectedColor ()
            val resultIntent = Intent ().apply {
                putExtra("selectedColor", selectedColor)
            }
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }

    private fun getSelectedColor(): Int {
        val checkedRadioButtonId = when {
            binding.rgColor1.checkedRadioButtonId != -1 -> binding.rgColor1.checkedRadioButtonId
            binding.rgColor2.checkedRadioButtonId != -1 -> binding.rgColor2.checkedRadioButtonId
            binding.rgColor3.checkedRadioButtonId != -1 -> binding.rgColor3.checkedRadioButtonId
            binding.rgColor4.checkedRadioButtonId != -1 -> binding.rgColor4.checkedRadioButtonId
            else -> -1
        }

        val selectedColor = when (checkedRadioButtonId) {
            binding.rbWhite.id -> Color.WHITE
            binding.rbBlack.id -> Color.BLACK
            binding.rbRed.id -> Color.RED
            binding.rbViolet.id -> Color.parseColor("#EE82EE") // Violet
            binding.rbOceanBlue.id -> Color.parseColor("#0077BE") // Ocean Blue
            binding.rbBlue.id -> Color.BLUE
            binding.rbWaterBlue.id -> Color.parseColor("#76D7EA") // Water Blue
            binding.rbWaterGreen.id -> Color.parseColor("#00FF7F") // Water Green
            binding.rbOceanGreen.id -> Color.parseColor("#2E8B57") // Ocean Green
            binding.rbLightYellow.id -> Color.parseColor("#FFFFE0") // Light Yellow
            binding.rbWaterMediumYellow.id -> Color.parseColor("#FFD700") // Medium Yellow
            binding.rbLightOrange.id -> Color.parseColor("#FFA07A") // Light Orange
            binding.rbMediumOrange.id -> Color.parseColor("#FFA500") // Medium Orange
            binding.rbBrown.id -> Color.parseColor("#A52A2A") // Brown
            binding.rbGrey.id -> Color.GRAY
            binding.rbMagenta.id -> Color.parseColor("#FF00FF") // Magenta
            else -> Color.TRANSPARENT
        }
        return selectedColor
    }
}