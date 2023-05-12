package com.example.saidur.di

import android.app.Application
import androidx.room.Room
import com.example.saidur.database.MovieDataBase
import com.example.saidur.database.dao.MovieLocalDataDAO
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    fun provideDatabase(application: Application): MovieDataBase {
        return Room.databaseBuilder(application, MovieDataBase::class.java, "MovieDB")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideMoviesDao(database: MovieDataBase): MovieLocalDataDAO {
        return database.movieLocalDataDAO
    }

    single { provideDatabase(androidApplication()) }
    single { provideMoviesDao(get()) }
}
