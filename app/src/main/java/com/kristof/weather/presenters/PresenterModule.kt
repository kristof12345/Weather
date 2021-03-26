package com.kristof.weather.presenters

import com.kristof.weather.presenters.CitiesPresenter
import com.kristof.weather.presenters.WeatherForecastPresenter
import com.kristof.weather.presenters.WeatherPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PresenterModule() {
    @Provides
    fun provideCitiesPresenter() : CitiesPresenter {
        return CitiesPresenter()
    }

    @Provides
    fun provideWeatherPresenter() : WeatherPresenter {
        return WeatherPresenter()
    }

    @Provides
    fun provideWeatherForecastPresenter() : WeatherForecastPresenter {
        return WeatherForecastPresenter()
    }
}