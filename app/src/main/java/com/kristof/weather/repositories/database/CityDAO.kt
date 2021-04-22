package com.kristof.weather.repositories.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kristof.weather.repositories.database.entities.CityEntity

@Dao
interface CityDAO {
    @Query("SELECT * FROM cities")
    fun getCities(): List<CityEntity>

    @Insert
    fun insertCity(city: CityEntity): Long

    @Query("UPDATE cities SET name = :name WHERE id = :id")
    fun updateCity(id: Long, name: String)

    @Query("DELETE FROM cities WHERE name = :name")
    fun deleteCityByName(name: String)

}