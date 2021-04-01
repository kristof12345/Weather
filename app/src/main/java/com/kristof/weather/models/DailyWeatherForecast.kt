package com.kristof.weather.models

data class DailyWeatherForecast(
    var temp: DailyTemperature,
    var speed: Double,
    var deg: Double,
    var weather: List<WeatherReport>,
    var dt: Int
)

data class DailyTemperature(
    var day: Double,
    var min: Double,
    var max: Double
)