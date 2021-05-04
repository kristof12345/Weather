package com.kristof.weather.mocks

import com.kristof.weather.interfaces.IWeatherRepository
import com.kristof.weather.models.*

class WeatherRepositoryMock : IWeatherRepository {
    override fun getCurrent(city: City, unit: String): CurrentWeather {
        return CurrentWeather(Location(1.0,2.0), 16.5, 10.5, 20.5, 100.99, 5.7, 300, Wind(10.4, 180.0), "icon", "nice weather")
    }

    override fun getHourlyForecast(city: City, unit: String): List<HourlyWeatherForecast> {
        TODO("Not yet implemented")
    }

    override fun getDailyForecast(city: City, unit: String): List<DailyWeatherForecast> {
        TODO("Not yet implemented")
    }
}