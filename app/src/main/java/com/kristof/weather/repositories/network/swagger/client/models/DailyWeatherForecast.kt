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

import io.swagger.client.models.DailyTemperature
import io.swagger.client.models.WeatherReport

/**
 * 
 * @param weather 
 * @param temp 
 * @param speed Wind speed. Unit Default: meter/sec, Metric: meter/sec, Imperial: miles/hour.
 * @param deg Wind direction, degrees (meteorological)
 * @param dt Date and time. Unix, UTC
 */
data class DailyWeatherForecast (

    val weather: kotlin.Array<WeatherReport>,
    val temp: DailyTemperature,
    /* Wind speed. Unit Default: meter/sec, Metric: meter/sec, Imperial: miles/hour. */
    val speed: Double,
    /* Wind direction, degrees (meteorological) */
    val deg: Double,
    /* Date and time. Unix, UTC */
    val dt: Double,
    val rain: Double?,
) {
}