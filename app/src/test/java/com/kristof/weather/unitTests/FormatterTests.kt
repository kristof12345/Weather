package com.kristof.weather.unitTests

import android.content.Context
import android.content.SharedPreferences
import com.kristof.weather.getDefaultSharedPreferences
import com.kristof.weather.presenters.Formatter
import com.kristof.weather.presenters.Formatter.format
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.any


class FormatterTests {

    private var sharedPrefs: SharedPreferences = Mockito.mock(SharedPreferences::class.java)
    private var context: Context = Mockito.mock(Context::class.java)

    @Before
    fun before() {
        Mockito.`when`(context.getSharedPreferences(any(), any())).thenReturn(this.sharedPrefs)
    }

    @Test
    fun mockSharedPrefsTest() {
        Mockito.`when`(sharedPrefs.getString(any(), any())).thenReturn("metric")
        assertEquals("metric", context.getDefaultSharedPreferences().getString("asd", "fgh"))
    }

    @Test
    fun metricTemperatureFormatTest() {
        // Arrange
        Mockito.`when`(sharedPrefs.getString(any(), any())).thenReturn("metric")

        // Act
        var format = Formatter.getTemperatureFormat(context)

        // Assert
        assertEquals("\u2103", format)
    }

    @Test
    fun standardTemperatureFormatTest() {
        // Arrange
        Mockito.`when`(sharedPrefs.getString(any(), any())).thenReturn("standard")

        // Act
        var format = Formatter.getTemperatureFormat(context)

        // Assert
        assertEquals("K", format)
    }

    @Test
    fun imperialTemperatureFormatTest() {
        // Arrange
        Mockito.`when`(sharedPrefs.getString(any(), any())).thenReturn("imperial")

        // Act
        var format = Formatter.getTemperatureFormat(context)

        // Assert
        assertEquals("\u2109", format)
    }

    @Test
    fun emptyTemperatureFormatTest() {
        // Arrange
        Mockito.`when`(sharedPrefs.getString(any(), any())).thenReturn("")

        // Act
        var format = Formatter.getTemperatureFormat(context)

        // Assert
        assertEquals("", format)
    }

    @Test
    fun metricSpeedFormatTest() {
        // Arrange
        Mockito.`when`(sharedPrefs.getString(any(), any())).thenReturn("metric")

        // Act
        var format = Formatter.getSpeedFormat(context)

        // Assert
        assertEquals("m/s", format)
    }

    @Test
    fun standardSpeedFormatTest() {
        // Arrange
        Mockito.`when`(sharedPrefs.getString(any(), any())).thenReturn("standard")

        // Act
        var format = Formatter.getSpeedFormat(context)

        // Assert
        assertEquals("m/s", format)
    }

    @Test
    fun imperialSpeedFormatTest() {
        // Arrange
        Mockito.`when`(sharedPrefs.getString(any(), any())).thenReturn("imperial")

        // Act
        var format = Formatter.getSpeedFormat(context)

        // Assert
        assertEquals("miles/h", format)
    }

    @Test
    fun emptySpeedFormatTest() {
        // Arrange
        Mockito.`when`(sharedPrefs.getString(any(), any())).thenReturn("")

        // Act
        var format = Formatter.getSpeedFormat(context)

        // Assert
        assertEquals("", format)
    }

    @Test
    fun doubleFormatTest() {
        assertEquals("3", 3.14.format(0))
        assertEquals("3,1", 3.14.format(1))
        assertEquals("3,14", 3.14.format(2))
    }
}