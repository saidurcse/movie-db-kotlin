package demo.movie.db.kotlin.database

import android.content.Context
import androidx.room.Room

object DatabaseSingleton {
    private var myAppDataBase: MyAppDataBase? = null
    fun GetDatabase(context: Context?): MyAppDataBase? {
        if (myAppDataBase == null) myAppDataBase = Room.databaseBuilder(context!!, MyAppDataBase::class.java, "MovieDB")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
        return myAppDataBase
    }
}