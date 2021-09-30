package demo.movie.db.kotlin.data.repository

import androidx.lifecycle.LiveData
import demo.movie.db.kotlin.data.api.MovieApi
import demo.movie.db.kotlin.data.api.model.RestListResponse
import demo.movie.db.kotlin.data.model.Movie
import demo.movie.db.kotlin.database.dao.MovieLocalDataDAO
import demo.movie.db.kotlin.utils.AppResult
import demo.movie.db.kotlin.utils.Utils.handleApiError
import demo.movie.db.kotlin.utils.Utils.handleSuccess
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