package com.kristof.weather

import com.kristof.weather.presenters.PresenterModule
import com.kristof.weather.views.cities.CitiesActivity
import com.kristof.weather.views.weather.WeatherActivity
import com.kristof.weather.views.weather.current.CurrentWeatherFragment
import com.kristof.weather.views.weather.forecast.WeatherForecastFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [PresenterModule::class])
interface DIComponent {
    fun inject(citiesActivity: CitiesActivity)
    fun inject(weatherActivity: WeatherActivity)
    fun inject(currentWeatherFragment: CurrentWeatherFragment)
    fun inject(weatherForecastFragment: WeatherForecastFragment)
}