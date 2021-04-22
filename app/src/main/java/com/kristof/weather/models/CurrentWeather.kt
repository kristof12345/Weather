package com.kristof.weather.models

data class CurrentWeather(
    var coord: Location,
    var temp: Double,
    var tempMin: Double,
    var tempMax: Double,
    var pressure: Double,
    var humidity: Double,
    var visibility: Long,
    var wind: Wind,
    var icon: String,
    var description: String
)