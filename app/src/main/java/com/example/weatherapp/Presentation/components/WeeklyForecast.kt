package com.example.weatherapp.Presentation.components

import android.os.Build
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.Presentation.components.util.ForecastData
import com.example.weatherapp.Presentation.components.util.ForecastItem
import com.example.weatherapp.Presentation.components.util.formatDayMonth
import com.example.weatherapp.Presentation.components.util.fromHex
import com.example.weatherapp.Presentation.theme.ColorGradient1
import com.example.weatherapp.Presentation.theme.ColorGradient2
import com.example.weatherapp.Presentation.theme.ColorGradient3
import com.example.weatherapp.Presentation.theme.ColorTextPrimary
import com.example.weatherapp.Presentation.theme.ColorTextPrimaryVariant
import com.example.weatherapp.Presentation.theme.ColorTextSecondary
import com.example.weatherapp.Presentation.theme.ColorTextSecondaryVariant
import com.example.weatherapp.R
import java.util.UUID

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeeklyForecast(
    modifier: Modifier = Modifier,
    data: List<ForecastItem> = ForecastData,
    condition: String
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        WeatherForecastHeader()

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            items(
                items = data.take(7),
                key = { UUID.randomUUID().toString() }
            ) { item ->
                Forecast(
                    item = item,
                    condition = condition
                )
            }
        }
    }
}

@Composable
fun WeatherIcon(weatherCondition: String) {
    val iconRes = when (weatherCondition.lowercase()) {
        "clear sky", "sunny" -> R.drawable.img_sun
        "rain", "light rain", "moderate rain", "rain showers" -> R.drawable.img_rain
        "clouds", "few clouds", "scattered clouds", "cloudy" -> R.drawable.img_clouds
        "thunderstorm", "thunder" -> R.drawable.img_thunder
        "snow" -> R.drawable.img_rain
        else -> R.drawable.img_cloudy
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun WeatherForecastHeader(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "7-day forecast",
            style = MaterialTheme.typography.titleLarge,
            color = ColorTextPrimary,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun Forecast(
    modifier: Modifier = Modifier,
    item: ForecastItem,
    condition: String
) {
    val temperatureInCelsius = (item.temperature - 273.15).toInt()

    // Get current date from device
    val currentDate = remember {
        java.time.LocalDate.now()
    }

    // Convert item.date to LocalDate for comparison
    val itemDate = remember(item.date) {
        java.time.LocalDate.parse(item.date)
    }

    val isCurrentDate = currentDate == itemDate

    val updatedModifier = if (isCurrentDate) {
        modifier.background(
            shape = RoundedCornerShape(percent = 50),
            brush = Brush.linearGradient(
                0f to ColorGradient1,
                0.5f to ColorGradient2,
                1f to ColorGradient3
            )
        )
    } else {
        modifier
    }

    val primaryTextColor = if (isCurrentDate) ColorTextSecondary else ColorTextPrimary

    val secondaryTextColor = if (isCurrentDate) ColorTextSecondaryVariant else ColorTextPrimaryVariant

    val temperatureTextStyle = if (isCurrentDate) {
        TextStyle(
            brush = Brush.verticalGradient(
                0f to Color.White,
                1f to Color.White.copy(alpha = 0.3f)
            ),
            fontSize = 24.sp,
            fontWeight = FontWeight.Black
        )
    } else {
        TextStyle(
            color = ColorTextPrimary,
            fontSize = 24.sp,
            fontWeight = FontWeight.Black
        )
    }

    Column(
        modifier = updatedModifier
            .width(65.dp)
            .padding(
                horizontal = 10.dp,
                vertical = 16.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = item.dayOfWeek,
            style = MaterialTheme.typography.labelLarge,
            color = primaryTextColor
        )
        Text(
            text = formatDayMonth(item.date),
            style = MaterialTheme.typography.labelMedium,
            color = secondaryTextColor,
            fontWeight = FontWeight.Normal
        )
        Spacer(
            modifier = Modifier.height(8.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            contentAlignment = Alignment.Center
        ) {
            WeatherIcon(weatherCondition = condition)
        }
        Spacer(
            modifier = Modifier.height(6.dp)
        )
        Text(
            text = "$temperatureInCelsius°",
            letterSpacing = 0.sp,
            style = temperatureTextStyle,
        )
        Spacer(
            modifier = Modifier.height(8.dp)
        )
        AirQualityIndicator(
            value = item.airQuality,
            color = item.airQualityIndicatorColorHex
        )
    }
}

@Composable
private fun AirQualityIndicator(
    modifier: Modifier = Modifier,
    value: String,
    color: String
) {
    Surface(
        modifier = modifier,
        color = Color.fromHex(color),
        contentColor = ColorTextSecondary,
        shape = RoundedCornerShape(8.dp)
    ) {
        Box(
            modifier = Modifier
                .width(35.dp)
                .padding(vertical = 2.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.labelSmall,
            )
        }
    }
}