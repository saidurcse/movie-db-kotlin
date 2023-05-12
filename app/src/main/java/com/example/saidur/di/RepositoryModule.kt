package com.example.saidur.di

import com.example.saidur.data.api.MovieApi
import com.example.saidur.data.repository.MoviesRepository
import com.example.saidur.data.repository.MoviesRepositoryImpl
import com.example.saidur.database.dao.MovieLocalDataDAO
import org.koin.dsl.module

val repositoryModule = module {

    fun provideMovieRepository(api: MovieApi, dao: MovieLocalDataDAO): MoviesRepository {
        return MoviesRepositoryImpl(api, dao)
    }

    single { provideMovieRepository(get(), get()) }
}