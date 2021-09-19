package demo.movie.db.kotlin.di

import android.app.Application
import androidx.room.Room
import demo.movie.db.kotlin.database.MovieDataBase
import demo.movie.db.kotlin.database.dao.MovieLocalDataDAO
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
        return  database.movieLocalDataDAO
    }

    single { provideDatabase(androidApplication()) }
    single { provideMoviesDao(get()) }
}
