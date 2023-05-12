package com.example.saidur.data.api.model

class MovieError(message: String, cause: Throwable) : Throwable(message, cause)