package com.kibong.weather.weather.feature.typhoon.service

import com.kibong.weather.weather.common.api.ApiResponseDto
import com.kibong.weather.weather.common.api.RequestApiDto
import com.kibong.weather.weather.feature.typhoon.dto.TyphoonResponseDto
import com.kibong.weather.weather.util.RequestApiUtil
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonObject
import lombok.RequiredArgsConstructor
import mu.KotlinLogging
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
@RequiredArgsConstructor
class TyphoonService (
    private val requestApiUtil: RequestApiUtil
){

    companion object {
        private val logger = KotlinLogging.logger {}
    }

    fun getTodayTyphoonInfo(
        offset: Int = 10,
        pageNo: Int = 1,
    ): ApiResponseDto<TyphoonResponseDto>? {
        val url = "http://apis.data.go.kr/1360000/TyphoonInfoService"
        val uri = "/getTyphoonInfo"

        val today = LocalDate.now().toString().replace("-", "")

        val paramMap = mapOf(
            "numOfRows" to offset,
            "pageNo" to pageNo,
            "dataType" to "JSON",
            "fromTmFc" to today,
            "toTmFc" to today
        )
        val headerMap = mapOf(
            "Content-Type" to "application/json"
        )

        val requestApi: JsonElement? = requestApiUtil.requestApi(RequestApiDto(url, uri, paramMap, null, headerMap, HttpMethod.GET))?.jsonObject?.get("response")
        val json: Json = Json { isLenient = true }
        return requestApi?.let { json.decodeFromJsonElement<ApiResponseDto<TyphoonResponseDto>?>(it) }
    }
}