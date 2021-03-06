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

import io.swagger.client.models.HourlyWeatherForecastMain
import io.swagger.client.models.WeatherReport
import io.swagger.client.models.Wind

/**
 * 
 * @param weather 
 * @param main 
 * @param dt Date and time. Unix, UTC
 * @param wind 
 */
data class HourlyWeatherForecast (

    val weather: kotlin.Array<WeatherReport>,
    val main: HourlyWeatherForecastMain,
    /* Date and time. Unix, UTC */
    val dt: Double,
    val wind: Wind
) {
}