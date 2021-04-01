package com.kristof.weather.models

import com.google.gson.annotations.SerializedName

data class CurrentWeather(
    var coord: Location,
    var main: CurrentWeatherDetails,
    var visibility: Long,
    var wind: Wind,
    var weather: List<WeatherReport>,
    var rain: Precipitation,
    var snow: Precipitation
)

data class CurrentWeatherDetails(
    var temp: Double,
    var tempMin: Double,
    var tempMax: Double,
    var pressure: Double,
    var humidity: Double
)

data class Precipitation(
    @SerializedName("1h") var lastHour: Double,
    @SerializedName("3h") var recently: Double,
)