package com.example.weatherapp.Presentation.components

import com.example.weatherapp.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.Presentation.theme.ColorGradient1
import com.example.weatherapp.Presentation.theme.ColorGradient2
import com.example.weatherapp.Presentation.theme.ColorGradient3
import com.example.weatherapp.Presentation.theme.DarkTextPrimary
import com.example.weatherapp.Presentation.theme.LightTextPrimary
import com.example.weatherapp.Presentation.theme.LightTextSecondary


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActionBar(
    cityName: String,
    onCityNameChange: (String) -> Unit,
    onSearchClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val primaryTextColor = if (isSystemInDarkTheme()) LightTextPrimary else DarkTextPrimary

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        OutlinedTextField(
            value = cityName,
            onValueChange = onCityNameChange,
            placeholder = { Text("Enter city name") },
            modifier = Modifier.weight(1f),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedTextColor = primaryTextColor,
                cursorColor = primaryTextColor
            )
        )

        Spacer(modifier = Modifier.width(8.dp))

        Button(onClick = onSearchClick) {
            Text(
                text = "Search",
                color = primaryTextColor
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ActionBarPreview() {
    //ActionBar()
}

@Composable
private fun LocationInfo(
    modifier: Modifier = Modifier,
    location: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_location_pin),
                contentDescription = null,
                modifier = Modifier.height(18.dp),
                contentScale = ContentScale.FillHeight
            )
            Text(
                text = location,
                style = MaterialTheme.typography.titleLarge,
                color = LightTextPrimary,
                fontWeight = FontWeight.Bold
            )
        }
        ProgressBar()
    }
}

@Composable
private fun ProgressBar(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(
                brush = Brush.linearGradient(
                    0f to ColorGradient1,
                    0.25f to ColorGradient2,
                    1f to ColorGradient3
                ),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(
                vertical = 2.dp,
                horizontal = 10.dp
            )
    ) {
        Text(
            text = "Change City",
            style = MaterialTheme.typography.labelSmall,
            color = LightTextSecondary.copy(alpha = 0.7f)
        )
    }
}
