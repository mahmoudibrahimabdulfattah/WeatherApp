package com.example.weatherapp.DataSource.Models

data class Forecast(
    val date: String,
    val dayOfWeek: String,
    val temperature: Double,
    val condition: String,
    val icon: String,
    val airQuality: Int
)
