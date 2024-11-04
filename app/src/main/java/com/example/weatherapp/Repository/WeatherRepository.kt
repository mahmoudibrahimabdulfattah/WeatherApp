package com.example.weatherapp.Repository

import com.example.weatherapp.DataSource.Models.Forecast
import com.example.weatherapp.DataSource.Models.Weather

interface WeatherRepository {
    suspend fun getCurrentWeather(cityName: String): Result<Weather>
    suspend fun getForecast(cityName: String): Result<List<Forecast>>
    suspend fun getLastSearchedCity(): String?
    suspend fun saveLastSearchedCity(cityName: String)
}