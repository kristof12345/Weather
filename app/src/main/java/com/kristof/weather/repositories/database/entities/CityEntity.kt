package com.kristof.weather.repositories.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cities")
data class CityEntity(
    @PrimaryKey(autoGenerate = true)
    var cityId: Long?,
    @ColumnInfo(name = "cityname")
    var cityname: String
)