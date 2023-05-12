package com.example.saidur.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.saidur.data.model.Movie

@Dao
interface MovieLocalDataDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun AddAll(data: List<Movie?>?)

    @Query("Select * from MovieData")
    fun Get(): LiveData<List<Movie>>
}