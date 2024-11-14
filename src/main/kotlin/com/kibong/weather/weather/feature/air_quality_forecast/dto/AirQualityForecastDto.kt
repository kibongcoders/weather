package com.kibong.weather.weather.feature.air_quality_forecast.dto

import com.kibong.weather.weather.domain.AirQualityForecast
import java.time.LocalDate
import java.util.*

data class AirQualityForecastDto(
    val aisQualityForecastId: UUID,
    val imageUrl6:String?,
    val imageUrl5: String?,
    val imageUrl4: String?,
    val imageUrl3: String?,
    val imageUrl2: String?,
    val imageUrl1: String?,
    val informCode: String?,
    val actionKnack: String?,
    val informData: LocalDate?,
    val informCause: String?,
    val informOverall: String?,
    val informGrade: String?
) {
    companion object {
        fun fromAirQualityForecast(airQualityForecast: AirQualityForecast): AirQualityForecastDto {
            return AirQualityForecastDto(
                aisQualityForecastId = airQualityForecast.aisQualityForecastId ,
                imageUrl6 = airQualityForecast.informCause,
                imageUrl5 = airQualityForecast.imageUrl5,
                imageUrl4 = airQualityForecast.imageUrl4,
                imageUrl3 = airQualityForecast.imageUrl3,
                imageUrl2 = airQualityForecast.imageUrl2,
                imageUrl1 = airQualityForecast.imageUrl1,
                informCode = airQualityForecast.informCode,
                actionKnack = airQualityForecast.actionKnack,
                informData = airQualityForecast.informData,
                informCause = airQualityForecast.informCause,
                informOverall = airQualityForecast.informOverall,
                informGrade = airQualityForecast.informGrade
            )
        }
    }
}
