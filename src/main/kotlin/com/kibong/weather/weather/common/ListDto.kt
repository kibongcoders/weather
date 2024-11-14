package com.kibong.weather.weather.common

data class ListDto<T>(
    val totalCount: Int,
    val items: List<T>
)
