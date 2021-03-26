package com.kristof.weather.models

data class WeatherForecast(
    var lat: Long,
    var lon: Long,
    var hourly: List<WeatherForecastHourlyPoint>,
    var daily: List<WeatherForecastDailyPoint>
)

data class WeatherForecastHourlyPoint(
    var temp: Double,
    var pressure: Double,
    var humidity: Double
)

data class WeatherForecastDailyPoint(
    var temp: Temp,
    var pressure: Double,
    var humidity: Double
)

data class Temp(
    var day: Double,
    var min: Double,
    var max: Double,
    var night: Double
)