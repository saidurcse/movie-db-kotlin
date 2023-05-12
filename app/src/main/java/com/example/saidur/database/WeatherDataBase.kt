package com.example.saidur.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.saidur.data.model.Weather
import com.example.saidur.database.converter.DataConverter
import com.example.saidur.database.dao.WeatherLocalDataDAO

@Database(entities = [Weather::class], version = 1, exportSchema = false)
@TypeConverters(DataConverter::class)
abstract class WeatherDataBase : RoomDatabase() {
    abstract val movieLocalDataDAO: WeatherLocalDataDAO
}