package com.example.weatherapp.Presentation.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherapp.Presentation.components.ActionBar
import com.example.weatherapp.Presentation.components.DailyForecast
import com.example.weatherapp.Presentation.components.ErrorMessage
import com.example.weatherapp.Presentation.components.ForecastIntent
import com.example.weatherapp.Presentation.components.LoadingIndicator
import com.example.weatherapp.Presentation.components.WeatherUiState
import com.example.weatherapp.Presentation.components.WeeklyForecast
import com.example.weatherapp.Presentation.components.util.formatDate
import com.example.weatherapp.Presentation.theme.ColorBackground
import com.example.weatherapp.Repository.toForecastItem

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherScreen(
    weatherViewModel: WeatherViewModel = hiltViewModel(),
    forecastViewModel: ForecastViewModel = hiltViewModel()
) {
    val weatherState by weatherViewModel.uiState.collectAsState()
    val forecastState by forecastViewModel.state.collectAsState()

    var cityName by remember { mutableStateOf("") }

    var fDate by remember { mutableStateOf("") }
    var weatherCondition by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = ColorBackground
    ) { paddings ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(paddings)
                .padding(horizontal = 24.dp, vertical = 10.dp)
        ) {
            ActionBar(
                cityName = cityName,
                onCityNameChange = { cityName = it },
                onSearchClick = {
                    weatherViewModel.fetchWeather(cityName)
                    forecastViewModel.processIntent(ForecastIntent.LoadForecast(cityName))
                }
            )

            Spacer(modifier = Modifier.height(12.dp))

            when (weatherState) {
                is WeatherUiState.Loading -> LoadingIndicator()
                is WeatherUiState.Success -> {
                    val weatherMain = (weatherState as WeatherUiState.Success).weatherMain
                    val weatherInfo = (weatherState as WeatherUiState.Success).weatherInfo
                    weatherCondition = weatherInfo.main
                    DailyForecast(
                        forecast = weatherInfo.description,
                        temperature = weatherMain.temp,
                        date = fDate,
                        weatherCondition = weatherCondition,
                        feelsLike = weatherMain.feels_like
                    )
                }
                is WeatherUiState.Error -> ErrorMessage(
                    message = (weatherState as WeatherUiState.Error).message
                )
                else -> Unit
            }

            Spacer(modifier = Modifier.height(24.dp))

            if (forecastState.isLoading) {
                LoadingIndicator()
            } else if (forecastState.error != null) {
                ErrorMessage(message = forecastState.error!!)
            } else if (forecastState.forecast.isNotEmpty()) {
                fDate = formatDate(forecastState.forecast.first().toForecastItem().date)
                WeeklyForecast(
                    data = forecastState.forecast.distinctBy { it.date}.map { it.toForecastItem() },
                    condition = weatherCondition,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherScreenPreview() {

}