package com.kristof.weather.presenters

import android.content.Context
import com.kristof.weather.getDefaultSharedPreferences
import com.kristof.weather.models.City
import com.kristof.weather.repositories.network.WeatherRepository
import com.kristof.weather.views.weather.forecast.IWeatherForecastScreen
import javax.inject.Inject

class WeatherForecastPresenter @Inject constructor(private val weatherRepository: WeatherRepository) :
    Presenter<IWeatherForecastScreen?>() {

    fun getDailyWeather(city: City, context: Context) {
        val unit = getUnit(context)

        var response = weatherRepository.getDailyForecast(city, unit)
        this.screen?.showWeather(response)
    }

    private fun getUnit(context: Context): String {
        val preferences = context.getDefaultSharedPreferences()
        return preferences.getString("unit", "metric")!!
    }
}