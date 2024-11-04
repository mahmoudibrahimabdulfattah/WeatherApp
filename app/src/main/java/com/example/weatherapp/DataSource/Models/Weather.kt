package com.example.weatherapp.DataSource.Models


data class Weather(
    val cityName: String,
    val temperature: Double,
    val condition: String,
    val icon: String,
    val feelsLike: Double,
    val timestamp: Long
)
