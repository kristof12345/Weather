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
 * @param lon City geo location, longitude
 * @param lat City geo location, latitude
 */
data class Location (

    /* City geo location, longitude */
    val lon: Double,
    /* City geo location, latitude */
    val lat: Double
) {
}