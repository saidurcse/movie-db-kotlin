package demo.movie.db.kotlin.network

import demo.movie.db.kotlin.BuildConfig
import demo.movie.db.kotlin.model.RestListResponse
import demo.movie.db.kotlin.ui.home.model.Movie
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieEndPoint {
    @GET("3/discover/movie")  // get_movie_list
    suspend fun getMovieList(@Query("api_key") apiKey: String = BuildConfig.API_KEY): RestListResponse<Movie>
}
