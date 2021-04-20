package com.kristof.weather.models

data class WeatherForecastSummary(
    var avgTemp: Double,
    var minTemp: Double,
    var maxTemp: Double,
    var avgRain: Double,
    var totalRain: Double
)