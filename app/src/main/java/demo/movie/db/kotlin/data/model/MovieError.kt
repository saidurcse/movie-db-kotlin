package demo.movie.db.kotlin.data.api.model

class MovieError(message: String, cause: Throwable) : Throwable(message, cause)