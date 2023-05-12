package com.example.saidur.data.repository

import androidx.lifecycle.LiveData
import com.example.saidur.data.api.WeatherApi
import com.example.saidur.data.api.model.RestListResponse
import com.example.saidur.data.model.Weather
import com.example.saidur.database.dao.WeatherLocalDataDAO
import com.example.saidur.utils.AppResult
import com.example.saidur.utils.Utils.handleApiError
import com.example.saidur.utils.Utils.handleSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRepositoryImpl(private val api: WeatherApi, private val dao: WeatherLocalDataDAO) :
    WeatherRepository {

    override suspend fun getAllMovies(): AppResult<RestListResponse<Weather>> {
        return try {
            val response = api.getMovieList()
            if (response.isSuccessful) {
                response.body()?.let {
                    withContext(Dispatchers.IO) {
                        dao.AddAll(it.results)
                    }
                }
                handleSuccess(response)
            } else {
                handleApiError(response)
            }
        } catch (e: Exception) {
            AppResult.Error(e)
        }
    }

    override fun getAllOfflineDB(): LiveData<List<Weather>> {
        return dao.Get()
    }
}