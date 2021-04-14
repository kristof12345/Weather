package com.kristof.weather.presenters

import android.content.Context
import com.kristof.weather.getDefaultSharedPreferences

object UnitFormatter {
    fun Temperature(context: Context): String {
        return FormatTemperature(getUnit(context))
    }

    private fun FormatTemperature(unit: String): String {
        if(unit == "metric") return "\u2103"
        if(unit == "standard") return "F"
        if(unit == "imperial") return "K"
        return ""
    }

    private fun getUnit(context: Context): String {
        val preferences = context.getDefaultSharedPreferences()
        return preferences.getString("unit", "metric")!!
    }
}