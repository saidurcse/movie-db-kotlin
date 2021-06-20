package demo.movie.db.kotlin.network

import demo.movie.db.kotlin.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RestServiceGenerator {
    private const val API_BASE_URL = BuildConfig.API_URL

    private val logger: HttpLoggingInterceptor = if (BuildConfig.DEBUG) {
        val data = HttpLoggingInterceptor()
        data.setLevel(HttpLoggingInterceptor.Level.BODY)
    } else {
        val data = HttpLoggingInterceptor()
        data.setLevel(HttpLoggingInterceptor.Level.NONE)
    }

    private val client = OkHttpClient().newBuilder()
        //.addInterceptor(logger)
        .connectTimeout(1, TimeUnit.MINUTES)
        .readTimeout(1, TimeUnit.MINUTES)
        .writeTimeout(1, TimeUnit.MINUTES)
        .build()

    private val retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <S> createService(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }
}