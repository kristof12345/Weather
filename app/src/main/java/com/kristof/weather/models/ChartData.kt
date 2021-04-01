package com.kristof.weather.models

import com.anychart.chart.common.dataentry.DataEntry

class ChartData(date: String, high: Double, low: Double) : DataEntry() {
    init {
        setValue("date", date)
        setValue("high", high)
        setValue("low", low)
    }
}