package com.example.weatherapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.ui.screens.components.ActionBar
import com.example.weatherapp.ui.screens.components.DailyForecast
import com.example.weatherapp.ui.screens.components.WeeklyForecast
import com.example.weatherapp.ui.theme.ColorBackground

@Composable
fun WeatherScreen() {
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
            ActionBar()
            Spacer(
                modifier = Modifier.height(12.dp)
            )
            DailyForecast()
            Spacer(
                modifier = Modifier.height(24.dp)
            )
            WeeklyForecast()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherScreenPreview() {
    WeatherScreen()
}