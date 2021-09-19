package demo.movie.db.kotlin.ui.home

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import demo.movie.db.kotlin.data.api.model.RestListResponse
import demo.movie.db.kotlin.data.model.Movie
import demo.movie.db.kotlin.data.repository.MoviesRepository
import demo.movie.db.kotlin.utils.AppResult
import demo.movie.db.kotlin.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class HomeListViewModel(private val repository : MoviesRepository) : ViewModel() {

    val dataLoading = ObservableBoolean(false)
    /*val movieList = MutableLiveData<RestListResponse<Movie>>()*/
    val movieList: LiveData<List<Movie>>
        get() = _movieList
    private var _movieList = MutableLiveData<List<Movie>>()
    val showError = SingleLiveEvent<String>()

    init {
        //fetchMovieList()
    }

    fun fetchMovieList() {
        dataLoading.set(true)
        viewModelScope.launch {
            val result = repository.getAllMovies()

            dataLoading.set(false)
            when (result) {
                is AppResult.Success -> {
                    _movieList.value = result.successData.results
                    showError.value = null
                }
                is AppResult.Error -> showError.value = result.exception.message
            }
        }
    }
}
