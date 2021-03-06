package demo.movie.db.kotlin.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import demo.movie.db.kotlin.ui.home.model.Movie

@Dao
interface MovieLocalDataDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun AddAll(data: List<Movie?>?)

    @Query("Select * from MovieData")
    fun Get(): List<Movie?>?
}