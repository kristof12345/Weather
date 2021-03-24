package com.kristof.weather.models

data class City(
    var name: String,
    var location: Location,
    var weather: DailyWeather? = null
)