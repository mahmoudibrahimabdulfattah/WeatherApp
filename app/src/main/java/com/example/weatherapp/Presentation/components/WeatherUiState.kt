package com.example.weatherapp.Presentation.components

import com.example.weatherapp.DataSource.Models.MainWeather
import com.example.weatherapp.DataSource.Models.Weather
import com.example.weatherapp.DataSource.Models.WeatherInfo

sealed class WeatherUiState {
    object Initial : WeatherUiState()
    object Loading : WeatherUiState()
    data class Success(val weatherMain: MainWeather, val weatherInfo: WeatherInfo) : WeatherUiState()
    data class Error(val message: String) : WeatherUiState()
}