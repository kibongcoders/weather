package com.kibong.weather.weather.common.api

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponseBodyDto<T>(
    val dataType: String,
    val items: ItemResponseDto<T>,
    val pageNo: Int,
    val numOfRows: Int,
    val totalCount: Int
)
