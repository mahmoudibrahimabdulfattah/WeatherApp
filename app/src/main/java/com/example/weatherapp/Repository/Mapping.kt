package com.example.weatherapp.Repository

import com.example.weatherapp.DataSource.Models.Forecast
import com.example.weatherapp.DataSource.Models.ForecastResponse
import com.example.weatherapp.DataSource.Models.MainWeather
import com.example.weatherapp.DataSource.Models.Weather
import com.example.weatherapp.DataSource.Models.WeatherInfo
import com.example.weatherapp.DataSource.Models.WeatherResponse
import com.example.weatherapp.Presentation.components.util.ForecastItem
import com.example.weatherapp.Presentation.components.util.calculateAirQuality
import com.example.weatherapp.Presentation.components.util.getDayOfWeek
import com.example.weatherapp.R
import kotlin.math.roundToInt

fun WeatherResponse.toWeather(): Weather {
    return Weather(
        cityName = this.name,
        temperature = this.main.temp,
        condition = this.weather.firstOrNull()?.description ?: "",
        timestamp = this.dt ,
        icon = this.weather.firstOrNull()?.icon ?: "",
        feelsLike = this.main.feels_like,
    )
}

fun ForecastResponse.toForecastList(): List<Forecast> {
    return this.list.map { forecastData ->
        Forecast(
            date = forecastData.dt_txt.split(" ")[0], // Assuming date format "YYYY-MM-DD HH:mm:ss"
            dayOfWeek = getDayOfWeek(forecastData.dt), // You'll need to implement this
            temperature = forecastData.main.temp,
            condition = forecastData.weather.firstOrNull()?.description ?: "",
            icon = forecastData.weather.firstOrNull()?.icon ?: "",
            airQuality = calculateAirQuality(forecastData) // You'll need to implement this
        )
    }
}

fun Forecast.toForecastItem(): ForecastItem {
    return ForecastItem(
        image = getWeatherIcon(this.icon), // implement this function
        dayOfWeek = this.dayOfWeek,
        date = this.date,
        temperature = this.temperature,
        airQuality = this.airQuality.toString(),
        airQualityIndicatorColorHex = getAirQualityColor(this.airQuality), // implement this function
        isSelected = false // يمكنك تعديل هذا حسب احتياجك
    )
}

fun WeatherResponse.toMainWeather(): MainWeather {
    return MainWeather(
        // Map the appropriate fields from the API response
        // Example:
        pressure = main.pressure,
        humidity = main.humidity,
        temp = main.temp,
        feels_like = main.feels_like,
        temp_min = main.temp_min,
        temp_max = main.temp_max,
        sea_level = main.sea_level,
        grnd_level = main.grnd_level,
        temp_kf = main.temp_kf
    )
}

fun WeatherResponse.toWeatherInfo(): WeatherInfo {
    return WeatherInfo(
        // Map the appropriate fields from the API response
        // Example:
        description = weather.firstOrNull()?.description ?: "",
        icon = weather.firstOrNull()?.icon ?: "",
        id = weather.firstOrNull()?.id ?: 1,
        main = weather.firstOrNull()?.main.toString(),
    )
}

// Helper functions
private fun getWeatherIcon(iconCode: String): Int {
    return when (iconCode) {
        "01d" -> R.drawable.img_sun
        "02d" -> R.drawable.img_cloudy
        "03d", "04d" -> R.drawable.img_clouds
        "09d", "10d" -> R.drawable.img_rain
        "11d" -> R.drawable.img_thunder
        else -> R.drawable.img_cloudy
    }
}

private fun getAirQualityColor(value: Int): String {
    return when {
        value <= 50 -> "#2dbe8d"
        value <= 100 -> "#f9cf5f"
        else -> "#ff7676"
    }
}