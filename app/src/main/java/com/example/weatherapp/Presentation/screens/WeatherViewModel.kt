package com.example.weatherapp.Presentation.screens


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.Presentation.components.WeatherUiState
import com.example.weatherapp.UseCases.GetCurrentWeatherUseCase
import com.example.weatherapp.UseCases.GetLastSearchedCityUseCase
import com.example.weatherapp.UseCases.SaveLastSearchedCityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val getLastSearchedCityUseCase: GetLastSearchedCityUseCase,
    private val saveLastSearchedCityUseCase: SaveLastSearchedCityUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<WeatherUiState>(WeatherUiState.Initial)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getLastSearchedCityUseCase()?.let { cityName ->
                fetchWeather(cityName)
            }
        }
    }

    fun fetchWeather(cityName: String = saveLastSearchedCityUseCase.toString()) {
        viewModelScope.launch {
            _uiState.value = WeatherUiState.Loading
            getCurrentWeatherUseCase(cityName)
                .onSuccess { weather ->
                    _uiState.value = WeatherUiState.Success(weather)
                    saveLastSearchedCityUseCase(cityName)
                }
                .onFailure { error ->
                    _uiState.value = WeatherUiState.Error(error.message ?: "Unknown error")
                }
        }
    }
}