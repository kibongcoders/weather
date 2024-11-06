package com.kibong.weather.weather.feature.air_pollution.dto

import kotlinx.serialization.Serializable

@Serializable
data class ResponseDto<T>(
    val totalCount : Int,
    val items: List<T>,
    val pageNo: Int,
    val numOfRows: Int
)
