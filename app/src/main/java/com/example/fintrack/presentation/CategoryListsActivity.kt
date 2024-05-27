import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fintrack.R
import com.example.fintrack.data.AppDataBase
import com.example.fintrack.data.Category
import com.example.fintrack.presentation.CategoryActivity
import com.example.fintrack.presentation.FinTrackActivity
import com.example.fintrack.presentation.viewmodel.CategoryAdapter
import com.example.fintrack.repository.FinTrackRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class CategoryListsActivity : AppCompatActivity() {

    private lateinit var viewModel: CategoryViewModel
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var ctnContent: LinearLayout
    private lateinit var rvCategoryList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_lists)

        ctnContent = findViewById(R.id.ctn_content_category)
        rvCategoryList = findViewById(R.id.rv_category_list)
        val btnAdd: FloatingActionButton = findViewById(R.id.btn_add_category)
        val btnHome: FloatingActionButton = findViewById(R.id.btn_home)

        val repository = FinTrackRepository(AppDataBase.getInstance(this).appDao())
        val factory = CategoryViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(CategoryViewModel::class.java)

        categoryAdapter = CategoryAdapter()
        rvCategoryList.layoutManager = LinearLayoutManager(this)
        rvCategoryList.adapter = categoryAdapter

        // Observa as categorias e atualiza o RecyclerView quando houver mudanças
        viewModel.categories.observe(this) { categories ->
            if (categories.isNullOrEmpty()) {
                findViewById<LinearLayout>(R.id.ctn_content_category).visibility = View.VISIBLE
                rvCategoryList.visibility = View.GONE
            } else {
                findViewById<LinearLayout>(R.id.ctn_content_category).visibility = View.GONE
                rvCategoryList.visibility = View.VISIBLE
                categoryAdapter.setData(categories)
            }
        }

        btnAdd.setOnClickListener {
            showMessage(it, "Adicione Categorias")
            openCategoryAdd()
        }

        btnHome.setOnClickListener {
            showMessage(it, "Menu Principal")
            openHome()
        }

    }

    private fun showMessage(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
            .setAction("Action", null)
            .show()

    }

    private fun openCategoryAdd(category: Category? = null) {
        val intent = CategoryActivity.start(this, category)
        startActivity(intent)
    }

    private fun openHome(category: Category? = null) {
        val intent = FinTrackActivity.start(this)
        startActivity(intent)
    }

    companion object {
        // Função estática para iniciar esta atividade
        fun start(context: Context, category: Category?): Intent {
            val intent = Intent(context, CategoryListsActivity::class.java)
            // intent.putExtra("category", category)
            return intent
        }
    }

    override fun onBackPressed() {
        super.onBackPressed() // Deixe este método vazio para desativar o botão voltar nativo do dispositivo
    }
}
