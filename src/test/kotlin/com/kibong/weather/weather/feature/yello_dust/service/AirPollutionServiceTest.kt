package com.kibong.weather.weather.feature.yello_dust.service

import com.kibong.weather.weather.feature.air_pollution.service.AirPollutionService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
class AirPollutionServiceTest {

    @Autowired
    lateinit var airPollutionService: AirPollutionService

    @Test
    fun getAirPollution() {
        airPollutionService.getAirPollution()
    }
}