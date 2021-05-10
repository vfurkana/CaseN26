package com.vfurkana.n26.charts.data.repository

import com.vfurkana.n26.charts.data.remote.BlockchainChartsApi
import com.vfurkana.n26.charts.domain.model.ChartsDomain
import com.vfurkana.n26.charts.domain.repository.ChartsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ChartsRepositoryImpl @Inject constructor(
    private val blockchainChartsApi: BlockchainChartsApi
): ChartsRepository {

    override fun getCharts(
        chartType: String,
        timespan: String,
        rollingAverage: String
    ): Flow<ChartsDomain> {
        return flow {
            val result = blockchainChartsApi.getCharts(chartType, timespan, rollingAverage).toDomain()
            emit(result)
        }
    }
}