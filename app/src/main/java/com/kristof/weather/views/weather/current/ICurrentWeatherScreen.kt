package com.kristof.weather.views.weather.current

import com.kristof.weather.models.DailyWeather

interface ICurrentWeatherScreen {
    fun showWeather(weather: DailyWeather)
}