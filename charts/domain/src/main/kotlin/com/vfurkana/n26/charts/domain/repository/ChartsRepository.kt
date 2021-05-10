package com.vfurkana.n26.charts.domain.repository

import com.vfurkana.n26.charts.domain.model.ChartsDomain
import kotlinx.coroutines.flow.Flow

interface ChartsRepository {
    fun getCharts(chartType: String, timespan: String, rollingAverage: String): Flow<ChartsDomain>
}