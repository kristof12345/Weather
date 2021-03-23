package com.kristof.weather.models

data class DailyWeather(
        var coord: Location,
        var main: Temperature,
        var visibility: Long,
        var wind: Wind
)

data class Temperature(
        var temp: Int,
        var pressure: Int,
        var humidity: Int
)

data class Wind(
        var speed: Int,
        var deg: Int
)