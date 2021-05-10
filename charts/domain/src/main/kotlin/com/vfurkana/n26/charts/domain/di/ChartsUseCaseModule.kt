package com.vfurkana.n26.charts.domain.di

import com.vfurkana.n26.charts.domain.repository.ChartsRepository
import com.vfurkana.n26.charts.domain.usecase.ChartsUseCase
import dagger.Module
import dagger.Provides

@Module
class ChartsUseCaseModule {

    @Provides
    fun providesChartsUseCase(chartsRepository: ChartsRepository): ChartsUseCase {
        return ChartsUseCase(chartsRepository)
    }

}