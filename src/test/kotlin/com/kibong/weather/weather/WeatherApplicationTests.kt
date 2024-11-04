package com.kibong.weather.weather

import com.kibong.weather.weather.config.BaseUrlProperties
import mu.KotlinLogging
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class WeatherApplicationTests {

    private val logger = KotlinLogging.logger {}

    @Autowired
    lateinit var baseUrlProperties: BaseUrlProperties

    @Test
    fun contextLoads() {
        logger.info { "baseUrlProperties: ${baseUrlProperties.airPollution}" }

    }

}
