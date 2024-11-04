package com.example.weatherapp.Presentation.components.util

import java.text.SimpleDateFormat
import java.util.Locale

fun formatDate(inputDate: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
    val outputFormat = SimpleDateFormat("EEEE, dd MMM", Locale.ENGLISH)
    val date = inputFormat.parse(inputDate)
    return outputFormat.format(date!!)
}

fun formatDayMonth(inputDate: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
    val outputFormat = SimpleDateFormat("dd MMM", Locale.ENGLISH)
    val date = inputFormat.parse(inputDate)
    return outputFormat.format(date!!)
}