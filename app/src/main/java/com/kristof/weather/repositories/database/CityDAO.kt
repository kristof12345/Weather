package com.kristof.weather.repositories.database

import androidx.room.*
import com.kristof.weather.repositories.database.entities.CityEntity

@Dao
interface CityDAO {
    @Query("SELECT * FROM cities")
    fun getAllCities(): List<CityEntity>

    @Insert
    fun insertCity(city: CityEntity): Long

    @Delete
    fun deleteCity(city: CityEntity)

    @Update
    fun updateCity(city: CityEntity)
}