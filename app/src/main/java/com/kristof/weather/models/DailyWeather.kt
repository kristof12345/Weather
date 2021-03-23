package com.kristof.weather.models

data class DailyWeather(
        var coord: Location,
        var main: Temperature,
        var visibility: Long,
        var wind: Wind
)

data class Temperature(
        var temp: Double,
        var pressure: Double,
        var humidity: Double
)

data class Wind(
        var speed: Double,
        var deg: Double
)