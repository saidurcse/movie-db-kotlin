package com.example.saidur.ui.home

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saidur.data.model.WeatherInfoResponse
import com.example.saidur.data.repository.WeatherRepository
import com.example.saidur.utils.AppResult
import com.example.saidur.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class WeatherViewModel(private val repository: WeatherRepository) : ViewModel() {

    val dataLoading = ObservableBoolean(false)

    val offlineMovieList = MutableLiveData<WeatherInfoResponse>()

    val weatherData: LiveData<WeatherInfoResponse>
        get() = _weatherData
    private var _weatherData = MutableLiveData<WeatherInfoResponse>()
    val showError = SingleLiveEvent<String>()

    init {

    }

    fun getAllOfflineDB(){
        offlineMovieList.value = repository.getAllOfflineDB().value
    }

    fun fetchWeatherData(lat: String, lon: String) {
        dataLoading.set(true)
        viewModelScope.launch {
            val result = repository.getWeatherDataInfo(lat, lon)

            dataLoading.set(false)
            when (result) {
                is AppResult.Success -> {
                    _weatherData.value = result.successData
                    showError.value = null
                }
                is AppResult.Error -> showError.value = result.exception.message
            }
        }
    }
}
