package com.kibong.weather.weather.feature.typhoon.service

import com.kibong.weather.weather.feature.typhoon.dto.RequestApiDto
import com.kibong.weather.weather.util.RequestApiUtil
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

    fun getTodayTyphoonInfo() {
        val url = "http://apis.data.go.kr/1360000/TyphoonInfoService"
        val uri = "/getTyphoonInfo"

        val today = LocalDate.now().toString().replace("-", "")
        logger.info { today }
        val paramMap = mapOf(
            "numOfRows" to 10,
            "pageNo" to 1,
            "dataType" to "JSON",
            "fromTmFc" to today,
            "toTmFc" to today
        )
        val headerMap = mapOf(
            "Content-Type" to "application/json"
        )

        val requestApi = requestApiUtil.requestApi(RequestApiDto(url, uri, paramMap, null, headerMap, HttpMethod.GET))
        logger.info { requestApi.toString() }
    }
}