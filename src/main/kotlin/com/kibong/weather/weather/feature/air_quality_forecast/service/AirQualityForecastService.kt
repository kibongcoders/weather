package com.kibong.weather.weather.feature.air_quality_forecast.service

import com.kibong.weather.weather.common.ListDto
import com.kibong.weather.weather.config.BaseUrlProperties
import com.kibong.weather.weather.config.KeyProperties
import com.kibong.weather.weather.domain.AirQualityForecast
import com.kibong.weather.weather.feature.air_quality_forecast.dto.AirQualityForecastDto
import com.kibong.weather.weather.feature.air_quality_forecast.dto.AirQualityForecastResponseDto
import com.kibong.weather.weather.feature.air_quality_forecast.dto.ResponseDto
import com.kibong.weather.weather.feature.air_quality_forecast.repository.AirQualityForecastQueryRepository
import com.kibong.weather.weather.feature.air_quality_forecast.repository.AirQualityForecastRepository
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonObject
import lombok.RequiredArgsConstructor
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import java.time.LocalDate

@Service
@RequiredArgsConstructor
class AirQualityForecastService(
    private val restTemplate: RestTemplate,
    private val baseUrlProperties: BaseUrlProperties,
    private val keyProperties: KeyProperties,
    private val airQualityForecastRepository: AirQualityForecastRepository,
    private val airQualityForecastQueryRepository: AirQualityForecastQueryRepository
) {

    companion object {
        private val logger = KotlinLogging.logger {}
        private const val RETURN_TYPE: String = "json"
        private const val GET_AIR_QUALITY_FORECAST_END_POINT = "/getMinuDustFrcstDspth"
    }

    fun getAirQualityForecast(searchDate: String): ListDto {
        try {
            val airQualityForecastDtoList = airQualityForecastQueryRepository.getAirQualityForecast(LocalDate.parse(searchDate)).map { airQualityForecast ->
                AirQualityForecastDto.fromAirQualityForecast(airQualityForecast)
            }.toList()
            return ListDto(airQualityForecastDtoList.size, airQualityForecastDtoList)
        } catch (e: Exception) {
            logger.error(e) { "getAirQualityForecast error" }
            return ListDto(0, emptyList())
        }
    }

    fun saveAirQualityForecast(pageNo: Int, pageSize: Int, searchDate: String) {
        val responseDto: ResponseDto<AirQualityForecastResponseDto>? = getAirQualityForecast(pageSize, searchDate)

        val items = responseDto?.items
        val airQualityForecasts = mutableListOf<AirQualityForecast>()
        val dataTimeSet = HashSet<String>()

        convertAjrQualityForecast(items, dataTimeSet, airQualityForecasts)
        airQualityForecastRepository.saveAll(airQualityForecasts)
    }

    private fun convertAjrQualityForecast(
        items: List<AirQualityForecastResponseDto>?,
        dataTimeSet: HashSet<String>,
        airQualityForecasts: MutableList<AirQualityForecast>
    ) {
        if (items != null) {
            for (item in items) {
                if (dataTimeSet.contains("${item.informData},${item.informCode}")) {
                    continue
                } else {
                    item.dataTime?.let { dataTimeSet.add("${item.informData},${item.informCode}") }
                }

                val airQualityForecast = AirQualityForecast.fromAirPollutionResponseDto(item)
                airQualityForecasts.add(airQualityForecast)
            }
        }
    }

    fun getAirQualityForecast(pageSize: Int, searchDate: String): ResponseDto<AirQualityForecastResponseDto>? {
        val url = UriComponentsBuilder.fromHttpUrl(baseUrlProperties.airPollution)
            .path(GET_AIR_QUALITY_FORECAST_END_POINT)
            .queryParam("serviceKey", keyProperties.decoding)
            .queryParam("returnType", RETURN_TYPE)
            .queryParam("numOfRows", pageSize)
            .queryParam("pageNo", Int)
            .queryParam("searchDate", searchDate)
            .build()
            .toUriString()

        val returnObject = restTemplate.getForObject(url, String::class.java)
        val parseToJsonElement = Json.parseToJsonElement(returnObject.toString())
        val body = parseToJsonElement.jsonObject.get("response")?.jsonObject?.get("body")
        val responseDto: ResponseDto<AirQualityForecastResponseDto>? = body?.let { Json.decodeFromJsonElement(it) }
        return responseDto
    }
}