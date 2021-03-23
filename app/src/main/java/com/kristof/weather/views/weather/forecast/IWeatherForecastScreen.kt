package com.kristof.weather.views.weather.forecast

import com.kristof.weather.models.WeatherForecast

interface IWeatherForecastScreen {
    fun showWeather(weather: WeatherForecast)
}