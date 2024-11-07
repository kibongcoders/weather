package com.kibong.weather.weather.feature.air_quality_forecast.repository

import com.kibong.weather.weather.domain.AirQualityForecast
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface AirQualityForecastRepository : JpaRepository<AirQualityForecast, UUID> {
}