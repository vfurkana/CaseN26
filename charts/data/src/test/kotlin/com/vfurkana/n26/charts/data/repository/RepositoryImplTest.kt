package com.vfurkana.n26.charts.data.repository


import com.google.common.truth.Truth
import com.vfurkana.n26.charts.data.model.ChartsEntity
import com.vfurkana.n26.charts.data.model.ChartsValueEntity
import com.vfurkana.n26.charts.data.remote.BlockchainChartsApi
import com.vfurkana.n26.charts.domain.repository.ChartsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.junit.Assert.assertThrows
import java.lang.Exception
import java.lang.RuntimeException


private const val ARGUMENT_TIME_SPAN = "1year"
private const val ARGUMENT_ROLLING_AVERAGE = "24hours"
private const val ARGUMENT_CHART_TYPE = "trade-volume"

@ExperimentalCoroutinesApi
class RepositoryImplTest {


    @Test
    fun `GIVEN successful result WHEN requests charts on repository THEN return flow with charts domain model`() {
        val chartsEntity = ChartsEntity(
            "OK", "Some chart", "test", "test", "Chart description", listOf(ChartsValueEntity(1234567890, 200000.0f))
        )

        val chartsApi =
            mock<BlockchainChartsApi> { onBlocking { getCharts(ARGUMENT_CHART_TYPE, ARGUMENT_TIME_SPAN, ARGUMENT_ROLLING_AVERAGE) }.thenReturn(chartsEntity) }
        val chartsRepo: ChartsRepository = ChartsRepositoryImpl(chartsApi)
        val expectedResult = chartsEntity.toDomain()

        runBlockingTest {
            chartsRepo.getCharts(ARGUMENT_CHART_TYPE, ARGUMENT_TIME_SPAN, ARGUMENT_ROLLING_AVERAGE).collect { result ->
                Truth.assertThat(result).isEqualTo(expectedResult)
            }
        }
    }

    @Test
    fun `GIVEN failure result WHEN user requests charts THEN return flow with exception`() {
        val chartsEntity = ChartsEntity(
            "OK", "Some chart", "test", "test", "Chart description", listOf(ChartsValueEntity(1234567890, 200000.0f))
        )

        val chartsApi =
            mock<BlockchainChartsApi> { onBlocking { getCharts(ARGUMENT_CHART_TYPE, ARGUMENT_TIME_SPAN, ARGUMENT_ROLLING_AVERAGE) }.thenThrow(RuntimeException()) }
        val chartsRepo: ChartsRepository = ChartsRepositoryImpl(chartsApi)

        assertThrows(RuntimeException::class.java) {
            runBlockingTest {
                chartsRepo.getCharts(ARGUMENT_CHART_TYPE, ARGUMENT_TIME_SPAN, ARGUMENT_ROLLING_AVERAGE).collect()
            }
        }
    }
}