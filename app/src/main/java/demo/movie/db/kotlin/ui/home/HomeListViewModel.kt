package demo.movie.db.kotlin.ui.home

import android.content.Context
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import demo.movie.db.kotlin.ui.home.model.Movie
import demo.movie.db.kotlin.model.MovieError
import demo.movie.db.kotlin.utils.SharedPreferencesHelper
import kotlinx.coroutines.launch

class HomeListViewModel(
    private val repo: HomeListRepo,
    private val sharedPreferencesHelper: SharedPreferencesHelper,
    private val context: Context
) : ViewModel() {
    val dataLoading = ObservableBoolean(false)

    val movieList: LiveData<List<Movie>>
        get() = _movieList
    private var _movieList = MutableLiveData<List<Movie>>()

    val showMessage: LiveData<String>
        get() = _showMessage
    private val _showMessage = MutableLiveData<String>()

    init {
        //fetchMovieList()
    }

    fun fetchMovieList() {
        viewModelScope.launch {
            try {
                dataLoading.set(true)
                val response = repo.fetchMovieList(context)
                _movieList.value = response.results
            } catch (error: MovieError) {
                _showMessage.value = error.message
            } finally {
                dataLoading.set(false)
            }
        }
    }
}
