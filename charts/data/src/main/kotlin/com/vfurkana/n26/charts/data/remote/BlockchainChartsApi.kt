package com.vfurkana.n26.charts.data.remote

import com.vfurkana.n26.charts.data.model.ChartsEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface BlockchainChartsApi {

    @GET("charts/transactions-per-second")
    suspend fun getTransactionsPerSecond(
        @Query(value = "timespan")
        timespan: String,
        @Query(value = "rollingAverage")
        rollingAverage: String
    ): ChartsEntity

    @GET("charts/market-price")
    suspend fun getUSDMarketPrice(
        @Query(value = "timespan")
        timespan: String,
        @Query(value = "rollingAverage")
        rollingAverage: String
    ): ChartsEntity

    @GET("charts/trade-volume")
    suspend fun gerUSDTradeVolume(
        @Query(value = "timespan")
        timespan: String,
        @Query(value = "rollingAverage")
        rollingAverage: String
    ): ChartsEntity
}