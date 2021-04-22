package com.kristof.weather.unitTests

import android.content.Context
import android.content.SharedPreferences
import com.kristof.weather.mocks.WeatherData
import com.kristof.weather.mocks.WeatherRepositoryMock
import com.kristof.weather.models.City
import com.kristof.weather.models.HighLowChartData
import com.kristof.weather.models.WeatherForecastSummary
import com.kristof.weather.presenters.WeatherForecastPresenter
import com.kristof.weather.views.weather.forecast.IWeatherForecastScreen
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import java.text.SimpleDateFormat

class WeatherForecastPresenterTests {

    private var sharedPrefs: SharedPreferences = Mockito.mock(SharedPreferences::class.java)
    private var context: Context = Mockito.mock(Context::class.java)

    @Before
    fun before() {
        Mockito.`when`(context.getSharedPreferences(any(), any())).thenReturn(this.sharedPrefs)
        Mockito.`when`(sharedPrefs.getString(any(), any())).thenReturn("metric")
    }

    @Test
    fun getDailyWeatherTest() {
        // Arrange
        var screenMock = Mockito.mock(IWeatherForecastScreen::class.java)
        var weatherPresenter = WeatherForecastPresenter(WeatherRepositoryMock())

        // Act
        weatherPresenter.attachScreen(screenMock)
        weatherPresenter.getDailyWeather(City("Budapest"), context)

        // Assert
        verify(screenMock).showChart(listOf(HighLowChartData(SimpleDateFormat("MM.dd").format(WeatherData.daily.first().dt), 30.2, 20.4)))
    }

    @Test
    fun getWeatherSummaryTest() {
        // Arrange
        var screenMock = Mockito.mock(IWeatherForecastScreen::class.java)
        var weatherPresenter = WeatherForecastPresenter(WeatherRepositoryMock(false, true))

        // Act
        weatherPresenter.attachScreen(screenMock)
        weatherPresenter.getDailyWeather(City("Budapest"), context)

        // Assert
        verify(screenMock).showWeather(WeatherForecastSummary(25.0, 10.0, 40.0, 150.0, 300.0))
    }

    @Test
    fun getDailyWeatherWithNetworkErrorTest() {
        // Arrange
        var screenMock = Mockito.mock(IWeatherForecastScreen::class.java)
        var weatherPresenter = WeatherForecastPresenter(WeatherRepositoryMock(true))

        // Act
        weatherPresenter.attachScreen(screenMock)
        weatherPresenter.getDailyWeather(City("Budapest"), context)

        // Assert
        verify(screenMock).showError("A network error occurred.")
    }
}