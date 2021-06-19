package demo.movie.db.kotlin

import androidx.multidex.MultiDexApplication

class MovieDBApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        var instance: MovieDBApplication? = null
            public set
    }

    fun getInstance(): MovieDBApplication? {
        return MovieDBApplication.instance
    }
}
