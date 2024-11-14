package com.kibong.weather.weather.feature.air_quality_forecast.controller

import com.kibong.weather.weather.common.ResponseDto
import com.kibong.weather.weather.feature.air_quality_forecast.service.AirQualityForecastService
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/air-quality-forecast")
@RequiredArgsConstructor
class AirQualityForecastController(
    private val airQualityForecastService: AirQualityForecastService
) {

    @GetMapping("/{searchDate}")
    fun getAirQualityForecast(@PathVariable searchDate: String): ResponseDto {
        return ResponseDto(airQualityForecastService.getAirQualityForecast(searchDate))
    }
}