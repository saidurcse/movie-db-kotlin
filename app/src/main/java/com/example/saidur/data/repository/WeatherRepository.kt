package com.example.saidur.data.repository

import androidx.lifecycle.LiveData
import com.example.saidur.data.model.WeatherInfoResponse
import com.example.saidur.utils.AppResult

interface WeatherRepository {
    suspend fun getWeatherDataInfo(): AppResult<WeatherInfoResponse>
    fun getAllOfflineDB(): LiveData<WeatherInfoResponse>
}
