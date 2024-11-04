package com.kibong.weather.weather.feature.yellow_dust.service

import com.kibong.weather.weather.config.BaseUrlProperties
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
@RequiredArgsConstructor
class YellowDustService(
    private val restTemplate: RestTemplate,
    private val baseUrlProperties: BaseUrlProperties
) {

    fun getYellowDust() {
        restTemplate.getForObject(baseUrlProperties.airPollution, String::class.java)
    }
}