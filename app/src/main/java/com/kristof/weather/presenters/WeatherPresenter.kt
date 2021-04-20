package com.kristof.weather.presenters

import android.content.Context
import com.kristof.weather.getDefaultSharedPreferences
import com.kristof.weather.models.ChartData
import com.kristof.weather.models.City
import com.kristof.weather.repositories.network.NetworkException
import com.kristof.weather.repositories.network.WeatherRepository
import com.kristof.weather.views.weather.current.ICurrentWeatherScreen
import java.text.SimpleDateFormat
import javax.inject.Inject


class WeatherPresenter @Inject constructor(private val weatherRepository: WeatherRepository) :
    Presenter<ICurrentWeatherScreen?>() {

    fun getCurrentWeather(city: City, context: Context) {
        val unit = getUnit(context)

        var response = weatherRepository.getCurrent(city, unit)
        this.screen?.showWeather(response)
    }

    fun getHourlyWeather(city: City, context: Context) {
        val unit = getUnit(context)
        try {
            val formatter= SimpleDateFormat("HH:mm")
            var response = weatherRepository.getHourlyForecast(city, unit)

            var chartData = mutableListOf<ChartData>()
            for (data in response) {
                chartData.add(ChartData(formatter.format(data.dt), data.temp))
            }

            this.screen?.showChart(chartData)

        } catch (e: NetworkException) {
            this.screen?.showError(e.message!!)
        }
    }

    private fun getUnit(context: Context): String {
        val preferences = context.getDefaultSharedPreferences()
        return preferences.getString("unit", "metric")!!
    }
}