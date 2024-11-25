package com.kibong.weather.weather.feature.typhoon.service

import com.kibong.weather.weather.common.api.ApiResponseDto
import com.kibong.weather.weather.common.api.ApiResponseHeaderDto
import com.kibong.weather.weather.feature.typhoon.dto.TyphoonResponseDto
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.assertEquals

@SpringBootTest

class TyphoonServiceTest {

    @Autowired
    lateinit var typhoonService: TyphoonService

    @Test
    fun getTodayTyphoonInfo() {
        val todayTyphoonInfo: ApiResponseDto<TyphoonResponseDto>? = typhoonService.getTodayTyphoonInfo()
        if (todayTyphoonInfo != null) {
            assertEquals(todayTyphoonInfo.header::class, ApiResponseHeaderDto::class)
            assertEquals(todayTyphoonInfo.body.items.item.first()::class, TyphoonResponseDto::class)
        }
    }
}