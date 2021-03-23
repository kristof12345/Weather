package com.kristof.weather.presenters

import com.kristof.weather.views.weather.current.ICurrentWeatherScreen

object WeatherPresenter: Presenter<ICurrentWeatherScreen?>() {

    fun getWeather() {
        var weather = WeatherRepository.getCurrent()
        this.screen?.showWeather(weather)
    }
}