package com.kristof.weather.presenters

import android.content.Context
import com.kristof.weather.getDefaultSharedPreferences
import com.kristof.weather.interfaces.ICitiesRepository
import com.kristof.weather.interfaces.IWeatherRepository
import com.kristof.weather.views.cities.ICitiesScreen
import javax.inject.Inject

class CitiesPresenter @Inject constructor(
    private val citiesRepository: ICitiesRepository,
    private val weatherRepository: IWeatherRepository
) :
    Presenter<ICitiesScreen?>() {

    fun getCities(context: Context) {
        val preferences = context.getDefaultSharedPreferences()
        val unit = preferences.getString("unit", "metric")!!

        var citiesList = citiesRepository.getFavourites(context)
        for (city in citiesList) {
            var weather = weatherRepository.getCurrent(city, unit)
            city.location = weather.coord
            city.temperature = weather.temp
            city.weatherIcon = weather.icon
        }
        this.screen?.showCities(citiesList)
    }

    fun addCity(city: String, context: Context) {
        citiesRepository.addToFavourites(city, context)
        getCities(context)
    }

    fun deleteCity(city: String, context: Context) {
        citiesRepository.removeFromFavourites(city, context)
        getCities(context)
    }
}