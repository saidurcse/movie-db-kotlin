package demo.movie.db.kotlin.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import demo.movie.db.kotlin.database.dao.MovieLocalDataDAO
import demo.movie.db.kotlin.data.model.Movie
import demo.movie.db.kotlin.database.converter.DataConverter

@Database(entities = [Movie::class], version = 1, exportSchema = false)
@TypeConverters(DataConverter::class)
abstract class MovieDataBase : RoomDatabase() {
    abstract val movieLocalDataDAO: MovieLocalDataDAO
}