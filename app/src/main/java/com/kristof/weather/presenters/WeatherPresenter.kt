package com.kristof.weather.presenters

import android.content.Context
import com.kristof.weather.getDefaultSharedPreferences
import com.kristof.weather.models.City
import com.kristof.weather.repositories.network.WeatherRepository
import com.kristof.weather.views.weather.current.ICurrentWeatherScreen
import javax.inject.Inject


class WeatherPresenter @Inject constructor(private val weatherRepository: WeatherRepository) :
    Presenter<ICurrentWeatherScreen?>() {

    fun getWeather(city: City, context: Context) {
        val preferences = context.getDefaultSharedPreferences()
        val unit = preferences.getString("unit", "metric")!!

        var response = weatherRepository.getCurrent(city, unit).execute()
        this.screen?.showWeather(response.body()!!)
    }
}