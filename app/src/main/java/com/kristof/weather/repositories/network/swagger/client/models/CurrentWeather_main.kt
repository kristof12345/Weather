/**
 * Weather Server
 * This is a weather data server. For each point on the globe, we provide historical, current and forecasted weather data via light-speed APIs.
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */
package io.swagger.client.models


/**
 * 
 * @param temp Temperature. Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit
 * @param tempMin Minimum temperature at the moment
 * @param tempMax Maximum temperature at the moment.
 * @param pressure Atmospheric pressure (on the sea level, if there is no sea_level or grnd_level data), hPa
 * @param humidity Humidity, %
 */
data class CurrentWeatherMain (

    /* Temperature. Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit */
    val temp: Double,
    /* Minimum temperature at the moment */
    val temp_min: Double,
    /* Maximum temperature at the moment. */
    val temp_max: Double,
    /* Atmospheric pressure (on the sea level, if there is no sea_level or grnd_level data), hPa */
    val pressure: Double,
    /* Humidity, % */
    val humidity: Double
) {
}