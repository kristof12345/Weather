package com.kristof.weather.presenters

import android.content.Context
import com.kristof.weather.getDefaultSharedPreferences

object UnitFormatter {
    fun getTemperature(context: Context): String {
        return formatTemperature(getUnit(context))
    }

    private fun formatTemperature(unit: String): String {
        return when (unit) {
            "metric" -> "\u2103"    //Celsius
            "standard" -> "K"       //Kelvin
            "imperial" -> "\u2109"  //Fahrenheit
            else -> ""
        }
    }

    private fun getUnit(context: Context): String {
        val preferences = context.getDefaultSharedPreferences()
        return preferences.getString("unit", "metric")!!
    }
}