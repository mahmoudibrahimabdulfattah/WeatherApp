package com.example.weatherapp.DataSource.Models

data class WeatherResponse(
    val name: String,
    val main: MainWeather,
    val weather: List<WeatherInfo>
)

