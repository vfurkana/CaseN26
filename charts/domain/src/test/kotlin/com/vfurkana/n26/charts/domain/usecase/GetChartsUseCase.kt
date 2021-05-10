package com.vfurkana.n26.charts.domain.usecase

import com.google.common.truth.Truth
import com.vfurkana.n26.charts.domain.model.*
import com.vfurkana.n26.charts.domain.repository.ChartsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertThrows
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

private const val ARGUMENT_TIME_SPAN = "7days"
private const val ARGUMENT_ROLLING_AVERAGE = "8hours"
private const val ARGUMENT_CHART_TYPE = "transactions-per-second"

@ExperimentalCoroutinesApi
class GetChartsUseCaseTest {

    private val chartsRepository: ChartsRepository = mock(ChartsRepository::class.java)
    private val getChartsUseCase: ChartsUseCase = ChartsUseCase(chartsRepository)

    @Test
    fun `GIVEN successful result WHEN requests charts usecase THEN return data`() {
        val chartsDomain = ChartsDomain("Some chart name", "Test", "hour","Bitcoin transactions per second",
            values = listOf(
                ChartsValueDomain(
                    timestamp = 1442534400,
                    value = 188330.0f
                )
            )
        )

        `when`(chartsRepository.getCharts(ARGUMENT_CHART_TYPE, ARGUMENT_TIME_SPAN, ARGUMENT_ROLLING_AVERAGE))
            .thenReturn(flowOf(chartsDomain))

        runBlockingTest {
            getChartsUseCase.getCharts(
                ChartsTypeDomain.TRANSACTIONS_PER_SECOND,
                ChartsDateRangeDomain.ONE_WEEK,
                ChartsRollingAverageDomain.EIGHT_HOURS
            ).collect { result ->
                Truth.assertThat(result).isEqualTo(chartsDomain)
            }
        }
    }

    @Test
    fun `GIVEN failure result WHEN requests charts usecase THEN return flow with exception`() {
        `when`(chartsRepository.getCharts(ARGUMENT_CHART_TYPE, ARGUMENT_TIME_SPAN, ARGUMENT_ROLLING_AVERAGE)).thenReturn(flow { throw Exception() })

        assertThrows(Exception::class.java) {
            runBlockingTest {
                getChartsUseCase.getCharts(ChartsTypeDomain.TRANSACTIONS_PER_SECOND, ChartsDateRangeDomain.ONE_WEEK, ChartsRollingAverageDomain.EIGHT_HOURS).collect()
            }
        }
    }
}