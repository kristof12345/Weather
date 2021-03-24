package com.kristof.weather.models

data class City(
    var name: String,
    var location: Location,
    var temperature: Double = 0.0,
    var weatherIcon: String = ""
)