package com.example.weatherapp.UseCases

import com.example.weatherapp.DataSource.Models.Weather
import com.example.weatherapp.DataSource.Models.WeatherResponse
import com.example.weatherapp.DataSource.Models.WeatherResult
import com.example.weatherapp.Repository.WeatherRepository
import javax.inject.Inject

class GetCurrentWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(cityName: String): Result<WeatherResult> {
        return repository.getCurrentWeather(cityName).map { mainWeather ->
            val weatherInfo = repository.getWeatherInfo(cityName)
                .getOrNull() ?: throw Exception("Failed to get weather info")
            WeatherResult(mainWeather, weatherInfo)
        }.also {
            if (it.isSuccess) {
                repository.saveLastSearchedCity(cityName)
            }
        }
    }
}