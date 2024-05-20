package com.example.fintrack.presentation

import android.app.Activity
import android.content.Intent
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
            val selectedColor = when {
                binding.rbWhite.isChecked -> R.color.white
                binding.rbBlack.isChecked -> R.color.black
                // Continue with other radio buttons
                else -> 0
            }

            if (selectedColor != 0) {
                val resultIntent = Intent()
                resultIntent.putExtra("selectedColor", selectedColor)
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            }
        }
    }
}