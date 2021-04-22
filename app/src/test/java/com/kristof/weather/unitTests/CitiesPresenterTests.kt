package com.kristof.weather.unitTests

import android.content.Context
import android.content.SharedPreferences
import com.kristof.weather.interfaces.ICitiesRepository
import com.kristof.weather.interfaces.IWeatherRepository
import com.kristof.weather.mocks.CitiesRepositoryMock
import com.kristof.weather.mocks.WeatherRepositoryMock
import com.kristof.weather.models.City
import com.kristof.weather.models.Location
import com.kristof.weather.presenters.CitiesPresenter
import com.kristof.weather.views.cities.ICitiesScreen
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.verify

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
    fun getCitiesEmptyTest() {
        // Arrange
        var screenMock = Mockito.mock(ICitiesScreen::class.java)
        var citiesPresenter = CitiesPresenter(citiesRepository, weatherRepository)

        // Act
        citiesPresenter.attachScreen(screenMock)
        citiesPresenter.getCities(context)

        // Assert
        verify(screenMock).showCities(mutableListOf())
    }

    @Test
    fun addCityTest() {
        // Arrange
        var screenMock = Mockito.mock(ICitiesScreen::class.java)
        var citiesPresenter = CitiesPresenter(citiesRepository, weatherRepository)

        // Act
        citiesPresenter.attachScreen(screenMock)
        citiesPresenter.addCity("Budapest", context)

        // Assert
        verify(screenMock).showCities(mutableListOf(City("Budapest", Location(1.0, 2.0), 16.5, "icon")))

        // Clean
        citiesPresenter.deleteCity("Budapest", context)
    }

    @Test
    fun deleteCityTest() {
        // Arrange
        var screenMock = Mockito.mock(ICitiesScreen::class.java)
        var citiesPresenter = CitiesPresenter(citiesRepository, weatherRepository)
        citiesPresenter.addCity("London", context)

        // Act
        citiesPresenter.attachScreen(screenMock)
        citiesPresenter.deleteCity("London", context)

        // Assert
        verify(screenMock).showCities(mutableListOf())
    }

    @Test
    fun getCitiesListTest() {
        // Arrange
        var screenMock = Mockito.mock(ICitiesScreen::class.java)
        var citiesPresenter = CitiesPresenter(citiesRepository, weatherRepository)
        citiesPresenter.addCity("New York", context)

        // Act
        citiesPresenter.attachScreen(screenMock)
        citiesPresenter.getCities(context)

        // Assert
        verify(screenMock).showCities(mutableListOf(City("New York", Location(1.0, 2.0), 16.5, "icon")))

        // Clean
        citiesPresenter.deleteCity("New York", context)
    }

    @Test
    fun attachAndDetachScreenTest() {
        // Arrange
        var screenMock = Mockito.mock(ICitiesScreen::class.java)
        var citiesPresenter = CitiesPresenter(citiesRepository, weatherRepository)

        // Act
        citiesPresenter.addCity("New York", context)
        citiesPresenter.deleteCity("New York", context)

        citiesPresenter.attachScreen(screenMock)

        citiesPresenter.addCity("Budapest", context)

        citiesPresenter.detachScreen()
        
        // Assert
        verify(screenMock).showCities(mutableListOf(City("Budapest", Location(1.0, 2.0), 16.5, "icon")))
    }

    @Test
    fun getCitiesWithNetworkErrorTest() {
        // Arrange
        var screenMock = Mockito.mock(ICitiesScreen::class.java)
        var citiesPresenter = CitiesPresenter(citiesRepository, WeatherRepositoryMock(true))
        citiesPresenter.addCity("New York", context)

        // Act
        citiesPresenter.attachScreen(screenMock)
        citiesPresenter.getCities(context)

        // Assert
        verify(screenMock).showError("A network error occurred.")

        // Clean
        citiesPresenter.deleteCity("New York", context)
    }
}