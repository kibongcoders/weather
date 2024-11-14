package com.kibong.weather.weather.domain

import com.kibong.weather.weather.feature.air_quality_forecast.dto.AirQualityForecastResponseDto
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import lombok.Getter
import org.hibernate.annotations.Comment
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.annotations.UuidGenerator
import org.hibernate.type.SqlTypes
import java.time.LocalDate
import java.util.*

@Entity
@Table(name = "air_quality_forecast")
@Getter
class AirQualityForecast private constructor(
    imageUrl6: String?,
    imageUrl5: String?,
    imageUrl4: String?,
    imageUrl3: String?,
    imageUrl2: String?,
    imageUrl1: String?,
    informCode: String?,
    actionKnack: String?,
    informData: LocalDate?,
    informCause: String?,
    informOverall: String?,
    informGrade: String?
) {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    @Column(name = "air_quality_forecast_id", nullable = false, columnDefinition = "VARCHAR(36)")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Comment("공기 질 예보 아이디")
    var aisQualityForecastId: UUID = UUID.randomUUID()

    @Column(name = "image_url_6", nullable = true, columnDefinition = "VARCHAR(600)")
    @Comment("시간대별 예측모델 결과사진 6")
    var imageUrl6: String? = imageUrl6

    @Column(name = "image_url_5", nullable = true, columnDefinition = "VARCHAR(600)")
    @Comment("시간대별 예측모델 결과사진 5")
    var imageUrl5: String? = imageUrl5

    @Column(name = "image_url_4", nullable = true, columnDefinition = "VARCHAR(600)")
    @Comment("시간대별 예측모델 결과사진 4")
    var imageUrl4: String? = imageUrl4

    @Column(name = "image_url_3", nullable = true, columnDefinition = "VARCHAR(600)")
    @Comment("시간대별 예측모델 결과사진 3")
    var imageUrl3: String? = imageUrl3

    @Column(name = "image_url_2", nullable = true, columnDefinition = "VARCHAR(600)")
    @Comment("시간대별 예측모델 결과사진 2")
    var imageUrl2: String? = imageUrl2

    @Column(name = "image_url_1", nullable = true, columnDefinition = "VARCHAR(600)")
    @Comment("시간대별 예측모델 결과사진 1")
    var imageUrl1: String? = imageUrl1

    @Column(name = "inform_code", nullable = true, columnDefinition = "VARCHAR(10)")
    @Comment("통보코드")
    var informCode: String? = informCode

    @Column(name = "action_knack", nullable = true, columnDefinition = "VARCHAR(2000)")
    @Comment("행동요령")
    var actionKnack: String? = actionKnack

    @Column(name = "inform_data", nullable = true)
    @Comment("발생원인")
    var informData: LocalDate? = informData

    @Column(name = "inform_cause", nullable = true, columnDefinition = "VARCHAR(2000)")
    @Comment("발생원인")
    var informCause: String? = informCause

    @Column(name = "inform_overall", nullable = true, columnDefinition = "VARCHAR(500)")
    @Comment("예보개황")
    var informOverall: String? = informOverall

    @Column(name = "inform_grade", nullable = true, columnDefinition = "VARCHAR(1000)")
    @Comment("예보등급")
    var informGrade: String? = informGrade

    companion object {
        fun fromAirPollutionResponseDto(
            airQualityForecastDto: AirQualityForecastResponseDto
        ): AirQualityForecast {
            return AirQualityForecast(
                imageUrl6 = airQualityForecastDto.imageUrl6,
                imageUrl5 = airQualityForecastDto.imageUrl5,
                imageUrl4 = airQualityForecastDto.imageUrl4,
                imageUrl3 = airQualityForecastDto.imageUrl3,
                imageUrl2 = airQualityForecastDto.imageUrl2,
                imageUrl1 = airQualityForecastDto.imageUrl1,
                informCode = airQualityForecastDto.informCode,
                actionKnack = airQualityForecastDto.actionKnack,
                informData = airQualityForecastDto.informData?.let { LocalDate.parse(it) },
                informCause = airQualityForecastDto.informCause,
                informOverall = airQualityForecastDto.informOverall,
                informGrade = airQualityForecastDto.informGrade
            )
        }
    }
}