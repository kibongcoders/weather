package com.kibong.weather.weather.feature.air_pollution.service

import com.kibong.weather.weather.config.BaseUrlProperties
import com.kibong.weather.weather.config.KeyProperties
import com.kibong.weather.weather.feature.air_pollution.dto.AirPollutionResponseDto
import com.kibong.weather.weather.feature.air_pollution.dto.ResponseDto
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonObject
import lombok.RequiredArgsConstructor
import mu.KotlinLogging
import mu.KotlinLogging.logger
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import kotlin.math.log

@Service
@RequiredArgsConstructor
class AirPollutionService(
    private val restTemplate: RestTemplate,
    private val baseUrlProperties: BaseUrlProperties,
    private val keyProperties: KeyProperties
) {

    private val logger = KotlinLogging.logger {}

    fun getAirPollution() {
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
        val parseToJsonElement = Json.parseToJsonElement(returnObject.toString())
        val body = parseToJsonElement.jsonObject.get("response")?.jsonObject?.get("body")
        val responseDto: ResponseDto<AirPollutionResponseDto>? = body?.let { Json.decodeFromJsonElement(it) }
        responseDto?.items?.forEach {
            logger.info { "item : $it" }
        }
    }
}