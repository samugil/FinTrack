import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fintrack.data.Category
import com.example.fintrack.repository.FinTrackRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryViewModel(private val repository: FinTrackRepository) : ViewModel() {

    val categories: LiveData<List<Category>> = repository.getAllCategories()

    // LiveData para navegação de volta para a lista de categorias
    private val _navigateToCategoryList = MutableLiveData<Boolean>()
    val navigateToCategoryList: LiveData<Boolean> get() = _navigateToCategoryList

    init {
        // Inicializa o LiveData como falso
        _navigateToCategoryList.value = false
    }

    fun insertCategory(category: Category) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertCategory(category)
            // Após a inserção, navega de volta para a lista de categorias
                onCategoryListNavigated()
        }
    }

    fun updateCategory(category: Category) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateCategory(category)
        }
    }

    fun deleteCategory(category: Category) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteCategory(category)
        }
    }

    // Método para indicar que a navegação para a lista de categorias foi concluída
    fun onCategoryListNavigated() {
        viewModelScope.launch(Dispatchers.Main) {
            _navigateToCategoryList.value = true
        }
    }
}
