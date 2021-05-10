package com.vfurkana.n26.charts.data.remote

import com.vfurkana.n26.charts.data.model.ChartsEntity
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BlockchainChartsApi {

    @GET("charts/{chart-type}")
    suspend fun getCharts(
        @Path("chart-type")
        chartType: String,
        @Query(value = "timespan")
        timespan: String,
        @Query(value = "rollingAverage")
        rollingAverage: String
    ): ChartsEntity
}