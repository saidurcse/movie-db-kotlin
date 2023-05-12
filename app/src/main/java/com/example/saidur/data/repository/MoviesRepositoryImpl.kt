package com.example.saidur.data.repository

import androidx.lifecycle.LiveData
import com.example.saidur.data.api.MovieApi
import com.example.saidur.data.api.model.RestListResponse
import com.example.saidur.data.model.Movie
import com.example.saidur.database.dao.MovieLocalDataDAO
import com.example.saidur.utils.AppResult
import com.example.saidur.utils.Utils.handleApiError
import com.example.saidur.utils.Utils.handleSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesRepositoryImpl(private val api: MovieApi, private val dao: MovieLocalDataDAO) :
    MoviesRepository {

    override suspend fun getAllMovies(): AppResult<RestListResponse<Movie>> {
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

    override fun getAllOfflineDB(): LiveData<List<Movie>> {
        return dao.Get()
    }
}