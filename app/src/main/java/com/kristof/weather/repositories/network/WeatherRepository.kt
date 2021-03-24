package com.kristof.weather.repositories.network

import com.kristof.weather.models.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherRepository {

    private val token: String
    private val units: String
    private val api: WeatherService

    init {
        var retrofit = Retrofit.Builder().baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        api = retrofit.create(WeatherService::class.java)
        token = "d9c9822385ae388d2b7eff3471abefc3"
        units = "metric"
    }

    fun getCurrent(city: City): Call<DailyWeather> {
        return api.getCurrentWeather(token, city.name, units)
    }

    fun getForecast(city: City): Call<WeatherForecast> {
        return api.getWeatherForecast(token, city.location.lat, city.location.lon, units)
    }
}