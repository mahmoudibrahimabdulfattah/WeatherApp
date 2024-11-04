package com.example.weatherapp.Presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.Presentation.components.ForecastIntent
import com.example.weatherapp.Presentation.components.ForecastState
import com.example.weatherapp.UseCases.GetForecastUseCase
import com.example.weatherapp.UseCases.SaveLastSearchedCityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(
    private val getForecastUseCase: GetForecastUseCase,
    private val saveLastSearchedCityUseCase: SaveLastSearchedCityUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ForecastState())
    val state = _state.asStateFlow()

    fun processIntent(intent: ForecastIntent) {
        when (intent) {
            is ForecastIntent.LoadForecast -> loadForecast(intent.cityName)
            is ForecastIntent.RefreshForecast -> loadForecast(intent.cityName)
        }
    }

    private fun loadForecast(cityName: String = saveLastSearchedCityUseCase.toString()) {
        viewModelScope.launch {
            _state.value = state.value.copy(isLoading = true)
            getForecastUseCase(cityName)
                .onSuccess { forecast ->
                    _state.value = state.value.copy(
                        isLoading = false,
                        forecast = forecast,
                        error = null
                    )
                }
                .onFailure { error ->
                    _state.value = state.value.copy(
                        isLoading = false,
                        error = error.message
                    )
                }
        }
    }
}
