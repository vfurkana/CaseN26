package com.vfurkana.n26.charts.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vfurkana.n26.bc.di.IODispatcher
import com.vfurkana.n26.bc.di.MainDispatcher
import com.vfurkana.n26.charts.domain.model.ChartsPeriodDomain
import com.vfurkana.n26.charts.domain.model.ChartsRollingAverageDomain
import com.vfurkana.n26.charts.domain.usecase.ChartsUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class ChartsViewModel @Inject constructor(
    @MainDispatcher val mainDispatcher: CoroutineDispatcher,
    @IODispatcher val ioDispatcher: CoroutineDispatcher,
    val chartsUseCase: ChartsUseCase
) : ViewModel() {

    val eventLiveData = MutableLiveData<String>()

    fun getChart() {
        chartsUseCase.getTransactionsPerSecond(
            ChartsPeriodDomain.ONE_MONTH,
            ChartsRollingAverageDomain.EIGHT_HOURS)
            .flowOn(ioDispatcher)
            .onStart { eventLiveData.value = "Start" }
            .onEach { eventLiveData.value = it.name }
            .catch { eventLiveData.value = "catch: ${it.message}"}
            .flowOn(mainDispatcher)
            .launchIn(viewModelScope)
    }

}