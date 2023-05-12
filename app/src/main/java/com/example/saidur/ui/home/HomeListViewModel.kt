package com.example.saidur.ui.home

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saidur.data.model.Movie
import com.example.saidur.data.repository.MoviesRepository
import com.example.saidur.utils.AppResult
import com.example.saidur.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class HomeListViewModel(private val repository: MoviesRepository) : ViewModel() {

    val dataLoading = ObservableBoolean(false)

    val offlineMovieList = MutableLiveData<List<Movie>>()

    val movieList: LiveData<List<Movie>>
        get() = _movieList
    private var _movieList = MutableLiveData<List<Movie>>()
    val showError = SingleLiveEvent<String>()

    init {
        //fetchMovieList()
    }

    fun getAllOfflineDB(){
        offlineMovieList.value = repository.getAllOfflineDB().value
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
