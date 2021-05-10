package com.vfurkana.n26.charts.domain.usecase

import com.vfurkana.n26.charts.domain.model.ChartsDomain
import com.vfurkana.n26.charts.domain.model.ChartsDateRangeDomain
import com.vfurkana.n26.charts.domain.model.ChartsRollingAverageDomain
import com.vfurkana.n26.charts.domain.model.ChartsTypeDomain
import com.vfurkana.n26.charts.domain.repository.ChartsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChartsUseCase @Inject constructor(
    private val chartsRepository: ChartsRepository
) {

    fun getCharts(
        chartsTypeDomain: ChartsTypeDomain,
        chartsDateRangeDomain: ChartsDateRangeDomain,
        chartsRollingAverageDomain: ChartsRollingAverageDomain = ChartsRollingAverageDomain.TWENTY_FOUR_HOURS
    ): Flow<ChartsDomain> {
        return chartsRepository.getCharts(chartsTypeDomain.value, chartsDateRangeDomain.value, chartsRollingAverageDomain.value)
    }

}