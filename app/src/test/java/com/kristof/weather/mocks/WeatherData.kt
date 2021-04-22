package com.kristof.weather.mocks

import com.kristof.weather.models.*
import java.util.*

object WeatherData {
    val Current = CurrentWeather(Location(1.0, 2.0), 16.5, 10.5, 20.5, 100.99, 5.7, 300, Wind(10.4, 180.0), "icon", "nice weather")
    val hourly = listOf(HourlyWeatherForecast(25.9, Wind(1.0, 180.0), "icon", "nice weather", Date()))
    val daily = listOf(DailyWeatherForecast(25.9, 20.4, 30.2, Wind(1.0, 180.0), "icon", "nice weather", 150.0, Date()))
    val dailyMultiple = listOf(
        DailyWeatherForecast(20.0, 10.0, 30.0, Wind(1.0, 180.0), "icon", "nice weather", 100.0, Date()),
        DailyWeatherForecast(30.0, 20.0, 40.0, Wind(1.0, 180.0), "icon", "nice weather", 200.0, Date())
    )
}