package com.example.weatherutils

import kotlin.math.roundToInt

class WeatherFormatter {
    fun formatTemperature(temp: Double, unit: TemperatureUnit = TemperatureUnit.CELSIUS): String {
        return when (unit) {
            TemperatureUnit.CELSIUS -> "${temp.roundToInt()}°C"
            TemperatureUnit.FAHRENHEIT -> "${(temp * 9/5 + 32).roundToInt()}°F"
        }
    }

    fun getWeatherIcon(condition: String): Int {
        return when (condition.toLowerCase()) {
            "clear" -> com.example.weatherapp.R.drawable.img_cloudy
            "cloudy" -> com.example.weatherapp.R.drawable.img_clouds
            "rain" -> com.example.weatherapp.R.drawable.img_rain
            else -> com.example.weatherapp.R.drawable.img_moon_stars
        }
    }

    companion object {
        fun getWeatherDescription(condition: String): String {
            return when (condition.toLowerCase()) {
                "clear" -> "Clear skies"
                "cloudy" -> "Cloudy weather"
                "rain" -> "Rainy weather"
                else -> "Unknown weather condition"
            }
        }
    }
}

enum class TemperatureUnit {
    CELSIUS,
    FAHRENHEIT
}