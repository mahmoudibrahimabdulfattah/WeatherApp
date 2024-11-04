package com.example.weatherapp.DataSource.Remote

import com.example.weatherapp.DataSource.Models.ForecastResponse
import com.example.weatherapp.DataSource.Models.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String
    ): WeatherResponse

    @GET("forecast")
    suspend fun getForecast(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String
    ): ForecastResponse
}