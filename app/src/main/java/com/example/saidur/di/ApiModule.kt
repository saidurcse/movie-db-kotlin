package com.example.saidur.di

import com.example.saidur.data.api.MovieApi
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {

    fun provideMoviesApi(retrofit: Retrofit): MovieApi {
        return retrofit.create(MovieApi::class.java)
    }

    single { provideMoviesApi(get()) }
}