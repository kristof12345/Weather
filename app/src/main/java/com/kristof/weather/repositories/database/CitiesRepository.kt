package com.kristof.weather.repositories.database

import com.kristof.weather.models.City
import com.kristof.weather.models.Location

object CitiesRepository {
    fun getFavourites(): MutableList<City> {
        val cities = mutableListOf(City("Budapest", Location(0.0,0.0) ), City("London", Location(0.0,0.0)), City("Paris", Location(0.0,0.0)));
        return cities;
    }

    fun addToFavourites(city: City) {

    }

    fun removeFromFavourites(city: City) {

    }
}