package com.example.weatherapp.Repository

import com.example.weatherapp.DataSource.Local.LastSearchDao
import com.example.weatherapp.DataSource.Local.LastSearchEntity
import com.example.weatherapp.DataSource.Models.Forecast
import com.example.weatherapp.DataSource.Models.MainWeather
import com.example.weatherapp.DataSource.Models.Weather
import com.example.weatherapp.DataSource.Models.WeatherInfo
import com.example.weatherapp.DataSource.Remote.WeatherApi
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi,
    private val dao: LastSearchDao,
    private val apiKey: String
) : WeatherRepository {
    override suspend fun getCurrentWeather(cityName: String): Result<MainWeather> = runCatching {
        val response = api.getCurrentWeather(cityName, apiKey)
        response.toMainWeather()
    }

    override suspend fun getWeatherInfo(cityName: String): Result<WeatherInfo> = runCatching {
        val response = api.getCurrentWeather(cityName, apiKey)
        response.toWeatherInfo()
    }

    override suspend fun getForecast(cityName: String): Result<List<Forecast>> = runCatching {
        api.getForecast(cityName, apiKey).toForecastList()
    }

    override suspend fun getLastSearchedCity(): String? {
        return dao.getLastSearch()?.cityName
    }

    override suspend fun saveLastSearchedCity(cityName: String) {
        dao.saveLastSearch(LastSearchEntity(cityName = cityName))
    }
}