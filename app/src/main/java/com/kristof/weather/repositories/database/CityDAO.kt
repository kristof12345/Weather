package com.kristof.weather.repositories.database

import androidx.room.*
import com.kristof.weather.repositories.database.entities.CityEntity

@Dao
interface CityDAO {
    @Query("SELECT * FROM cities")
    fun getAllCities(): List<CityEntity>

    @Insert
    fun insertCity(city: CityEntity): Long

    @Query("DELETE FROM cities WHERE name = :name")
    fun deleteCityByName(name: String)

}