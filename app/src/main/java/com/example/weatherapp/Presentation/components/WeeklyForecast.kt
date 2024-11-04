package com.example.weatherapp.Presentation.components

import androidx.annotation.DrawableRes
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
import java.util.UUID

@Composable
fun WeeklyForecast(
    modifier: Modifier = Modifier,
    data: List<ForecastItem> = ForecastData
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
                items = data.distinctBy { it.date },
                key = { UUID.randomUUID().toString() }
            ) { item ->
                Forecast(
                    item = item
                )
            }
        }
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

@Composable
private fun Forecast(
    modifier: Modifier = Modifier,
    item: ForecastItem
) {
    val temperatureInCelsius = (item.temperature - 273.15).toInt()

    val updatedModifier = remember(item.isSelected) {
        if (item.isSelected) {
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
    }

    val primaryTextColor = remember(item.isSelected) {
        if (item.isSelected) ColorTextSecondary else ColorTextPrimary
    }

    val secondaryTextColor = remember(item.isSelected) {
        if (item.isSelected) ColorTextSecondaryVariant else ColorTextPrimaryVariant
    }

    val temperatureTextStyle = remember(item.isSelected) {
        if (item.isSelected) {
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
        WeatherImage(
            image = item.image
        )
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
private fun WeatherImage(
    modifier: Modifier = Modifier,
    @DrawableRes image: Int
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth()
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