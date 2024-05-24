package com.example.fintrack.presentation

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.fintrack.data.AppDataBase
import com.example.fintrack.data.Category
import com.example.fintrack.databinding.ActivityCategoryBinding
import com.example.fintrack.presentation.viewmodel.FinTrackViewModel
import com.example.fintrack.presentation.viewmodel.FinTrackViewModelFactory
import com.example.fintrack.repository.FinTrackRepository


class CategoryActivity : AppCompatActivity() {


    private lateinit var binding: ActivityCategoryBinding
    private var selectedColor: Int = Color.TRANSPARENT
    private var selectedIcon: Int = 0

    private lateinit var colorActivityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var iconActivityResultLauncher: ActivityResultLauncher<Intent>

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)


        colorActivityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                selectedColor = result.data?.getIntExtra("selectedColor", Color.TRANSPARENT) ?: Color.TRANSPARENT
                updateColorPreview() // Atualiza a pré-visualização da cor
            }
        }

        iconActivityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                selectedIcon = result.data?.getIntExtra("selectedIcon", 0) ?: 0
                updateIconPreview() // Atualiza a pré-visualização do ícone
            }
        }

        binding.btnColorSelect.setOnClickListener {
            val intent = Intent(this, ColorCategoryActivity::class.java)
            colorActivityResultLauncher.launch(intent)
        }

        binding.btnIconSelect.setOnClickListener {
            val intent = Intent(this, IconCategoryActivity::class.java)
            iconActivityResultLauncher.launch(intent)
        }

        binding.btnCategoryCreate.setOnClickListener {
            val title = binding.tilNewCategory.editText?.text.toString()
            if (title.isNotEmpty() && selectedColor != Color.TRANSPARENT && selectedIcon != 0) {
                val category = Category(title = title, color = selectedColor.toString(), icon = selectedIcon)
                val db = AppDataBase.getInstance(this)
                db.expensesDao().insertCategory(category)
                setResult(Activity.RESULT_OK)
                finish()
            }
        }
    }

    private fun updateColorPreview() {
        binding.imgColorCategory.setBackgroundColor(selectedColor)
    }

    private fun updateIconPreview() {
        if (selectedIcon != 0) {
            binding.imgIconCategory.setImageResource(selectedIcon)
        }
   }
}
