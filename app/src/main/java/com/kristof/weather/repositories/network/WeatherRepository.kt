package com.kristof.weather.repositories.network

import com.kristof.weather.models.*
import io.swagger.client.apis.WeatherApi
import java.util.*

class WeatherRepository {

    private val token: String = "4891f6bfdfbdbfd179496273e38b0619"
    private lateinit var api: WeatherApi

    fun getCurrent(city: City, unit: String): CurrentWeather {
        try {
            api = WeatherApi("https://api.openweathermap.org/data/2.5")
            var response = api.weatherGet(token, city.name, unit)
            return CurrentWeather(
                Location(response.coord!!.lat, response.coord!!.lon),
                response.main!!.temp,
                response.main!!.temp_min,
                response.main!!.temp_max,
                response.main!!.pressure,
                response.main!!.humidity,
                response.visibility!!.toLong(),
                Wind(response.wind!!.speed, response.wind!!.deg),
                response.weather!!.first().icon,
                response.weather!!.first().description
            )
        } catch (e: Exception) {
            throw NetworkException(e.message)
        }
    }

    fun getHourlyForecast(city: City, unit: String): List<HourlyWeatherForecast> {
        try {
            api = WeatherApi("https://pro.openweathermap.org/data/2.5")
            var response = api.forecastHourlyGet(token, city.name, unit)
            return response.list!!.map {
                HourlyWeatherForecast(
                    it.main.temp,
                    Wind(it.wind.speed, it.wind.deg),
                    it.weather.first().icon,
                    it.weather.first().description,
                    Date(it.dt.toLong())
                )
            }
        } catch (e: Exception) {
            throw NetworkException(e.message)
        }
    }

    fun getDailyForecast(city: City, unit: String): List<DailyWeatherForecast> {
        try {
            api = WeatherApi("https://api.openweathermap.org/data/2.5")
            var response = api.forecastDailyGet(token, city.name, unit)
            return response.list!!.map {
                DailyWeatherForecast(
                    it.temp.day,
                    it.temp.min,
                    it.temp.max,
                    Wind(it.speed, it.deg),
                    it.weather.first().icon,
                    it.weather.first().description,
                    Date(it.dt.toLong())
                )
            }
        } catch (e: Exception) {
            throw NetworkException(e.message)
        }
    }
}