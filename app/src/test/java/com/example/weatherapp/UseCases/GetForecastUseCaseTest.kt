package com.example.weatherapp.UseCases

import com.example.weatherapp.DataSource.Models.Forecast
import com.example.weatherapp.Repository.WeatherRepository
import com.example.weatherapp.utils.MainDispatcherRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class GetForecastUseCaseTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var repository: WeatherRepository
    private lateinit var useCase: GetForecastUseCase

    @Before
    fun setup() {
        repository = mock()
        useCase = GetForecastUseCase(repository)
    }

    @Test
    fun `invoke returns forecast list when successful`() = runTest {
        // Arrange
        val cityName = "London"
        val forecasts = listOf(
            Forecast(
                date = "2024-03-06",
                dayOfWeek = "Wednesday",
                temperature = 20.0,
                condition = "Clear",
                icon = "01d",
                airQuality = 1
            ),
            Forecast(
                date = "2024-03-07",
                dayOfWeek = "Thursday",
                temperature = 22.0,
                condition = "Cloudy",
                icon = "02d",
                airQuality = 2
            )
        )
        whenever(repository.getForecast(cityName))
            .thenReturn(Result.success(forecasts))

        // Act
        val result = useCase(cityName)

        // Assert
        assertThat(result.isSuccess).isTrue()
        assertThat(result.getOrNull()).isEqualTo(forecasts)
    }

    @Test
    fun `invoke returns failure when repository fails`() = runTest {
        // Arrange
        val cityName = "London"
        val error = Exception("Network error")
        whenever(repository.getForecast(cityName))
            .thenReturn(Result.failure(error))

        // Act
        val result = useCase(cityName)

        // Assert
        assertThat(result.isFailure).isTrue()
        assertThat(result.exceptionOrNull()).isEqualTo(error)
    }
}