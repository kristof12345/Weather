package com.kristof.weather.models

data class HourlyWeatherForecast(
    var main: HourlyTemperature,
    var wind: Wind,
    var weather: List<WeatherReport>,
    var dt: Int
)

data class HourlyTemperature(
    var temp: Double,
)