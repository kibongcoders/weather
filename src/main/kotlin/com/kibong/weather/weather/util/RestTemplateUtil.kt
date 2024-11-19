package com.kibong.weather.weather.util

import com.kibong.weather.weather.config.KeyProperties
import com.kibong.weather.weather.feature.typhoon.dto.RequestApiDto
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import lombok.RequiredArgsConstructor
import mu.KotlinLogging
import org.springframework.http.HttpHeaders
import org.springframework.http.RequestEntity
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.exchange
import org.springframework.web.util.UriComponentsBuilder

@Component
@RequiredArgsConstructor
class RequestApiUtil constructor(
    private val restTemplate: RestTemplate,
    private val keyProperties: KeyProperties,
) {

    companion object {
        private val logger = KotlinLogging.logger {}
        private const val RETURN_TYPE: String = "json"
    }

    fun requestApi(
        requestApiDto: RequestApiDto
    ): JsonElement? {

        val uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(requestApiDto.url)
            .path(requestApiDto.uri)
            .queryParam("serviceKey", keyProperties.decoding)
            .queryParam("returnType", RETURN_TYPE)

        if (requestApiDto.paramMap != null) {
            requestApiDto.paramMap.forEach { (key, value) ->
                uriComponentsBuilder.queryParam(key, value)
            }
        }

        val httpHeaders = HttpHeaders()
        if (requestApiDto.headerMap != null) {
            requestApiDto.headerMap.forEach { (key, value) ->
                httpHeaders.add(key, value)
            }
        }

        val httpEntity: RequestEntity<Any> = if (requestApiDto.bodyMap != null) {
            RequestEntity(requestApiDto.bodyMap, httpHeaders, requestApiDto.httpMethod, uriComponentsBuilder.build().toUri())
        } else {
            RequestEntity(httpHeaders, requestApiDto.httpMethod, uriComponentsBuilder.build().toUri())
        }

        val response = restTemplate.exchange<String>(
            requestEntity = httpEntity
        )

        response.statusCode
        if (response.statusCode.is2xxSuccessful) {
            return response.body?.let { Json.parseToJsonElement(it) }
        } else {
            logger.warn { response.body }
            throw RuntimeException("error")
        }

    }
}
