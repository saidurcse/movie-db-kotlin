package com.example.saidur.data.repository

import androidx.lifecycle.LiveData
import com.example.saidur.data.api.WeatherApi
import com.example.saidur.data.model.WeatherInfoResponse
import com.example.saidur.database.dao.WeatherLocalDataDAO
import com.example.saidur.utils.AppResult
import com.example.saidur.utils.Utils.handleApiError
import com.example.saidur.utils.Utils.handleSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRepositoryImpl(private val api: WeatherApi, private val dao: WeatherLocalDataDAO) :
    WeatherRepository {

    override suspend fun getWeatherDataInfo(): AppResult<WeatherInfoResponse> {
        return try {
            val response = api.getWeatherData("32.8140", "96.9489")
            if (response.isSuccessful) {
                response.body()?.let {
                    withContext(Dispatchers.IO) {
                        //dao.AddAll(it.results)
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

    override fun getAllOfflineDB(): LiveData<WeatherInfoResponse> {
        return dao.Get()
    }
}