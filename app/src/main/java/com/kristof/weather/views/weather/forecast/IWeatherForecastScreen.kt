package com.kristof.weather.views.weather.forecast

import com.kristof.weather.models.DailyWeatherForecast

interface IWeatherForecastScreen {

    fun showWeather(weather: List<DailyWeatherForecast>)

    fun showError(msg: String)
}