package com.kibong.weather.weather.feature.air_pollution.dto

import kotlinx.serialization.Serializable

@Serializable
data class AirQualityForecastResponseDto(
    val imageUrl4: String?,
    val informCode: String?,
    val imageUrl5: String?,
    val imageUrl6: String?,
    val actionKnack: String?,
    val informCause: String?,
    val informOverall: String?,
    val informData: String?,
    val informGrade: String?,
    val dataTime: String?,
    val imageUrl3: String?,
    val imageUrl2: String?,
    val imageUrl1: String?
)
