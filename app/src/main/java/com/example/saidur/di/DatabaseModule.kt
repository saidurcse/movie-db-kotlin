package com.example.saidur.di

import android.app.Application
import androidx.room.Room
import com.example.saidur.database.WeatherDataBase
import com.example.saidur.database.dao.WeatherLocalDataDAO
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    fun provideDatabase(application: Application): WeatherDataBase {
        return Room.databaseBuilder(application, WeatherDataBase::class.java, "MovieDB")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideMoviesDao(database: WeatherDataBase): WeatherLocalDataDAO {
        return database.movieLocalDataDAO
    }

    single { provideDatabase(androidApplication()) }
    single { provideMoviesDao(get()) }
}
