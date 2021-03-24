package com.kristof.weather.repositories.network

import com.kristof.weather.models.DailyWeather
import com.kristof.weather.models.WeatherForecast
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("weather")
    fun getCurrentWeather(@Query("appid") token: String, @Query("q") city: String, @Query("units") units: String): Call<DailyWeather>

    @GET("onecall")
    fun getWeatherForecast(@Query("appid") token: String, @Query("lat") latitude: Double, @Query("lon") longitude: Double, @Query("units") units: String, @Query("exclude") exclude: String = "current,minutely,alerts"): Call<WeatherForecast>
}