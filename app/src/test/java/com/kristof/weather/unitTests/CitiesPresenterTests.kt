package com.kristof.weather.unitTests

import android.content.Context
import android.content.SharedPreferences
import com.kristof.weather.getDefaultSharedPreferences
import com.kristof.weather.interfaces.ICitiesRepository
import com.kristof.weather.interfaces.IWeatherRepository
import com.kristof.weather.mocks.CitiesRepositoryMock
import com.kristof.weather.mocks.WeatherRepositoryMock
import com.kristof.weather.presenters.CitiesPresenter
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.any

class CitiesPresenterTests {

    private var sharedPrefs: SharedPreferences = Mockito.mock(SharedPreferences::class.java)
    private var context: Context = Mockito.mock(Context::class.java)
    private var citiesRepository: ICitiesRepository = CitiesRepositoryMock()
    private var weatherRepository: IWeatherRepository = WeatherRepositoryMock()

    @Before
    fun before() {
        Mockito.`when`(context.getSharedPreferences(any(), any())).thenReturn(this.sharedPrefs)
        Mockito.`when`(sharedPrefs.getString(any(), any())).thenReturn("metric")
    }

    @Test
    fun mockSharedPrefsTest() {
        Mockito.`when`(sharedPrefs.getString(any(), any())).thenReturn("metric")
        Assert.assertEquals("metric", context.getDefaultSharedPreferences().getString("asd", "fgh"))
    }

    @Test
    fun addCityTest() {
        // Arrange
        var citiesPresenter = CitiesPresenter(citiesRepository, weatherRepository)

        // Act
        citiesPresenter.addCity("Budapest", context)

        // Assert
        Assert.assertEquals("metric", context.getDefaultSharedPreferences().getString("asd", "fgh"))
    }

}