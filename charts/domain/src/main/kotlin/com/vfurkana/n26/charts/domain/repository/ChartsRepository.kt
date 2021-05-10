package com.vfurkana.n26.charts.domain.repository

import com.vfurkana.n26.charts.domain.model.ChartsDomain
import kotlinx.coroutines.flow.Flow

interface ChartsRepository {

    fun getTransactionsPerSecond(timespan: String, rollingAverage: String): Flow<ChartsDomain>
    fun getUSDMarketPrice(timespan: String, rollingAverage: String): Flow<ChartsDomain>
    fun getUSDTradeVolume(timespan: String, rollingAverage: String): Flow<ChartsDomain>
}