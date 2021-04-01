package com.kristof.weather.repositories.network

import com.kristof.weather.models.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("weather")
    fun getCurrentWeather(@Query("appid") token: String, @Query("q") city: String, @Query("units") units: String): Call<CurrentWeather>

    @GET("https://pro.openweathermap.org/data/2.5/forecast/hourly")
    fun getHourlyWeatherForecast(@Query("appid") token: String, @Query("q") city: String, @Query("units") units: String): Call<ListResponse<HourlyWeatherForecast>>

    @GET("forecast/daily")
    fun getDailyWeatherForecast(@Query("appid") token: String, @Query("q") city: String, @Query("units") units: String): Call<ListResponse<DailyWeatherForecast>>
}