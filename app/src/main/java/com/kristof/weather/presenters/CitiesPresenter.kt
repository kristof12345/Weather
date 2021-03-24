package com.kristof.weather.presenters

import com.kristof.weather.repositories.database.CitiesRepository
import com.kristof.weather.repositories.network.WeatherRepository
import com.kristof.weather.views.cities.ICitiesScreen

object CitiesPresenter : Presenter<ICitiesScreen?>() {

    fun getCities() {
        var citiesList = CitiesRepository.getFavourites()
        for (city in citiesList) {
            var weather = WeatherRepository.getCurrent(city).execute().body()!!
            city.temperature = weather.main.temp
            city.weatherIcon = weather.weather.first().icon
        }

        this.screen?.showCities(citiesList)
    }

    fun addCity(city: String) {
        CitiesRepository.addToFavourites(city)
        getCities()
    }

    fun deleteCity(city: String) {
        CitiesRepository.removeFromFavourites(city)
        getCities()
    }
}