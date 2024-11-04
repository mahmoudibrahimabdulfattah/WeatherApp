package com.example.weatherapp.UseCases

import com.example.weatherapp.DataSource.Models.Forecast
import com.example.weatherapp.Repository.WeatherRepository
import javax.inject.Inject

class GetForecastUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(cityName: String): Result<List<Forecast>> {
        return repository.getForecast(cityName)
    }
}