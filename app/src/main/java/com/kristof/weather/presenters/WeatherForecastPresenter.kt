package com.kristof.weather.presenters

import com.kristof.weather.views.weather.forecast.IWeatherForecastScreen

object WeatherForecastPresenter : Presenter<IWeatherForecastScreen?>() {

    fun getWeather() {
        var weather = WeatherRepository.getForecast()
        this.screen?.showWeather(weather)
    }
}