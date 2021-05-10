package com.vfurkana.n26.charts.domain.usecase

import com.vfurkana.n26.charts.domain.model.ChartsDomain
import com.vfurkana.n26.charts.domain.model.ChartsPeriodDomain
import com.vfurkana.n26.charts.domain.model.ChartsRollingAverageDomain
import com.vfurkana.n26.charts.domain.repository.ChartsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChartsUseCase @Inject constructor(
    private val chartsRepository: ChartsRepository
) {

    fun getTransactionsPerSecond(
        chartsPeriodDomain: ChartsPeriodDomain,
        chartsRollingAverageDomain: ChartsRollingAverageDomain = ChartsRollingAverageDomain.TWENTY_FOUR_HOURS
    ): Flow<ChartsDomain> {
        return chartsRepository.getTransactionsPerSecond(chartsPeriodDomain.value, chartsRollingAverageDomain.value)
    }
    fun getUSDMarketPrice(
        chartsPeriodDomain: ChartsPeriodDomain,
        chartsRollingAverageDomain: ChartsRollingAverageDomain = ChartsRollingAverageDomain.TWENTY_FOUR_HOURS
    ): Flow<ChartsDomain> {
        return chartsRepository.getUSDMarketPrice(chartsPeriodDomain.value, chartsRollingAverageDomain.value)
    }
    fun getUSDTradeVolume(
        chartsPeriodDomain: ChartsPeriodDomain,
        chartsRollingAverageDomain: ChartsRollingAverageDomain = ChartsRollingAverageDomain.TWENTY_FOUR_HOURS
    ): Flow<ChartsDomain> {
        return chartsRepository.getUSDTradeVolume(chartsPeriodDomain.value, chartsRollingAverageDomain.value)
    }

}