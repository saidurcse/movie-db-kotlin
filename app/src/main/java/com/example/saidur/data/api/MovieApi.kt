package com.example.saidur.data.api

import com.example.saidur.BuildConfig
import com.example.saidur.data.api.model.RestListResponse
import com.example.saidur.data.model.Movie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("3/discover/movie")  // get_movie_list
    suspend fun getMovieList(@Query("api_key") apiKey: String = BuildConfig.API_KEY): Response<RestListResponse<Movie>>
}
