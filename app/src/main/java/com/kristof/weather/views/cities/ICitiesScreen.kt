package com.kristof.weather.views.cities

import com.kristof.weather.models.City

interface ICitiesScreen {
    fun showCities(citiesList: List<City>)

    fun deleteCity(position: Int)

    fun navigateToDetails(city: City)
}