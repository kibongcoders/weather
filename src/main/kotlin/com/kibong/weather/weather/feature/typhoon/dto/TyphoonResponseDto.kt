package com.kibong.weather.weather.feature.typhoon.dto

import kotlinx.serialization.Serializable

@Serializable
data class TyphoonResponseDto(
    val other: String,
    val img: String,
    val rem: String,
    val tmFc: String,
    val tmSeq: Int,
    val typ15: Int,
    val typ15ed: String,
    val typ15er: Int,
    val typ25: Int,
    val typ25ed: String,
    val typ25er: Int,
    val typDir: String,
    val typEn: String,
    val typLoc: String,
    val typLat: Double,
    val typLon: Double,
    val typName: String,
    val typPs: Int,
    val typSeq: Int,
    val typSp: Int,
    val typTm: String,
    val typWs: Int
)
