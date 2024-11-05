package com.example.weatherapp.Repository

import com.example.weatherapp.DataSource.Models.Forecast
import com.example.weatherapp.DataSource.Models.MainWeather
import com.example.weatherapp.DataSource.Models.Weather
import com.example.weatherapp.DataSource.Models.WeatherInfo

interface WeatherRepository {
    suspend fun getCurrentWeather(cityName: String): Result<MainWeather>
    suspend fun getWeatherInfo(cityName: String): Result<WeatherInfo>
    suspend fun getForecast(cityName: String): Result<List<Forecast>>
    suspend fun getLastSearchedCity(): String?
    suspend fun saveLastSearchedCity(cityName: String)
}