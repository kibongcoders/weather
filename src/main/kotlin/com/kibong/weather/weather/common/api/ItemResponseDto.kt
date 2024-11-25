package com.kibong.weather.weather.common.api

import kotlinx.serialization.Serializable

@Serializable
data class ItemResponseDto<T>(
    val item : List<T>
)
