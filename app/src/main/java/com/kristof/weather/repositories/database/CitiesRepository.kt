package com.kristof.weather.repositories.database

import android.content.Context
import com.kristof.weather.interfaces.ICitiesRepository
import com.kristof.weather.models.City
import com.kristof.weather.repositories.database.entities.CityEntity

class CitiesRepository: ICitiesRepository {
    override fun getFavourites(context: Context): MutableList<City> {
        var citiesList = AppDatabase.getInstance(context).cityDao().getCities()
        val cities = mutableListOf<City>()
        for (city in citiesList) cities.add(City(city.name))
        return cities
    }

    override fun addToFavourites(name: String, context: Context) {
        AppDatabase.getInstance(context).cityDao().insertCity(CityEntity(null, name))
    }

    override fun removeFromFavourites(name: String, context: Context) {
        AppDatabase.getInstance(context).cityDao().deleteCityByName(name)
    }

    override fun updateFavourite(id: Long, name: String, context: Context) {
        AppDatabase.getInstance(context).cityDao().updateCity(id, name)
    }
}