package com.kristof.weather.models

data class WeatherForecast(
        var lat: Long,
        var lon: Long,
        var hourly: List<WeatherForecastPoint>,
        var daily: List<WeatherForecastPoint>
)

data class WeatherForecastPoint(
        var temp: Int,
        var pressure: Int,
        var humidity: Int
)