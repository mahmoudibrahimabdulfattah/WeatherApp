package com.example.weatherapp.Presentation.components

import com.example.weatherapp.DataSource.Models.Forecast

data class ForecastState(
    val isLoading: Boolean = false,
    val forecast: List<Forecast> = emptyList(),
    val error: String? = null
)

sealed class ForecastIntent {
    data class LoadForecast(val cityName: String) : ForecastIntent()
    data class RefreshForecast(val cityName: String) : ForecastIntent()
}