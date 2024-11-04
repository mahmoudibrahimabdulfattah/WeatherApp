package com.example.weatherapp.UseCases

import com.example.weatherapp.Repository.WeatherRepository
import javax.inject.Inject

class GetLastSearchedCityUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(): String? {
        return repository.getLastSearchedCity()
    }
}