import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fintrack.repository.FinTrackRepository


class CategoryViewModelFactory(
    private val repository: FinTrackRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CategoryViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
