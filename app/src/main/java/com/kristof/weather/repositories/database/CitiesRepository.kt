package com.kristof.weather.repositories.database

import android.content.Context
import com.kristof.weather.models.City
import com.kristof.weather.models.Location
import com.kristof.weather.repositories.database.entities.CityEntity

object CitiesRepository {
    private val cities = mutableListOf(
        City("Budapest", Location(0.0, 0.0)),
        City("London", Location(0.0, 0.0)),
        City("Paris", Location(0.0, 0.0))
    );

    fun getFavourites(context: Context): MutableList<City> {
        var citiesList = AppDatabase.getInstance(context).cityDao().getAllCities()
        val cities = mutableListOf<City>()
        for (city in citiesList) cities.add(City(city.name))
        return cities;
    }

    fun addToFavourites(name: String, context: Context) {
        AppDatabase.getInstance(context).cityDao().insertCity(CityEntity(null, name))
    }

    fun removeFromFavourites(name: String, context: Context) {
        //AppDatabase.getInstance(context).cityDao().insertCity(CityEntity(null, name))
    }
}