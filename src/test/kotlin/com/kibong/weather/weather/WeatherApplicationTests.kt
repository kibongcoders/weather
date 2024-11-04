package com.kibong.weather.weather

import com.kibong.weather.weather.config.BaseUrlProperties
import lombok.extern.slf4j.Slf4j
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.math.log

@SpringBootTest
@Slf4j
class WeatherApplicationTests {

    @Autowired
    lateinit var baseUrlProperties: BaseUrlProperties

    @Test
    fun contextLoads() {


    }

}
