package com.kristof.weather.repositories.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kristof.weather.repositories.database.entities.CityEntity


@Database(entities = [CityEntity::class], version = 4)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cityDao(): CityDAO

    companion object {
        private var Instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (Instance == null) {
                Instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, "cities.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return Instance!!
        }

        fun destroyInstance() {
            Instance = null
        }
    }
}