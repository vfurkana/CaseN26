package com.vfurkana.n26.charts.data.di

import com.vfurkana.n26.charts.data.remote.BlockchainChartsApi
import com.vfurkana.n26.charts.data.repository.ChartsRepositoryImpl
import com.vfurkana.n26.charts.domain.repository.ChartsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
class ChartsRepositoryModule {
    @Provides
    fun providesChartsRepository(blockchainChartsApi: BlockchainChartsApi): ChartsRepository {
        return ChartsRepositoryImpl(blockchainChartsApi)
    }
}