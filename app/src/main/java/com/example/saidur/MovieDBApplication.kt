package com.example.saidur

import androidx.multidex.MultiDexApplication
import com.example.saidur.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MovieDBApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin {
            androidLogger()
            androidContext(this@MovieDBApplication)
            modules(
                apiModule,
                viewModelModule,
                repositoryModule,
                networkModule,
                databaseModule
            )
        }
    }

    companion object {
        var instance: MovieDBApplication? = null
            public set
    }

    fun getInstance(): MovieDBApplication? {
        return instance
    }
}
