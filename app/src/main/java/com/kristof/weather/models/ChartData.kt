package com.kristof.weather.models

import com.anychart.chart.common.dataentry.DataEntry

data class ChartData(val date: String, val value: Double) : DataEntry() {
    init {
        setValue("date", date)
        setValue("value", value)
    }
}

data class HighLowChartData(val date: String, val high: Double, val low: Double) : DataEntry() {
    init {
        setValue("date", date)
        setValue("high", high)
        setValue("low", low)
    }
}