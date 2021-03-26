package com.kristof.weather.presenters

import com.kristof.weather.repositories.database.CitiesRepository
import com.kristof.weather.repositories.network.WeatherRepository
import dagger.Module
import dagger.Provides

@Module
class PresenterModule() {
    @Provides
    fun provideCitiesPresenter() : CitiesPresenter {
        return CitiesPresenter(CitiesRepository(), WeatherRepository())
    }

    @Provides
    fun provideWeatherPresenter() : WeatherPresenter {
        return WeatherPresenter(WeatherRepository())
    }

    @Provides
    fun provideWeatherForecastPresenter() : WeatherForecastPresenter {
        return WeatherForecastPresenter(WeatherRepository())
    }
}