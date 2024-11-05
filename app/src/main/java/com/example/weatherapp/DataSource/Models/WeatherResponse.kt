package com.example.weatherapp.DataSource.Models

data class WeatherResponse(
    val dt: Long,
    val main: MainWeather,
    val weather: List<WeatherInfo>,
    val name: String
)

