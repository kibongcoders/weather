package com.kibong.weather.weather.feature.air_quality_forecast.repository

import com.kibong.weather.weather.domain.AirQualityForecast
import com.linecorp.kotlinjdsl.querydsl.expression.col
import com.linecorp.kotlinjdsl.spring.data.SpringDataQueryFactory
import com.linecorp.kotlinjdsl.spring.data.listQuery
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
@RequiredArgsConstructor
class AirQualityForecastQueryRepository(private val queryFactory: SpringDataQueryFactory) {

    fun getAirQualityForecast(searchDate: LocalDate): List<AirQualityForecast> {
        return queryFactory.listQuery {
            select(entity(AirQualityForecast::class))
            from(entity(AirQualityForecast::class))
            where(col(AirQualityForecast::informData).equal(searchDate))
        }
    }
}