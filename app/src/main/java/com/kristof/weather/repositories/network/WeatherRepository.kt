package com.kristof.weather.repositories.network

import com.kristof.weather.models.City
import com.kristof.weather.models.CurrentWeather
import com.kristof.weather.models.DailyWeatherForecast
import com.kristof.weather.models.HourlyWeatherForecast
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherRepository {

    private val token: String
    private val api: WeatherService

    init {
        var retrofit = Retrofit.Builder().baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        api = retrofit.create(WeatherService::class.java)
        token = "4891f6bfdfbdbfd179496273e38b0619"
    }

    fun getCurrent(city: City, unit: String): CurrentWeather {
        var response = api.getCurrentWeather(token, city.name, unit).execute()
        if (response.isSuccessful) return response.body()!!
        else throw NetworkException()
    }

    fun getHourlyForecast(city: City, unit: String): List<HourlyWeatherForecast> {
        var response = api.getHourlyWeatherForecast(token, city.name, unit).execute()
        if (response.isSuccessful) return response.body()!!.list
        else throw NetworkException()
    }

    fun getDailyForecast(city: City, unit: String): List<DailyWeatherForecast> {
        var response = api.getDailyWeatherForecast(token, city.name, unit).execute()
        if (response.isSuccessful) return response.body()!!.list
        else throw NetworkException()
    }
}