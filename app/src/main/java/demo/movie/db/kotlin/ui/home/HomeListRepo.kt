package demo.movie.db.kotlin.ui.home

import android.content.Context
import demo.movie.db.kotlin.R
import demo.movie.db.kotlin.model.MovieError
import demo.movie.db.kotlin.model.RestListResponse
import demo.movie.db.kotlin.network.MovieEndPoint
import demo.movie.db.kotlin.ui.home.model.Movie

class HomeListRepo(private val endPoint: MovieEndPoint) {

    suspend fun fetchMovieList(context: Context): RestListResponse<Movie> {
        return try {
            endPoint.getMovieList()
        } catch (throwable: Throwable) {
            throw MovieError(context.getString(R.string.movie_list_warning), throwable)
        }
    }
}
