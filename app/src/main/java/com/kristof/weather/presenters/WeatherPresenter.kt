package com.kristof.weather.presenters

import com.kristof.weather.models.City
import com.kristof.weather.repositories.network.WeatherRepository
import com.kristof.weather.views.weather.current.ICurrentWeatherScreen
import javax.inject.Inject

class WeatherPresenter @Inject constructor(private val weatherRepository: WeatherRepository) : Presenter<ICurrentWeatherScreen?>() {

    fun getWeather(city: City) {
        var response = weatherRepository.getCurrent(city).execute()
        this.screen?.showWeather(response.body()!!)
    }
}