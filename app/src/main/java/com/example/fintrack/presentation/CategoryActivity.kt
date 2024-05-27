package com.example.fintrack.presentation

import CategoryViewModel
import CategoryViewModelFactory
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.fintrack.R
import com.example.fintrack.data.AppDataBase
import com.example.fintrack.data.Category
import com.example.fintrack.repository.FinTrackRepository
import com.google.android.material.textfield.TextInputLayout
import java.io.Serializable

class CategoryActivity : AppCompatActivity() {

    private lateinit var viewModel: CategoryViewModel
    private var selectedColor: String = ""
    private var selectedIcon: Int = 0

    companion object {
        private const val REQUEST_SELECT_COLOR = 1001
        private const val REQUEST_SELECT_ICON = 1002
        private const val CATEGORY_KEY = "category_key"

        fun start(context: Context, category: Category?): Intent {
            val intent = Intent(context, CategoryActivity::class.java)
            if (category != null) {
                intent.putExtra(CATEGORY_KEY, category as Serializable)
            }
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.category_add)

        val repository = FinTrackRepository(AppDataBase.getInstance(this).appDao())
        val factory = CategoryViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(CategoryViewModel::class.java)

        // Recuperar a categoria, se houver
        val category: Category? = intent.getSerializableExtra(CATEGORY_KEY) as? Category

        setupButtonClickListeners()

        // Observa a navegação de volta para a lista de categorias
        viewModel.navigateToCategoryList.observe(this, Observer { navigate ->
            if (navigate) {
                finish()
                viewModel.onCategoryListNavigated()
            }
        })

        category?.let {
            // Atualizar a UI com os dados da categoria, se necessário
            findViewById<TextInputLayout>(R.id.til_new_category).editText?.setText(it.title)
            selectedColor = it.color
            selectedIcon = it.icon
            updateColorPreview()
            updateIconPreview()
        }
    }

    private fun setupButtonClickListeners() {
        val btnColorSelect = findViewById<Button>(R.id.btn_color_select)
        val btnIconSelect = findViewById<Button>(R.id.btn_icon_select)

        btnColorSelect.setOnClickListener {
            val intent = Intent(this, CategoryColorActivity::class.java)
            startActivityForResult(intent, REQUEST_SELECT_COLOR)
        }

        btnIconSelect.setOnClickListener {
            val intent = Intent(this, CategoryIconActivity::class.java)
            startActivityForResult(intent, REQUEST_SELECT_ICON)
        }

        val btnCategoryCreate = findViewById<Button>(R.id.btn_category_create)
        btnCategoryCreate.setOnClickListener {
            val title = findViewById<TextInputLayout>(R.id.til_new_category).editText?.text.toString()
            if (title.isNotEmpty() && selectedColor != "" && selectedIcon != 0) {
                val category = Category(title = title, color = selectedColor.toString(), icon = selectedIcon)
                viewModel.insertCategory(category)
            } else {
                Toast.makeText(this, "Por favor preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_SELECT_COLOR -> {
                    selectedColor = data?.getStringExtra("selectedColor") ?: ""
                    updateColorPreview()
                }
                REQUEST_SELECT_ICON -> {
                    selectedIcon = data?.getIntExtra("selectedIcon", 0) ?: 0
                    updateIconPreview()
                }
            }
        }
    }

    private fun updateColorPreview() {
        val imgColorCategory = findViewById<ImageView>(R.id.img_color_category)
        imgColorCategory.setBackgroundColor(Color.parseColor(selectedColor))
    }

    private fun updateIconPreview() {
        val imgIconCategory = findViewById<ImageView>(R.id.img_icon_category)
        if (selectedIcon != 0) {
            imgIconCategory.setImageResource(selectedIcon)
        }
    }

    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setMessage("Ao fazer isso irá cancelar a inclusão de uma categoria")
            .setPositiveButton("Sim") { _, _ -> super.onBackPressed() }
            .setNegativeButton("Não", null)
            .show()
    }
}
