package com.kristof.weather.models

import java.util.*

data class HourlyWeatherForecast(
    var temp: Double,
    var wind: Wind,
    var icon: String,
    var description: String,
    var dt: Date
)