package com.kibong.weather.weather.feature.yello_dust.service

import com.kibong.weather.weather.feature.air_quality_forecast.service.AirQualityForecastService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
class AirQualityForecastServiceTest {

    @Autowired
    lateinit var saveAirQualityForecastService: AirQualityForecastService

    @Test
    fun getAirPollution() {
        saveAirQualityForecastService.saveAirQualityForecast(0, 100, "2024-11-07")
    }
}