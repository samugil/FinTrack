package com.example.fintrack.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fintrack.R
import com.example.fintrack.data.AppDataBase
import com.example.fintrack.data.Category
import com.example.fintrack.presentation.viewmodel.ExpenseWithCategory
import com.example.fintrack.presentation.viewmodel.ExpenseWithCategoryAdapter
import com.example.fintrack.presentation.viewmodel.ExpenseWithCategoryViewModel
import com.example.fintrack.presentation.viewmodel.ExpenseWithCategoryViewModelFactory
import com.example.fintrack.repository.FinTrackRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class FinTrackActivity : AppCompatActivity() {

    private lateinit var viewModel: ExpenseWithCategoryViewModel
    private lateinit var expensesAdapter: ExpenseWithCategoryAdapter
    private lateinit var ctnContent: LinearLayout
    private lateinit var rvExpenses: RecyclerView
    private lateinit var dialog: AlertDialog


    private lateinit var tvTotalSpentLabel: TextView
    private lateinit var tvTotalSpentValue: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fintrack_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        ctnContent = findViewById(R.id.ctn_content)
        rvExpenses = findViewById(R.id.rv_expenses_list)
        tvTotalSpentLabel = findViewById(R.id.tv_total_spent_label)
        tvTotalSpentValue = findViewById(R.id.tv_total_spent_value)
        val btnAdd: FloatingActionButton = findViewById(R.id.btn_add)
        val btnCategory: FloatingActionButton = findViewById(R.id.btn_categories)

        val repository = FinTrackRepository(AppDataBase.getInstance(this).appDao())
        val factory = ExpenseWithCategoryViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(ExpenseWithCategoryViewModel::class.java)

        expensesAdapter = ExpenseWithCategoryAdapter()
        rvExpenses.layoutManager = LinearLayoutManager(this)
        rvExpenses.adapter = expensesAdapter

        viewModel.expensesWithCategories.observe(this, Observer { listExpenses ->
            expensesAdapter.submitList(listExpenses)
            ctnContent.visibility = if (listExpenses.isEmpty()) View.VISIBLE else View.GONE
        })

        btnAdd.setOnClickListener {
            showMessage(it, "Here's a Snackbar")
            openExpensesAdd()
        }

        btnCategory.setOnClickListener {
            showMessage(it, "Here's a Snackbar")
            openCategoryAdd()
        }
    }

    private fun showMessage(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
            .setAction("Action", null)
            .show()
    }

    private fun openCategoryAdd(category: Category? = null) {
        val intent = CategoryListsActivity.start(this, category)
        startActivity(intent)
    }

    private fun openExpensesAdd(category: Category? = null) {
        val intent = ExpensesActivity.start(this, category)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_fintrack, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.sobre -> {
                showAlertDialogAbout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        fun start(context: Context): Intent {
            val intent = Intent(context, FinTrackActivity::class.java)
            return intent
        }
    }

    private fun showAlertDialogAbout() {
        val builder = AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.dialog_settings_about, null)
        builder.setView(view)

        val btnClose = view.findViewById<ImageButton>(R.id.btnClose)
        btnClose.setOnClickListener {
            dialog.dismiss()
        }

        dialog = builder.create()
        dialog.show()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    private fun updateTotalSpent(expensesWithCategories: List<ExpenseWithCategory>) {
        val totalSpent = expensesWithCategories.sumOf { it.expense.price }
        tvTotalSpentValue.text = "Total Spent: R$%.2f".format(totalSpent)
    }
}
