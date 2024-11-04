package com.kibong.weather.weather.feature.yello_dust.service

import com.kibong.weather.weather.config.BaseUrlProperties
import com.kibong.weather.weather.feature.yellow_dust.service.YellowDustService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
class YellowDustServiceTest {

    @Autowired
    lateinit var yellowDustService: YellowDustService

    @Test
    fun getYellowDust() {
        yellowDustService.getYellowDust()
    }
}