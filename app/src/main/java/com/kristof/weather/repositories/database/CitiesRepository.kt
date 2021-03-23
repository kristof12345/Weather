package com.kristof.weather.repositories.database

import com.kristof.weather.models.City

object CitiesRepository {
    fun getFavourites(): MutableList<City> {
        val cities = mutableListOf(City("Budapest"), City("London"), City("Paris"));
        return cities;
    }

    fun addToFavourites(city: City) {

    }

    fun removeFromFavourites(city: City) {

    }
}