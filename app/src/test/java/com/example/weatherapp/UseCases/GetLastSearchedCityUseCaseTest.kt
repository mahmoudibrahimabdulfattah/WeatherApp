package com.example.weatherapp.UseCases

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
class GetLastSearchedCityUseCaseTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var repository: WeatherRepository
    private lateinit var useCase: GetLastSearchedCityUseCase

    @Before
    fun setup() {
        repository = mock()
        useCase = GetLastSearchedCityUseCase(repository)
    }

    @Test
    fun `invoke returns last searched city when available`() = runTest {
        // Arrange
        val cityName = "London"
        whenever(repository.getLastSearchedCity())
            .thenReturn(cityName)

        // Act
        val result = useCase()

        // Assert
        assertThat(result).isEqualTo(cityName)
    }

    @Test
    fun `invoke returns null when no last search available`() = runTest {
        // Arrange
        whenever(repository.getLastSearchedCity())
            .thenReturn(null)

        // Act
        val result = useCase()

        // Assert
        assertThat(result).isNull()
    }
}