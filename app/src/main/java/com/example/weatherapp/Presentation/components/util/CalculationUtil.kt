package com.example.weatherapp.Presentation.components.util

import com.example.weatherapp.DataSource.Models.ForecastData
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun getDayOfWeek(timestamp: Long): String {
    return SimpleDateFormat("EEE", Locale.getDefault()).format(Date(timestamp * 1000))
}

// Helper function to calculate air quality (example implementation)
fun calculateAirQuality(forecastData: ForecastData): Int {
    // This is a placeholder implementation
    // You should implement your own logic based on your requirements
    return ((forecastData.main.temp + forecastData.main.feels_like) / 2).toInt()
}