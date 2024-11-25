package com.kibong.weather.weather.common.api

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule

@Serializable
data class ApiResponseDto<T>(
    val header: ApiResponseHeaderDto,
    val body: ApiResponseBodyDto<T>
)