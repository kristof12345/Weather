package com.kristof.weather.views.weather.current

import com.kristof.weather.models.ChartData
import com.kristof.weather.models.CurrentWeather

interface ICurrentWeatherScreen {

    fun showWeather(weather: CurrentWeather)

    fun showChart(weather: List<ChartData>)

    fun showError(msg: String)
}