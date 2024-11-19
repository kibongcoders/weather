package com.kibong.weather.weather.feature.typhoon.service

import com.kibong.weather.weather.feature.air_quality_forecast.service.AirQualityForecastService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest

class TyphoonServiceTest {

    @Autowired
    lateinit var typhoonService: TyphoonService

    @Test
    fun getTodayTyphoonInfo() {
        typhoonService.getTodayTyphoonInfo()
    }
}