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
 * @param speed Wind speed. Unit Default: meter/sec, Metric: meter/sec, Imperial: miles/hour.
 * @param deg Wind direction, degrees (meteorological)
 */
data class Wind (

    /* Wind speed. Unit Default: meter/sec, Metric: meter/sec, Imperial: miles/hour. */
    val speed: Double,
    /* Wind direction, degrees (meteorological) */
    val deg: Double
) {
}