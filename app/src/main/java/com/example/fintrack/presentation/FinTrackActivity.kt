package com.example.fintrack.presentation

import android.os.Bundle
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fintrack.R
import com.example.fintrack.data.AppDataBase
import com.example.fintrack.presentation.viewmodel.FinTrackViewModel
import com.example.fintrack.presentation.viewmodel.FinTrackAdapter
import com.example.fintrack.presentation.viewmodel.FinTrackViewModelFactory
import com.example.fintrack.repository.FinTrackRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView

class FinTrackActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var viewModel: FinTrackViewModel
    private lateinit var expensesAdapter: FinTrackAdapter
    private lateinit var ctnContent: LinearLayout
    private lateinit var rvExpenses: RecyclerView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fintrack_main)


        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        ctnContent = findViewById(R.id.ctn_content) // Ajuste o id para o layout correto
        rvExpenses = findViewById(R.id.rv_expenses_list)
        val btnAdd: FloatingActionButton = findViewById(R.id.btn_add)
        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener(this)

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

        viewModel.fetchExpensesWithCategories { expensesWithCategories ->
            if (expensesWithCategories.isNullOrEmpty()) {
                // Mostra empty state
                ctnContent.visibility = LinearLayout.VISIBLE
                rvExpenses.visibility = RecyclerView.GONE
            } else {
                // Esconde empty state
                ctnContent.visibility = LinearLayout.GONE
                rvExpenses.visibility = RecyclerView.VISIBLE
                expensesAdapter.setData(expensesWithCategories)
            }
        }

        btnAdd.setOnClickListener {
            // Lógica para adicionar uma nova despesa
        }
    }
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
            }
            R.id.nav_settings -> {
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
