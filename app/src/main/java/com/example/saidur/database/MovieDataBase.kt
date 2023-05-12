package com.example.saidur.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.saidur.data.model.Movie
import com.example.saidur.database.converter.DataConverter
import com.example.saidur.database.dao.MovieLocalDataDAO

@Database(entities = [Movie::class], version = 1, exportSchema = false)
@TypeConverters(DataConverter::class)
abstract class MovieDataBase : RoomDatabase() {
    abstract val movieLocalDataDAO: MovieLocalDataDAO
}