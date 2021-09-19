package demo.movie.db.kotlin.di

import android.content.Context
import demo.movie.db.kotlin.data.api.MovieApi
import demo.movie.db.kotlin.data.repository.MoviesRepository
import demo.movie.db.kotlin.data.repository.MoviesRepositoryImpl
import demo.movie.db.kotlin.database.dao.MovieLocalDataDAO
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {

    fun provideMovieRepository(api: MovieApi, dao: MovieLocalDataDAO): MoviesRepository {
        return MoviesRepositoryImpl(api, dao)
    }

    single { provideMovieRepository(get(), get()) }
}