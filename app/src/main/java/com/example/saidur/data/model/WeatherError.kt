package com.example.saidur.data.api.model

class WeatherError(message: String, cause: Throwable) : Throwable(message, cause)