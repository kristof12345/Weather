package com.kristof.weather.views.weather.forecast

import com.kristof.weather.models.HighLowChartData
import com.kristof.weather.models.WeatherForecastSummary

interface IWeatherForecastScreen {

    fun showWeather(weather: WeatherForecastSummary)

    fun showChart(weather: List<HighLowChartData>)

    fun showError(msg: String)
}