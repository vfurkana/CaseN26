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

    override fun getTransactionsPerSecond(timespan: String, rollingAverage: String): Flow<ChartsDomain> {
        return flow {
            val result = blockchainChartsApi.getTransactionsPerSecond(timespan, rollingAverage).toDomain()
            emit(result)
        }
    }

    override fun getUSDMarketPrice(timespan: String, rollingAverage: String): Flow<ChartsDomain> {
        return flow {
            val result = blockchainChartsApi.getUSDMarketPrice(timespan, rollingAverage).toDomain()
            emit(result)
        }
    }

    override fun getUSDTradeVolume(timespan: String, rollingAverage: String): Flow<ChartsDomain> {
        return flow {
            val result = blockchainChartsApi.gerUSDTradeVolume(timespan, rollingAverage).toDomain()
            emit(result)
        }
    }
}