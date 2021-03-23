package com.kristof.weather.presenters

import com.kristof.weather.models.City
import com.kristof.weather.models.DailyWeather
import com.kristof.weather.repositories.database.CitiesRepository
import com.kristof.weather.repositories.network.WeatherRepository
import com.kristof.weather.views.cities.ICitiesScreen

object CitiesPresenter : Presenter<ICitiesScreen?>() {

    fun getCities() {
        var citiesList = CitiesRepository.getFavourites()
        this.screen?.showCities(citiesList)
    }

    fun addCity(city: City) {
        CitiesRepository.addToFavourites(city)
        getCities()
    }

    fun deleteCity(city: City) {
        CitiesRepository.removeFromFavourites(city)
        getCities()
    }
}