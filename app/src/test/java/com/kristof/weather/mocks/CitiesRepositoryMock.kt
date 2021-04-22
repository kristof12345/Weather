package com.kristof.weather.mocks

import android.content.Context
import com.kristof.weather.interfaces.ICitiesRepository
import com.kristof.weather.models.City

class CitiesRepositoryMock : ICitiesRepository {

    private val list = mutableListOf<City>()

    override fun getFavourites(context: Context): MutableList<City> {
        return list
    }

    override fun addToFavourites(name: String, context: Context) {
        list.add(City(name))
    }

    override fun removeFromFavourites(name: String, context: Context) {
        var city = list.find { it.name == name }
        list.remove(city)
    }

    override fun updateFavourite(id: Long, name: String, context: Context) {
        var city = list.first()
        city.name = name
    }
}