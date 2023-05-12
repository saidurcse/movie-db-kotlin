package com.example.saidur.data.repository

import androidx.lifecycle.LiveData
import com.example.saidur.data.api.model.RestListResponse
import com.example.saidur.data.model.Weather
import com.example.saidur.utils.AppResult

interface WeatherRepository {
    suspend fun getAllMovies(): AppResult<RestListResponse<Weather>>
    fun getAllOfflineDB(): LiveData<List<Weather>>
}
