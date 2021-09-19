package demo.movie.db.kotlin.data.repository

import demo.movie.db.kotlin.data.api.model.RestListResponse
import demo.movie.db.kotlin.data.model.Movie
import demo.movie.db.kotlin.utils.AppResult

interface MoviesRepository {
    suspend fun getAllMovies() : AppResult<RestListResponse<Movie>>
}
