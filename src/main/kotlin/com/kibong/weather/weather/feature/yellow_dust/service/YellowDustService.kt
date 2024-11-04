package com.kibong.weather.weather.feature.yellow_dust.service

import com.kibong.weather.weather.config.BaseUrlProperties
import com.kibong.weather.weather.config.KeyProperties
import lombok.RequiredArgsConstructor
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

@Service
@RequiredArgsConstructor
class YellowDustService(
    private val restTemplate: RestTemplate,
    private val baseUrlProperties: BaseUrlProperties,
    private val keyProperties: KeyProperties
) {

    private val logger = KotlinLogging.logger {}

    fun getYellowDust() {
        val url = UriComponentsBuilder.fromHttpUrl(baseUrlProperties.airPollution)
            .path("/getMinuDustFrcstDspth")
            .queryParam("serviceKey", keyProperties.decoding)
            .queryParam("returnType", "json")
            .queryParam("numOfRows", 10)
            .queryParam("pageNo", 1)
            .queryParam("searchDate", "2024-11-04")
            .build()
            .toUriString()

        val returnObject = restTemplate.getForObject(url, String::class.java)
        logger.info { "return : ${returnObject.toString()}" }
    }
}