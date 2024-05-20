package com.example.fintrack.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.fintrack.data.AppDataBase
import com.example.fintrack.data.Category
import com.example.fintrack.databinding.ActivityCategoryBinding


class CategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryBinding
    private var selectedColor: Int = 0
    private var selectedIcon: Int = 0

    private lateinit var colorActivityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var iconActivityResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Register the ActivityResultLaunchers
        colorActivityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                selectedColor = result.data?.getIntExtra("selectedColor", 0) ?: 0
                // Update the UI to reflect the selected color
            }
        }

        iconActivityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                selectedIcon = result.data?.getIntExtra("SelectedIcon", 0) ?: 0
                // Update the UI to reflect the selected icon
            }
        }

        binding.btnColorSelect.setOnClickListener {
            val intent = Intent(this, ColorCategoryActivity::class.java)
            colorActivityResultLauncher.launch(intent)
        }

        binding.btnIcon.setOnClickListener {
            val intent = Intent(this, IconCategoryActivity::class.java)
            iconActivityResultLauncher.launch(intent)
        }

        binding.btnCategoryCreate.setOnClickListener {
            val title = binding.tilNewCategory.editText?.text.toString()
            if (title.isNotEmpty() && selectedColor != 0 && selectedIcon != 0) {
                val category = Category(title = title, color = selectedColor, icon = selectedIcon)
                val db = AppDataBase.getInstance(this)
                db.AppDataBase.Dao().insertCategory(category)
                setResult(Activity.RESULT_OK)
                finish()
            }
        }

    }

    companion object {
        const val REQUEST_COLOR = 1
        const val REQUEST_ICON = 2
    }
}