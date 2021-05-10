package com.vfurkana.n26.charts.di

import com.vfurkana.n26.bc.di.ChartsFeatureDependencies
import com.vfurkana.n26.charts.data.di.ChartsApiModule
import com.vfurkana.n26.charts.data.di.ChartsRepositoryModule
import com.vfurkana.n26.charts.domain.di.ChartsUseCaseModule
import com.vfurkana.n26.charts.view.ChartsActivity
import dagger.Component
import retrofit2.Retrofit

@Component(
    dependencies = [ChartsFeatureDependencies::class],
    modules = [
        ChartsApiModule::class,
        ChartsRepositoryModule::class,
        ChartsUseCaseModule::class,
        ChartsViewModelsModule::class
    ]
)
interface ChartsComponent {

    fun inject(chartsActivity: ChartsActivity)

    @Component.Builder
    interface Builder {
        fun featureDependencies(featureDependencies: ChartsFeatureDependencies): Builder
        fun build(): ChartsComponent
    }
}