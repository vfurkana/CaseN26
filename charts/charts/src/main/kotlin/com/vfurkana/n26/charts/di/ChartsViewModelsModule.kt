package com.vfurkana.n26.charts.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vfurkana.n26.bc.di.viewmodel.ViewModelFactory
import com.vfurkana.n26.bc.di.viewmodel.ViewModelKey
import com.vfurkana.n26.charts.viewmodel.ChartsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ChartsViewModelsModule {
    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ChartsViewModel::class)
    fun chartsViewModel(viewModel: ChartsViewModel): ViewModel
}