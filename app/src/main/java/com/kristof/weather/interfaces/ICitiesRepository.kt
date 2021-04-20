package com.kristof.weather.interfaces

import android.content.Context
import com.kristof.weather.models.City

interface ICitiesRepository {
    fun getFavourites(context: Context): MutableList<City>

    fun addToFavourites(name: String, context: Context)

    fun removeFromFavourites(name: String, context: Context)

    fun updateFavourite(id: Long, name: String, context: Context)
}