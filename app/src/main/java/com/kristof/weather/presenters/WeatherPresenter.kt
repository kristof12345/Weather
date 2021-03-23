package com.kristof.weather.presenters

import com.kristof.weather.repositories.network.WeatherRepository
import com.kristof.weather.views.weather.current.ICurrentWeatherScreen

object WeatherPresenter : Presenter<ICurrentWeatherScreen?>() {

    fun getWeather(city: String) {
        var response = WeatherRepository.getCurrent(city).execute()
        this.screen?.showWeather(response.body()!!)
    }
}