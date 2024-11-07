package com.kibong.weather.weather.feature.air_quality_forecast.service

import com.kibong.weather.weather.config.BaseUrlProperties
import com.kibong.weather.weather.config.KeyProperties
import com.kibong.weather.weather.domain.AirQualityForecast
import com.kibong.weather.weather.feature.air_pollution.dto.AirQualityForecastResponseDto
import com.kibong.weather.weather.feature.air_pollution.dto.ResponseDto
import com.kibong.weather.weather.feature.air_quality_forecast.repository.AirQualityForecastRepository
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonObject
import lombok.RequiredArgsConstructor
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

@Service
@RequiredArgsConstructor
class AirQualityForecastService(
    private val restTemplate: RestTemplate,
    private val baseUrlProperties: BaseUrlProperties,
    private val keyProperties: KeyProperties,
    private val airQualityForecastRepository: AirQualityForecastRepository
) {

    private val logger = KotlinLogging.logger {}

    fun getAirQualityForecast() {
        val url = UriComponentsBuilder.fromHttpUrl(baseUrlProperties.airPollution)
            .path("/getMinuDustFrcstDspth")
            .queryParam("serviceKey", keyProperties.decoding)
            .queryParam("returnType", "json")
            .queryParam("numOfRows", 100)
            .queryParam("pageNo", 1)
            .queryParam("searchDate", "2024-11-07")
            .build()
            .toUriString()

        val returnObject = restTemplate.getForObject(url, String::class.java)
        val parseToJsonElement = Json.parseToJsonElement(returnObject.toString())
        val body = parseToJsonElement.jsonObject.get("response")?.jsonObject?.get("body")
        val responseDto: ResponseDto<AirQualityForecastResponseDto>? = body?.let { Json.decodeFromJsonElement(it) }
        val items = responseDto?.items
        val airQualityForecasts = mutableListOf<AirQualityForecast>()
        if (items != null) {
            for (item in items) {

                val airQualityForecast = AirQualityForecast.fromAirPollutionResponseDto(item)
                airQualityForecasts.add(airQualityForecast)
            }
        }
        airQualityForecastRepository.saveAll(airQualityForecasts)
    }
}