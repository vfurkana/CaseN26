package com.vfurkana.n26.bc.di

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher

@EntryPoint
@InstallIn(SingletonComponent::class)
interface AppDependecies {
    @IODispatcher fun providesIODispatcher(): CoroutineDispatcher
    @MainDispatcher fun providesMainDispatcher(): CoroutineDispatcher
}