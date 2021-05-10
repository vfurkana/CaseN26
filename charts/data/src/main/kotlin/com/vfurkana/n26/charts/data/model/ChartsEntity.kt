package com.vfurkana.n26.charts.data.model

import com.google.gson.annotations.SerializedName
import com.vfurkana.n26.charts.domain.model.ChartsDomain
import com.vfurkana.n26.charts.domain.model.ChartsValueDomain


data class ChartsEntity(
    @SerializedName("status")
    val status: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("unit")
    val unit: String,
    @SerializedName("period")
    val period: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("values")
    val values: List<ChartsValueEntity>
) {
    fun toDomain(): ChartsDomain {
        return ChartsDomain(
            name = name,
            unit = unit,
            period = period,
            description = description,
            values = values.map { it.toDomain() }
        )
    }
}

data class ChartsValueEntity(
    @SerializedName("x")
    val timestamp: Long,
    @SerializedName("y")
    val value: Float
) {
    fun toDomain(): ChartsValueDomain {
        return ChartsValueDomain(
            timestamp = timestamp,
            value = value
        )
    }
}
