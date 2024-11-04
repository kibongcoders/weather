package com.kibong.weather.weather.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding

@ConfigurationProperties(prefix = "api.base-url")
data class BaseUrlProperties @ConstructorBinding constructor(
    val airPollution: String
)