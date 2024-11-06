package com.example.weatherapp.UseCases

import com.example.weatherapp.DataSource.Models.MainWeather
import com.example.weatherapp.DataSource.Models.WeatherInfo
import com.example.weatherapp.Repository.WeatherRepository
import com.example.weatherapp.utils.MainDispatcherRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class GetCurrentWeatherUseCaseTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var repository: WeatherRepository
    private lateinit var useCase: GetCurrentWeatherUseCase

    @Before
    fun setup() {
        repository = mock()
        useCase = GetCurrentWeatherUseCase(repository)
    }

    @Test
    fun `invoke returns weather result when both calls succeed`() = runTest {
        // Arrange
        val cityName = "London"
        val mainWeather = MainWeather(
            temp = 20.0,
            feels_like = 19.5,
            temp_min = 18.0,
            temp_max = 22.0,
            pressure = 1013,
            sea_level = 1015,
            grnd_level = 1010,
            humidity = 70,
            temp_kf = 0.0
        )
        val weatherInfo = WeatherInfo(
            id = 800,
            main = "Clear",
            description = "Sunny",
            icon = "01d"
        )

        whenever(repository.getCurrentWeather(cityName))
            .thenReturn(Result.success(mainWeather))
        whenever(repository.getWeatherInfo(cityName))
            .thenReturn(Result.success(weatherInfo))

        // Act
        val result = useCase(cityName)

        // Assert
        assertThat(result.isSuccess).isTrue()
        result.getOrNull()?.let { weatherResult ->
            assertThat(weatherResult.mainWeather).isEqualTo(mainWeather)
            assertThat(weatherResult.weatherInfo).isEqualTo(weatherInfo)
        }
        verify(repository).saveLastSearchedCity(cityName)
    }

    @Test
    fun `invoke returns failure when getCurrentWeather fails`() = runTest {
        // Arrange
        val cityName = "London"
        val error = Exception("Network error")
        whenever(repository.getCurrentWeather(cityName))
            .thenReturn(Result.failure(error))

        // Act
        val result = useCase(cityName)

        // Assert
        assertThat(result.isFailure).isTrue()
        assertThat(result.exceptionOrNull()).isEqualTo(error)
    }

    @Test
    fun `invoke returns failure when getWeatherInfo fails`() = runTest {
        // Arrange
        val cityName = "London"
        val mainWeather = MainWeather(
            temp = 20.0,
            feels_like = 19.5,
            temp_min = 18.0,
            temp_max = 22.0,
            pressure = 1013,
            sea_level = 1015,
            grnd_level = 1010,
            humidity = 70,
            temp_kf = 0.0
        )

        whenever(repository.getCurrentWeather(cityName))
            .thenReturn(Result.success(mainWeather))
        whenever(repository.getWeatherInfo(cityName))
            .thenReturn(Result.failure(Exception("Failed to get weather info")))

        // Act
        val result = useCase(cityName)

        // Assert
        assertThat(result.isFailure).isTrue()
        assertThat(result.exceptionOrNull()?.message).isEqualTo("Failed to get weather info")
    }
}