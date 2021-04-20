package com.kristof.weather.presenters

import android.content.Context
import com.kristof.weather.getDefaultSharedPreferences

object UnitFormatter {
    fun getTemperatureFormat(context: Context): String {
        return formatTemperature(getUnit(context))
    }

    fun getSpeedFormat(context: Context): Any? {
        return formatSpeed(getUnit(context))
    }

    private fun formatTemperature(unit: String): String {
        return when (unit) {
            "metric" -> "\u2103"    //Celsius
            "standard" -> "K"       //Kelvin
            "imperial" -> "\u2109"  //Fahrenheit
            else -> ""
        }
    }

    private fun formatSpeed(unit: String): String {
        return when (unit) {
            "metric" -> "m/s"    //Celsius
            "standard" -> "m/s"       //Kelvin
            "imperial" -> "miles/h"  //Fahrenheit
            else -> ""
        }
    }

    private fun getUnit(context: Context): String {
        val preferences = context.getDefaultSharedPreferences()
        return preferences.getString("unit", "metric")!!
    }

    fun Double.format(digits: Int) = "%.${digits}f".format(this)
}
