package com.kristof.weather.presenters

import android.content.Context
import com.kristof.weather.repositories.database.CitiesRepository
import com.kristof.weather.repositories.network.WeatherRepository
import com.kristof.weather.views.cities.ICitiesScreen

object CitiesPresenter : Presenter<ICitiesScreen?>() {

    fun getCities(context: Context) {
        var citiesList = CitiesRepository.getFavourites(context)
        for (city in citiesList) {
            var response = WeatherRepository.getCurrent(city).execute()
            if (response.isSuccessful) {
                var weather = response.body()!!
                city.location = weather.coord
                city.temperature = weather.main.temp
                city.weatherIcon = weather.weather.first().icon
            }
        }
        this.screen?.showCities(citiesList)
    }

    fun addCity(city: String, context: Context) {
        CitiesRepository.addToFavourites(city, context)
        getCities(context)
    }

    fun deleteCity(city: String, context: Context) {
        CitiesRepository.removeFromFavourites(city, context)
        getCities(context)
    }
}