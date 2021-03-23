package com.kristof.weather.repositories.network

import com.kristof.weather.models.*

object WeatherRepository {

    fun getCurrent(): DailyWeather {
        return DailyWeather(Location(1, 2), Temperature(32, 120, 80), 10, Wind(50, 90))
    }

    fun getForecast(): WeatherForecast {
        return WeatherForecast(
            1,
            2,
            mutableListOf(WeatherForecastPoint(32, 120, 80)),
            mutableListOf(WeatherForecastPoint(32, 120, 80))
        )
    }
}