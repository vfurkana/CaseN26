package com.vfurkana.n26.charts.data.di

import com.vfurkana.n26.charts.data.remote.BlockchainChartsApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ChartsApiModule {

    @Provides
    fun provideBlockchainChartsApi(retrofit: Retrofit): BlockchainChartsApi {
        return retrofit.create(BlockchainChartsApi::class.java)
    }
}