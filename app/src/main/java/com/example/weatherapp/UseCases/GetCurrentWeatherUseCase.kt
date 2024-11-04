package com.example.weatherapp.UseCases

import com.example.weatherapp.DataSource.Models.Weather
import com.example.weatherapp.Repository.WeatherRepository
import javax.inject.Inject

class GetCurrentWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(cityName: String): Result<Weather> {
        return repository.getCurrentWeather(cityName).also {
            if (it.isSuccess) {
                repository.saveLastSearchedCity(cityName)
            }
        }
    }
}