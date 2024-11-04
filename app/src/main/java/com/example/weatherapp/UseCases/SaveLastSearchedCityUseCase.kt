package com.example.weatherapp.UseCases

import com.example.weatherapp.DataSource.Local.LastSearchDao
import com.example.weatherapp.DataSource.Local.LastSearchEntity
import javax.inject.Inject

class SaveLastSearchedCityUseCase @Inject constructor(
    private val lastSearchDao: LastSearchDao
) {
    suspend operator fun invoke(cityName: String) {
        val lastSearchEntity = LastSearchEntity(
            cityName = cityName,
            timestamp = System.currentTimeMillis()
        )
        lastSearchDao.saveLastSearch(lastSearchEntity)
    }
}