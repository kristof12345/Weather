package com.kristof.weather.mocks

import com.kristof.weather.interfaces.IWeatherRepository
import com.kristof.weather.models.*
import com.kristof.weather.repositories.network.NetworkException

class WeatherRepositoryMock(private val throwError: Boolean = false, private val multiple: Boolean = false) : IWeatherRepository {
    override fun getCurrent(city: City, unit: String): CurrentWeather {
        if (throwError) throw NetworkException()
        return WeatherData.Current
    }

    override fun getHourlyForecast(city: City, unit: String): List<HourlyWeatherForecast> {
        if (throwError) throw NetworkException()
        return WeatherData.hourly
    }

    override fun getDailyForecast(city: City, unit: String): List<DailyWeatherForecast> {
        if (throwError) throw NetworkException()
        if (multiple) return  WeatherData.dailyMultiple
        return WeatherData.daily
    }
}