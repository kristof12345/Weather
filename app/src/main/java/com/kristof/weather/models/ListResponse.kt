package com.kristof.weather.models

data class ListResponse<T>(
    var list: List<T>
)