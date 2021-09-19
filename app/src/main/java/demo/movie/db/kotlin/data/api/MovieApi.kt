package demo.movie.db.kotlin.data.api

import demo.movie.db.kotlin.BuildConfig
import demo.movie.db.kotlin.data.api.model.RestListResponse
import demo.movie.db.kotlin.data.model.Movie
import demo.movie.db.kotlin.utils.AppResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("3/discover/movie")  // get_movie_list
    suspend fun getMovieList(@Query("api_key") apiKey: String = BuildConfig.API_KEY): Response<RestListResponse<Movie>>
}
