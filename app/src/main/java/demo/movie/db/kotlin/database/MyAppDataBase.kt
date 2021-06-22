package demo.movie.db.kotlin.database

import androidx.room.Database
import androidx.room.RoomDatabase
import demo.movie.db.kotlin.database.dao.MovieLocalDataDAO
import demo.movie.db.kotlin.ui.home.model.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MyAppDataBase : RoomDatabase() {
    abstract fun movieLocalDataDAO(): MovieLocalDataDAO?
}