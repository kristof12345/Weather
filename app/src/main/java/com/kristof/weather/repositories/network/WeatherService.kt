package com.kristof.weather.repositories.network

import com.kristof.weather.models.DailyWeather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("weather")
    fun getCurrentWeather(@Query("appid") token: String, @Query("q") city: String, @Query("units") units: String): Call<DailyWeather>
}