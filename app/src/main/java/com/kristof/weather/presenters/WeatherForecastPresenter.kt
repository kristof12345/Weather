package com.kristof.weather.presenters

import com.kristof.weather.models.City
import com.kristof.weather.repositories.network.WeatherRepository
import com.kristof.weather.views.weather.forecast.IWeatherForecastScreen

class WeatherForecastPresenter : Presenter<IWeatherForecastScreen?>() {

    fun getWeather(city: City) {
        var response = WeatherRepository.getForecast(city).execute()
        this.screen?.showWeather(response.body()!!)
    }
}