package com.vfurkana.n26.charts.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.vfurkana.n26.bc.utils.StatefulData
import com.vfurkana.n26.charts.domain.model.*
import com.vfurkana.n26.charts.domain.usecase.ChartsUseCase
import com.vfurkana.n26.charts.model.Charts
import com.vfurkana.n26.charts.model.toUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import okhttp3.internal.wait
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.ArgumentMatchers
import org.mockito.kotlin.*


@ExperimentalCoroutinesApi
class ChartsViewModelTest {


    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Test
    fun `GIVEN successful result WHEN user opens charts feature THEN show loading and successful response sequentially`() {
        Dispatchers.setMain(TestCoroutineDispatcher())
        val chartsDomain = ChartsDomain(
            name = "Chart name",
            unit = "Transactions",
            period = "day",
            description = "The number of daily confirmed Bitcoin transactions.",
            values = listOf(
                ChartsValueDomain(
                    timestamp = 1442534400,
                    value = 188330.0f
                )
            )
        )

        val getChartsUseCase: ChartsUseCase = mock {
            onBlocking { getCharts(ChartsTypeDomain.TRANSACTIONS_PER_SECOND, ChartsDateRangeDomain.ONE_MONTH, ChartsRollingAverageDomain.EIGHT_HOURS) }
                .thenReturn(flowOf(chartsDomain))
        }
        val chartsViewModel = ChartsViewModel(TestCoroutineDispatcher(), TestCoroutineDispatcher(), getChartsUseCase)
        val expectedResult = chartsDomain.toUi()

        val observer = spy<Observer<StatefulData<Charts>>> { }
        chartsViewModel.chartData.observeForever(observer)
        chartsViewModel.getChart()
        chartsViewModel.onDateRange(ChartsDateRangeDomain.ONE_WEEK)
        chartsViewModel.onChartType(ChartsTypeDomain.TRANSACTIONS_PER_SECOND)

        inOrder(observer) {
            verify(observer).onChanged(isA<StatefulData.Progress>())
            //TODO on device and emulator this always gets called with success after progress but fails on test
            verify(observer).onChanged(isA<StatefulData.Success<Charts>>())
        }

        Dispatchers.resetMain()
    }

    @Test
    fun `GIVEN failure result WHEN user opens charts feature THEN show loading and error response sequentially`() {
        Dispatchers.setMain(TestCoroutineDispatcher())
        val getChartsUseCase: ChartsUseCase = mock {
            onBlocking { getCharts(ChartsTypeDomain.TRANSACTIONS_PER_SECOND, ChartsDateRangeDomain.ONE_MONTH, ChartsRollingAverageDomain.EIGHT_HOURS) }
                .thenReturn(flow { throw RuntimeException() })
        }
        val chartsViewModel = ChartsViewModel(TestCoroutineDispatcher(), TestCoroutineDispatcher(), getChartsUseCase)


        val observer = spy<Observer<StatefulData<Charts>>> { }
        chartsViewModel.chartData.observeForever(observer)
        chartsViewModel.getChart()
        chartsViewModel.onDateRange(ChartsDateRangeDomain.ONE_WEEK)
        chartsViewModel.onChartType(ChartsTypeDomain.TRANSACTIONS_PER_SECOND)

        inOrder(observer) {
            verify(observer).onChanged(isA<StatefulData.Progress>())
            //TODO on device and emulator this always gets called with success after progress but fails on test
            verify(observer).onChanged(isA<StatefulData.Error>())
        }
        Dispatchers.resetMain()
    }

}