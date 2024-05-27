package com.example.fintrack.presentation

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fintrack.R
import com.example.fintrack.data.AppDataBase
import com.example.fintrack.presentation.viewmodel.ExpenseWithCategory
import com.example.fintrack.presentation.viewmodel.FinTrackViewModel
import com.example.fintrack.presentation.viewmodel.FinTrackAdapter
import com.example.fintrack.presentation.viewmodel.FinTrackViewModelFactory
import com.example.fintrack.repository.FinTrackRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FinTrackActivity : AppCompatActivity() {

    private lateinit var viewModel: FinTrackViewModel
    private lateinit var expensesAdapter: FinTrackAdapter
    private lateinit var ctnContent: LinearLayout
    private lateinit var rvExpenses: RecyclerView
    private lateinit var tvTotalSpentLabel: TextView
    private lateinit var tvTotalSpentValue: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fintrack_main)

        ctnContent = findViewById(R.id.ctn_content) // Ajuste o id para o layout correto
        rvExpenses = findViewById(R.id.rv_expenses_list)
        tvTotalSpentLabel = findViewById(R.id.tv_total_spent_label)
        tvTotalSpentValue = findViewById(R.id.tv_total_spent_value)
        val btnAdd: FloatingActionButton = findViewById(R.id.btn_add)

        // Inicializando o ViewModel usando ViewModelProvider
        val repository = FinTrackRepository(AppDataBase.getInstance(this).expensesDao())
        val factory = FinTrackViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(FinTrackViewModel::class.java)

        expensesAdapter = FinTrackAdapter()
        rvExpenses.layoutManager = LinearLayoutManager(this)
        rvExpenses.adapter = expensesAdapter

        // Observar as categorias e Despesas
        viewModel.categories.observe(this, Observer { categories ->

        })

        val selectedCategoryId: Int = 1

        viewModel.fetchExpensesWithCategories(selectedCategoryId) { expensesWithCategories ->
            if (expensesWithCategories.isNullOrEmpty()) {
                // Mostra empty state
                ctnContent.visibility = LinearLayout.VISIBLE
                rvExpenses.visibility = RecyclerView.GONE
                tvTotalSpentLabel.visibility = TextView.GONE
                tvTotalSpentValue.visibility = TextView.GONE
            } else {
                // Esconde empty state
                ctnContent.visibility = LinearLayout.GONE
                rvExpenses.visibility = RecyclerView.VISIBLE
                tvTotalSpentLabel.visibility = TextView.VISIBLE
                tvTotalSpentValue.visibility = TextView.VISIBLE
                expensesAdapter.setData(expensesWithCategories)
                updateTotalSpent(expensesWithCategories)
            }
        }

        btnAdd.setOnClickListener {
            // Lógica para adicionar uma nova despesa
        }
    }

    private fun updateTotalSpent(expensesWithCategories: List<ExpenseWithCategory>) {
        val totalSpent = expensesWithCategories.sumOf { it.expense.price }
        tvTotalSpentValue.text = "Total Spent: R$%.2f".format(totalSpent)
    }
}
