package com.kristof.weather.interfaces

import com.kristof.weather.models.City
import com.kristof.weather.models.CurrentWeather
import com.kristof.weather.models.DailyWeatherForecast
import com.kristof.weather.models.HourlyWeatherForecast

interface IWeatherRepository {

    fun getCurrent(city: City, unit: String): CurrentWeather

    fun getHourlyForecast(city: City, unit: String): List<HourlyWeatherForecast>

    fun getDailyForecast(city: City, unit: String): List<DailyWeatherForecast>
}