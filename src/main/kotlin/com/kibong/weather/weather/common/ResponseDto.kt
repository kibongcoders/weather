package com.kibong.weather.weather.common

import java.time.LocalDateTime

data class ResponseDto(
    val content: Any,
    val responseTime: LocalDateTime = LocalDateTime.now()
)
