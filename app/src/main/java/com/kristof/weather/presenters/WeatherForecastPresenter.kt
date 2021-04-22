package com.kristof.weather.presenters

import android.content.Context
import com.kristof.weather.getDefaultSharedPreferences
import com.kristof.weather.interfaces.IWeatherRepository
import com.kristof.weather.models.City
import com.kristof.weather.models.HighLowChartData
import com.kristof.weather.models.WeatherForecastSummary
import com.kristof.weather.repositories.network.NetworkException
import com.kristof.weather.repositories.network.WeatherRepository
import com.kristof.weather.views.weather.forecast.IWeatherForecastScreen
import java.text.SimpleDateFormat
import javax.inject.Inject

class WeatherForecastPresenter @Inject constructor(private val weatherRepository: IWeatherRepository) :
    Presenter<IWeatherForecastScreen?>() {

    fun getDailyWeather(city: City, context: Context) {
        val unit = getUnit(context)

        try {
            val formatter = SimpleDateFormat("MM.dd")
            var response = weatherRepository.getDailyForecast(city, unit)

            var chartData = mutableListOf<HighLowChartData>()
            for (data in response) {
                chartData.add(HighLowChartData(formatter.format(data.dt), data.tempMax, data.tempMin))
            }

            this.screen?.showChart(chartData)

            var rains = response.map { s -> s.rain }
            this.screen?.showWeather(
                WeatherForecastSummary(
                    response.map { s -> s.temp }.average(),
                    response.map { s -> s.tempMin }.minOrNull()!!,
                    response.map { s -> s.tempMax }.maxOrNull()!!,
                    rains.average(),
                    rains.sum()
                )
            )

        } catch (e: NetworkException) {
            this.screen?.showError(e.message!!)
        }
    }

    private fun getUnit(context: Context): String {
        val preferences = context.getDefaultSharedPreferences()
        return preferences.getString("unit", "metric")!!
    }
}