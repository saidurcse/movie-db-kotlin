package demo.movie.db.kotlin.model

class MovieError (message: String, cause: Throwable) : Throwable(message, cause)