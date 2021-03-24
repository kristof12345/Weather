package com.kristof.weather.repositories.database

import com.kristof.weather.models.City
import com.kristof.weather.models.Location

object CitiesRepository {
    private val cities = mutableListOf(
        City("Budapest", Location(0.0, 0.0)),
        City("London", Location(0.0, 0.0)),
        City("Paris", Location(0.0, 0.0))
    );

    fun getFavourites(): MutableList<City> {
        return cities;
    }

    fun addToFavourites(name: String) {
        cities.add(City(name, Location(0.0, 0.0)))
    }

    fun removeFromFavourites(name: String) {

    }
}