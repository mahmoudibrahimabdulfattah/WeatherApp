package com.example.weatherapp.Presentation.components

import com.example.weatherapp.DataSource.Models.Weather

sealed class WeatherUiState {
    object Initial : WeatherUiState()
    object Loading : WeatherUiState()
    data class Success(val weather: Weather) : WeatherUiState()
    data class Error(val message: String) : WeatherUiState()
}