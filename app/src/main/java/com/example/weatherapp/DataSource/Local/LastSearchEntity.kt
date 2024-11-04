package com.example.weatherapp.DataSource.Local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "last_search")
data class LastSearchEntity(
    @PrimaryKey val id: Int = 1,
    val cityName: String,
    val timestamp: Long = System.currentTimeMillis()
)