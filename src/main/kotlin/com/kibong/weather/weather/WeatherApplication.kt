package com.kibong.weather.weather

import com.kibong.weather.weather.config.BaseUrlProperties
import com.kibong.weather.weather.config.KeyProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(*[BaseUrlProperties::class, KeyProperties::class])
class WeatherApplication

fun main(args: Array<String>) {
    runApplication<WeatherApplication>(*args)
}
