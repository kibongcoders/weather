package com.kibong.weather.weather.common.api

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponseHeaderDto(
    val resultCode: String,
    val resultMsg: String
)
