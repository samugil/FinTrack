package com.example.fintrack.presentation

import CategoryViewModel
import CategoryViewModelFactory
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.fintrack.R
import com.example.fintrack.data.AppDataBase
import com.example.fintrack.data.Category
import com.example.fintrack.data.Expenses
import com.example.fintrack.presentation.viewmodel.CategoryAdapter
import com.example.fintrack.presentation.viewmodel.ExpenseWithCategoryViewModel
import com.example.fintrack.presentation.viewmodel.ExpenseWithCategoryViewModelFactory
import com.example.fintrack.repository.FinTrackRepository

class ExpensesActivity : AppCompatActivity() {
    private lateinit var viewModel: CategoryViewModel
    private lateinit var viewModelWithCategory: ExpenseWithCategoryViewModel
    private lateinit var categorySpinner: Spinner
    private lateinit var expenseNameEditText: EditText
    private lateinit var expensePriceEditText: EditText

    private var selectedCategory: Category? = null
    private lateinit var listCategory:List<Category>

    companion object {
        fun start(context: Context, category: Category?): Intent {
            val intent = Intent(context, ExpensesActivity::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.expenses_add)

        categorySpinner = findViewById(R.id.categorySpinner)
        expenseNameEditText = findViewById(R.id.edt_nome_despesa)
        expensePriceEditText = findViewById(R.id.edt_price)

        // Inicializando o ViewModel usando ViewModelProvider
        val repository = FinTrackRepository(AppDataBase.getInstance(this).appDao())
        val factory = ExpenseWithCategoryViewModelFactory(repository)
        val factoryCategory = CategoryViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factoryCategory).get(CategoryViewModel::class.java)
        viewModelWithCategory = ViewModelProvider(this, factory).get(ExpenseWithCategoryViewModel::class.java)


        // Observando as categorias e atualizando o Spinner
        viewModel.categories.observe(this, Observer { categories ->
            listCategory = categories
            val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listCategory.map { it.title })
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            categorySpinner.adapter = adapter
            selectedCategory = categories.first()
        })

        categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val selectedItem = p0?.getItemAtPosition(p2).toString()
                for (categoryItem in listCategory) {
                    if (categoryItem.title == selectedItem) {
                        selectedCategory = categoryItem
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                Toast.makeText(applicationContext, "Selecione uma categoria", Toast.LENGTH_SHORT).show()
            }
        }



        val btnCreate = findViewById<Button>(R.id.createButton)
        btnCreate.setOnClickListener {
            val name = expenseNameEditText.text.toString()
            val priceText = expensePriceEditText.text.toString()

            if (name.isNotEmpty() && priceText.isNotEmpty() && selectedCategory != null) {
                val price = priceText.toDoubleOrNull()
                if (price != null && price > 0) {
                    val expense = Expenses(title = name, price = price, categoryId = selectedCategory!!.id)
                    viewModelWithCategory.insertExpense(expense)
                    // Limpar campos após a inserção
                    expenseNameEditText.text.clear()
                    expensePriceEditText.text.clear()
                    categorySpinner.setSelection(0)
                } else {
                    Toast.makeText(this, "Insira um preço válido", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }

        // Observe the insertComplete LiveData
        viewModelWithCategory.insertComplete.observe(this, Observer { isComplete ->
            if (isComplete) {
                Toast.makeText(this, "Despesa adicionada com sucesso", Toast.LENGTH_SHORT).show()
                finish()
            }
        })
    }
}
