package com.kristof.weather.presenters

import com.kristof.weather.models.City
import com.kristof.weather.repositories.database.CitiesRepository
import com.kristof.weather.repositories.network.WeatherRepository
import com.kristof.weather.views.weather.forecast.IWeatherForecastScreen
import javax.inject.Inject

class WeatherForecastPresenter @Inject constructor(private val weatherRepository: WeatherRepository) : Presenter<IWeatherForecastScreen?>() {

    fun getWeather(city: City) {
        var response = weatherRepository.getForecast(city).execute()
        this.screen?.showWeather(response.body()!!)
    }
}