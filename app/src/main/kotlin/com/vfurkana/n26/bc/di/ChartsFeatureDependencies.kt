package com.vfurkana.n26.bc.di

import retrofit2.Retrofit
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface ChartsFeatureDependencies : AppDependecies {
    fun provideRetrofit(): Retrofit
}