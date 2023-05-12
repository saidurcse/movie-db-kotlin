package com.example.saidur.data.repository

import androidx.lifecycle.LiveData
import com.example.saidur.data.api.model.RestListResponse
import com.example.saidur.data.model.Movie
import com.example.saidur.utils.AppResult

interface MoviesRepository {
    suspend fun getAllMovies(): AppResult<RestListResponse<Movie>>
    fun getAllOfflineDB(): LiveData<List<Movie>>
}
