package com.example.weatherapp.DataSource.Models

data class ForecastResponse(
    val list: List<ForecastData>,
    val city: City
)

data class ForecastData(
    val dt: Long,
    val main: MainWeather,
    val weather: List<WeatherInfo>,
    val dt_txt: String,
    val humidity: Int
)

data class City(
    val name: String,
    val country: String
)
