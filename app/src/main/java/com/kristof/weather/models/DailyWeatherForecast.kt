package com.kristof.weather.models

import java.util.*

data class DailyWeatherForecast(
    var temp: Double,
    var tempMin: Double,
    var tempMax: Double,
    var wind: Wind,
    var icon: String,
    var description: String,
    var dt: Date
)