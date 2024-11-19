package com.kibong.weather.weather.feature.typhoon.dto

import org.springframework.http.HttpMethod

data class RequestApiDto(
    val url: String,
    val uri: String,
    val paramMap: Map<String, Any>?,
    val bodyMap: Map<String, Any>?,
    val headerMap: Map<String, String>?,
    val httpMethod: HttpMethod
)
