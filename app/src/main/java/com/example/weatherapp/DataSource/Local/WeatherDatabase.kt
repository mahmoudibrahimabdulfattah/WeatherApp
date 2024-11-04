package com.example.weatherapp.DataSource.Local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LastSearchEntity::class], version = 1)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun lastSearchDao(): LastSearchDao
}