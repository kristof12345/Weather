package com.kristof.weather.unitTests

import android.content.Context
import android.content.SharedPreferences
import com.kristof.weather.mocks.WeatherData
import com.kristof.weather.mocks.WeatherRepositoryMock
import com.kristof.weather.models.*
import com.kristof.weather.presenters.WeatherPresenter
import com.kristof.weather.views.weather.current.ICurrentWeatherScreen
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import java.text.SimpleDateFormat

class WeatherPresenterTests {

    private var sharedPrefs: SharedPreferences = Mockito.mock(SharedPreferences::class.java)
    private var context: Context = Mockito.mock(Context::class.java)

    @Before
    fun before() {
        Mockito.`when`(context.getSharedPreferences(any(), any())).thenReturn(this.sharedPrefs)
        Mockito.`when`(sharedPrefs.getString(any(), any())).thenReturn("metric")
    }

    @Test
    fun getCurrentWeatherTest() {
        // Arrange
        var screenMock = Mockito.mock(ICurrentWeatherScreen::class.java)
        var weatherPresenter = WeatherPresenter(WeatherRepositoryMock())

        // Act
        weatherPresenter.attachScreen(screenMock)
        weatherPresenter.getCurrentWeather(City("Budapest"), context)

        // Assert
        verify(screenMock).showWeather(WeatherData.Current)
    }

    @Test
    fun getCurrentWeatherWithNetworkErrorTest() {
        // Arrange
        var screenMock = Mockito.mock(ICurrentWeatherScreen::class.java)
        var weatherPresenter = WeatherPresenter(WeatherRepositoryMock(true))

        // Act
        weatherPresenter.attachScreen(screenMock)
        weatherPresenter.getCurrentWeather(City("Budapest"), context)

        // Assert
        verify(screenMock).showError("A network error occurred.")
    }

    @Test
    fun getHourlyWeatherTest() {
        // Arrange
        var screenMock = Mockito.mock(ICurrentWeatherScreen::class.java)
        var weatherPresenter = WeatherPresenter(WeatherRepositoryMock())

        // Act
        weatherPresenter.attachScreen(screenMock)
        weatherPresenter.getHourlyWeather(City("Budapest"), context)

        // Assert
        verify(screenMock).showChart(listOf(ChartData(SimpleDateFormat("HH:mm").format(WeatherData.hourly.first().dt), 25.9)))
    }

    @Test
    fun getHourlyWeatherWithNetworkErrorTest() {
        // Arrange
        var screenMock = Mockito.mock(ICurrentWeatherScreen::class.java)
        var weatherPresenter = WeatherPresenter(WeatherRepositoryMock(true))

        // Act
        weatherPresenter.attachScreen(screenMock)
        weatherPresenter.getHourlyWeather(City("Budapest"), context)

        // Assert
        verify(screenMock).showError("A network error occurred.")
    }

    @Test
    fun getWeatherSummaryTest() {
        // Arrange
        var screenMock = Mockito.mock(ICurrentWeatherScreen::class.java)
        var weatherPresenter = WeatherPresenter(WeatherRepositoryMock())

        // Act
        weatherPresenter.attachScreen(screenMock)
        weatherPresenter.getCurrentWeather(City("Budapest"), context)

        // Assert
        verify(screenMock).showWeather(CurrentWeather(Location(1.0, 2.0), 16.5, 10.5, 20.5, 100.99, 5.7, 300, Wind(10.4, 180.0), "icon", "nice weather"))
    }
}