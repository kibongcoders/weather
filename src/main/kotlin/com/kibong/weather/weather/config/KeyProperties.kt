package com.kibong.weather.weather.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding

@ConfigurationProperties(prefix = "api.key")
data class KeyProperties @ConstructorBinding constructor(
    val encoding: String,
    val decoding: String
)