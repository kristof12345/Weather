package com.kristof.weather.presenters

import android.content.Context
import com.kristof.weather.getDefaultSharedPreferences
import com.kristof.weather.models.City
import com.kristof.weather.repositories.database.CitiesRepository
import com.kristof.weather.repositories.network.WeatherRepository
import com.kristof.weather.views.weather.forecast.IWeatherForecastScreen
import javax.inject.Inject

class WeatherForecastPresenter @Inject constructor(private val weatherRepository: WeatherRepository) : Presenter<IWeatherForecastScreen?>() {

    fun getWeather(city: City, context: Context) {
        val preferences = context.getDefaultSharedPreferences()
        val unit = preferences.getString("unit", "metric")!!

        var response = weatherRepository.getForecast(city, unit).execute()
        this.screen?.showWeather(response.body()!!)
    }
}