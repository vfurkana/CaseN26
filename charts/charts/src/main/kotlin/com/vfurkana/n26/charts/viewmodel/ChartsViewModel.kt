package com.vfurkana.n26.charts.viewmodel

import android.util.Pair
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vfurkana.n26.bc.di.IODispatcher
import com.vfurkana.n26.bc.di.MainDispatcher
import com.vfurkana.n26.bc.utils.StatefulData
import com.vfurkana.n26.charts.domain.model.ChartsDateRangeDomain
import com.vfurkana.n26.charts.domain.model.ChartsDomain
import com.vfurkana.n26.charts.domain.model.ChartsRollingAverageDomain
import com.vfurkana.n26.charts.domain.model.ChartsTypeDomain
import com.vfurkana.n26.charts.domain.usecase.ChartsUseCase
import com.vfurkana.n26.charts.model.Charts
import com.vfurkana.n26.charts.model.toUi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChartsViewModel @Inject constructor(
    @MainDispatcher val mainDispatcher: CoroutineDispatcher,
    @IODispatcher val ioDispatcher: CoroutineDispatcher,
    val chartsUseCase: ChartsUseCase
) : ViewModel() {

    private val _activeDateRange = MutableLiveData<ChartsDateRangeDomain>()
    val activeDateRange: LiveData<ChartsDateRangeDomain> = _activeDateRange

    private val _activeChartType = MutableLiveData<ChartsTypeDomain>()
    val activeChartType: LiveData<ChartsTypeDomain> = _activeChartType

    private val dateRangeChannel = ConflatedBroadcastChannel<ChartsDateRangeDomain>()
    fun onDateRange(range: ChartsDateRangeDomain) {
        viewModelScope.launch {
            dateRangeChannel.offer(range)
        }
    }

    private val chartTypeChannel = ConflatedBroadcastChannel<ChartsTypeDomain>()
    fun onChartType(chartType: ChartsTypeDomain) {
        viewModelScope.launch {
            chartTypeChannel.offer(chartType)
        }
    }

    private val _chartData = MutableLiveData<StatefulData<Charts>>()
    val chartData: LiveData<StatefulData<Charts>> = _chartData
    fun getChart() {
        combine(dateRangeChannel.asFlow(), chartTypeChannel.asFlow()) { dateRange, type ->
            Pair(dateRange, type)
        }.onStart { _chartData.postValue(StatefulData.Progress) }
            .onEach { _chartData.postValue(StatefulData.Progress) }
            .debounce(1000)
            .flatMapMerge {
                _activeDateRange.postValue(it.first)
                _activeChartType.postValue(it.second)
                chartsUseCase.getCharts(it.second, it.first, ChartsRollingAverageDomain.EIGHT_HOURS)
            }.map<ChartsDomain, StatefulData<Charts>> {
                StatefulData.Success(it.toUi())
            }.flowOn(ioDispatcher)
            .onEach { _chartData.postValue(it) }
            .catch { _chartData.postValue(StatefulData.Error(it)) }
            .flowOn(mainDispatcher)
            .launchIn(viewModelScope)
    }

}