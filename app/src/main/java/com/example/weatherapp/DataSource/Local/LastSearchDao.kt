package com.example.weatherapp.DataSource.Local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LastSearchDao {
    @Query("SELECT * FROM last_search WHERE id = 1")
    suspend fun getLastSearch(): LastSearchEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveLastSearch(lastSearch: LastSearchEntity)
}